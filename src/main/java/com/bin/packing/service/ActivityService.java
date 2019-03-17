package com.bin.packing.service;

import com.bin.packing.loader.DataFactory;
import com.bin.packing.loader.DataImporter;
import com.bin.packing.model.Activity;
import com.bin.packing.model.Team;
import com.bin.packing.processor.BinPackingProcessor;
import com.bin.packing.repository.ActivityRepository;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private BinPackingProcessor binPackingProcessor;

    public List<Team> addActivities(List<Activity> activities) {
        activityRepository.saveAll(activities);
        return binPackingProcessor.callBinPacking();
    }

    public List<Team> addActivities(String body) throws IOException, ParseException {
        DataImporter dataImporter = DataFactory.getImporter("json_api");
        List<Activity> activities = dataImporter.loadActivities(body);
        return addActivities(activities);
    }
}
