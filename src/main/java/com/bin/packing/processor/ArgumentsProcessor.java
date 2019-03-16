package com.bin.packing.processor;

import com.bin.packing.loader.DataFactory;
import com.bin.packing.loader.DataImporter;
import com.bin.packing.model.Activity;
import com.bin.packing.repository.ActivityRepository;
import com.google.common.io.Files;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArgumentsProcessor {

    @Autowired
    private DataImporter dataImporter;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private BinPackingProcessor binPackingProcessor;

    public void processArguments(String[] args) throws IOException, ParseException {

        System.out.println(args.toString());
        if (args.length == 1) {
            String fileExtension = Files.getFileExtension(args[0]);
            DataFactory dataFactory = new DataFactory();
            DataImporter dataImporter = dataFactory.getImporter(fileExtension);
            if (dataImporter != null) {
                List<Activity> activities = dataImporter.loadActivities(args[0]);
                activityRepository.saveAll(activities);
            }
        }


        Iterable<Activity> all = activityRepository.findAll();
        List<Activity> myList = new ArrayList<>(((List<Activity>) all).size());
        all.forEach(myList::add);
        binPackingProcessor.binPacking(myList);
    }
}
