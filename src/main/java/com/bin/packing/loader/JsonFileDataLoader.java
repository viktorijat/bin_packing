package com.bin.packing.loader;

import com.bin.packing.model.Activity;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonFileDataLoader implements DataImporter {

    @Override
    public List<Activity> loadActivities(String fileName) throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(fileName));
        List<Activity> activities = new ArrayList<>();
        for (Object o : jsonArray) {
            JSONObject activity = (JSONObject) o;
            activities.add(ActivityCreator.createActivity((String) activity.get("name"),
                    (String) activity.get("time")));
        }
        return activities;
    }
}