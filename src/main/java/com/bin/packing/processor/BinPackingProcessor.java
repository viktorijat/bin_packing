package com.bin.packing.processor;

import com.bin.packing.model.Activity;
import com.bin.packing.model.ActivityType;
import com.bin.packing.model.Team;
import com.bin.packing.repository.ActivityRepository;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BinPackingProcessor {

    private LocalTime startOfDay = LocalTime.of(9, 0, 0);
    private LocalTime endOfDay = LocalTime.of(17, 0, 0);
    private LocalTime startOfLunchBreak = LocalTime.of(11, 30, 0);

    @Autowired
    ActivityRepository activityRepository;


    public void binPacking(List<Activity> activities, Duration size, int n) {

        List<Team> teams = Arrays.asList(new Team(1L, startOfDay, startOfDay, endOfDay, startOfLunchBreak, Duration.ofMinutes(0)),
                new Team(2L, startOfDay, startOfDay, endOfDay, startOfLunchBreak, Duration.ofMinutes(0)));

        for (Team team : teams) {

            boolean hasHadLunch = false;

            for (Activity activity : new ArrayList<Activity>(activities)) {

//                if (!(startOfDay.plus(team.getActivitiesTotalTime()).compareTo(endOfDay) == -1)) {
//                    break;
//                }

//                if (team.isEndOfDay()) {
//                    team.addActivity(new Activity("Staff Motivation Presentation", Duration.ofMinutes(60),
//                            ActivityType.MOTIVATION, team.getCurrentTime()));
//                    break;
//                }

                team.isViableActivityTime(activity);

                if (team.isBeforeLunchBreak(activity)) {
                    team.addActivity(new Activity(activity.getName(), activity.getLength(), ActivityType.NORMAL, team.getCurrentTime()));
                    activities.remove(activity);
                } else if (team.isLunchTime() && !hasHadLunch) {
                    team.addActivity(new Activity("Lunch Break", Duration.ofMinutes(60), ActivityType.LUNCH, team.getCurrentTime()));
                    hasHadLunch = true;
                } else {
                    team.addActivity(new Activity("Staff Motivation Presentation", Duration.ofMinutes(60), ActivityType.MOTIVATION, team.getCurrentTime()));
                    break;
                }

            }
        }

        System.out.println(activities);

        for (Team team : teams) {
            System.out.println("TEAM " + team.getTeamId() + " - " + team.getCurrentTime() + " - " + team.getActivitiesTotalTime());
            for (Activity activity : team.getActivities()) {
                System.out.println(activity);
            }
            System.out.println();
        }
//        System.out.println("TEAM 1: " + teams.get(0).getActivities());
//        System.out.println("TEAM 1: " + teams.get(0).getActivitiesTotalTime());
//        System.out.println("TEAM 2: " + teams.get(1).getActivities());
//        System.out.println("TEAM 2: " + teams.get(1).getActivitiesTotalTime());

//        int binCount = 1;
//        Duration s = size;
//
//        for (int i = 0; i < n; i++) {
//            if (s.compareTo(a.get(i).getLength()) > 0) {
//                s = s.minus(a.get(i).getLength());
//                continue;
//            } else {
//                binCount++;
//                s = size;
//                i--;
//            }
//        }
//        System.out.println("Number of bins required: " + binCount);
    }

//        https://www.sanfoundry.com/java-program-implement-bin-packing-algorithm/

    public void calculate() {


        Iterable<Activity> all = activityRepository.findAll();
        List<Activity> myList = new ArrayList<>(((List<Activity>) all).size());
        all.forEach(myList::add);


        binPacking(myList, Duration.ofHours(8), 22);
//
//        List<Team> teams = Arrays.asList(new Team(), new Team());
    }
}
