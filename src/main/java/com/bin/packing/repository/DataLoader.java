package com.bin.packing.repository;

import com.bin.packing.model.Activity;
import com.bin.packing.model.ActivityType;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    ActivityRepository activityRepository;

    @Override
    public void run(ApplicationArguments args) {
        activityRepository.save(new Activity("Duck Herding", Duration.ofMinutes(60)));
        activityRepository.save(new Activity("Archery", Duration.ofMinutes(45)));
        activityRepository.save(new Activity("Learning Magic Tricks", Duration.ofMinutes(40)));
        activityRepository.save(new Activity("Laser Clay Shooting", Duration.ofMinutes(60)));
        activityRepository.save(new Activity("Human Table Football", Duration.ofMinutes(30)));
        activityRepository.save(new Activity("Buggy Driving", Duration.ofMinutes(30)));
        activityRepository.save(new Activity("Salsa & Pickles", Duration.ofMinutes(15)));
        activityRepository.save(new Activity("2-wheeled Segways", Duration.ofMinutes(45)));
        activityRepository.save(new Activity("Viking Axe Throwing", Duration.ofMinutes(60)));
        activityRepository.save(new Activity("Giant Puzzle Dinosaurs", Duration.ofMinutes(30)));
        activityRepository.save(new Activity("Giant Digital Graffiti", Duration.ofMinutes(60)));
        activityRepository.save(new Activity("Cricket 2020", Duration.ofMinutes(60)));
        activityRepository.save(new Activity("Wine Tasting", Duration.ofMinutes(15)));
        activityRepository.save(new Activity("Arduino Bonanza", Duration.ofMinutes(30)));
        activityRepository.save(new Activity("Digital Tresure Hunt", Duration.ofMinutes(60)));
        activityRepository.save(new Activity("Enigma Challenge", Duration.ofMinutes(45)));
        activityRepository.save(new Activity("Monti Carlo or Bust", Duration.ofMinutes(60)));
        activityRepository.save(new Activity("New Zealand Haka", Duration.ofMinutes(30)));
        activityRepository.save(new Activity("Time Tracker", Duration.ofMinutes(15)));
        activityRepository.save(new Activity("Indiano Drizzle", Duration.ofMinutes(45)));
    }

    @Autowired
    public DataLoader(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }
}
