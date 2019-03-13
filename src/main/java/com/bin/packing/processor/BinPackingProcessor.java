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

    private LocalTime startOfDay = LocalTime.of(8, 0, 0);
    private LocalTime endOfDay = LocalTime.of(18, 0, 0);
    private LocalTime startOfLunchBreak = LocalTime.of(11, 30, 0);

    @Autowired
    ActivityRepository activityRepository;


    public void binPacking(List<Activity> activities, Duration size, int n) {

        List<Team> teams = Arrays.asList(new Team(startOfDay, startOfDay, endOfDay, startOfLunchBreak, Duration.ofMinutes(0)),
                new Team(startOfDay, startOfDay, endOfDay, startOfLunchBreak, Duration.ofMinutes(0)));

        for (Team team : teams) {

            for (Activity activity : new ArrayList<Activity>(activities)) {

                System.out.println("COMPARE: " + startOfDay.plus(team.getActivitiesTotalTime()).compareTo(endOfDay));
                System.out.println("CURRENT TIME: " + team.getCurrentTime());
                System.out.println("ACTIVITY: " + activity.getLength().toString());

                if (!(startOfDay.plus(team.getActivitiesTotalTime()).compareTo(endOfDay) == -1)) {
                    break;
                }

                if (team.isLunchTime()) {
                    System.out.println("CURRENT TIME 2: " + team.getCurrentTime());
                    team.addActivity(new Activity("Lunch Break", Duration.ofMinutes(60), ActivityType.LUNCH));
                }

                if (team.isEndOfDay()) {
                    team.addActivity(new Activity("Staff Motivation Presentation", Duration.ofMinutes(60),
                            ActivityType.MOTIVATION));
                }



                if (!team.isEndOfDay()) {
                    team.addActivity(activity);
                }
                activities.remove(activity);
            }
        }

        System.out.println(activities);

        System.out.println("TEAM 1: " + teams.get(0).getActivities());
        System.out.println("TEAM 1: " + teams.get(0).getActivitiesTotalTime());
        System.out.println("TEAM 2: " + teams.get(1).getActivities());
        System.out.println("TEAM 2: " + teams.get(1).getActivitiesTotalTime());

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
