package com.bin.packing.processor;

import com.bin.packing.loader.DataFactory;
import com.bin.packing.loader.DataImporter;
import com.bin.packing.model.Activity;
import com.bin.packing.model.Team;
import com.bin.packing.service.ActivityService;
import com.google.common.io.Files;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ArgumentsProcessor {

    @Autowired
    private ActivityService activityService;

    private final static Logger LOGGER = Logger.getLogger(ArgumentsProcessor.class.getName());


    public void processArguments(String[] args) throws IOException, ParseException {

        if (args.length == 1) {
            String fileExtension = Files.getFileExtension(args[0]);
            DataImporter dataImporter = DataFactory.getImporter(fileExtension);
            if (dataImporter != null) {
                List<Activity> activities = dataImporter.loadActivities(args[0]);

                List<Team> teams = activityService.addActivities(activities);
                printActivities(teams);
            }
        } else {
            LOGGER.info("You called the program with no arguments, you can add activities with the " +
                    "api endpoint");
        }
    }

    public void printActivities(List<Team> teams) {
        for (Team team : teams) {
            LOGGER.info("TEAM " + team.getTeamId());
            for (Activity activity : team.getActivities()) {
                LOGGER.info(activity.toString());
            }
        }
    }
}