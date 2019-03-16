package com.bin.packing.processor;

import com.bin.packing.model.Activity;
import com.bin.packing.model.Team;
import com.bin.packing.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class BinPackingProcessor {

    private LocalTime startOfDay = LocalTime.of(9, 0, 0);
    private LocalTime endOfDay = LocalTime.of(17, 0, 0);
    private LocalTime startOfLunchBreak = LocalTime.of(11, 31, 0);

    @Autowired
    private ActivityRepository activityRepository;


    private Activity findNextShortestActivity(List<Activity> activities) {
        return activities.stream().min(Comparator.comparing(Activity::getLength)).orElse(null);
    }

    private void removeActivity(List<Activity> activities, Activity activity) {
        activities.remove(activity);
    }

    private void removeActivity(List<Activity> activities, int i) {
        activities.remove(i);
    }

    private void binPacking(List<Activity> activities, Duration size, int n) {

        List<Team> teams = Arrays.asList(new Team(1L, startOfDay, startOfDay, endOfDay, startOfLunchBreak, Duration.ofMinutes(0)),
                new Team(2L, startOfDay, startOfDay, endOfDay, startOfLunchBreak, Duration.ofMinutes(0)));

        for (Team team : teams) {

            boolean hasHadLunch = false;

            int i = 0;
            while (team.getCurrentTime().isBefore(endOfDay) && i < activities.size()) {

                Activity next = activities.get(i);
                Activity nextShortest = findNextShortestActivity(activities);

                if (team.isBeforeLunchBreak(next)) {
                    team.addActivity(new Activity(next.getName(), next.getLength(), team.getCurrentTime()));
                    removeActivity(activities, i);
                } else if (team.isBeforeLunchBreak(nextShortest)) {
                    team.addActivity(new Activity(nextShortest.getName(), nextShortest.getLength(), team.getCurrentTime()));
                    removeActivity(activities, nextShortest);
                } else if (team.isLunchTime() && !hasHadLunch) {
                    team.addActivity(new Activity("Lunch Break", Duration.ofMinutes(60), team.getCurrentTime()));
                    hasHadLunch = true;
                } else if (team.isAfterLunchBreakAndBeforeEndOfDay(next)) {
                    team.addActivity(new Activity(next.getName(), next.getLength(), team.getCurrentTime()));
                    removeActivity(activities, i);
                } else if (team.isAfterLunchBreakAndBeforeEndOfDay(nextShortest)) {
                    team.addActivity(new Activity(nextShortest.getName(), nextShortest.getLength(), team.getCurrentTime()));
                    removeActivity(activities, nextShortest);
                } else {
                    break;
                }
            }
            team.addActivity(new Activity("Staff Motivation Presentation", Duration.ofMinutes(60), team.getCurrentTime()));
        }


        for (Team team : teams) {
            System.out.println("TEAM " + team.getTeamId() + " - " + team.getCurrentTime() + " - " + team.getActivitiesTotalTime());
            for (Activity activity : team.getActivities()) {
                System.out.println(activity);
            }
            System.out.println();
        }
    }

    public void calculate() {

        Iterable<Activity> all = activityRepository.findAll();
        List<Activity> myList = new ArrayList<>(((List<Activity>) all).size());
        all.forEach(myList::add);
        binPacking(myList, Duration.ofHours(8), 22);
    }
}
