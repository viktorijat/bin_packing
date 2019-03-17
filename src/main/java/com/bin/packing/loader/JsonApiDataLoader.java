package com.bin.packing.loader;

import com.bin.packing.model.Activity;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JsonApiDataLoader implements DataImporter {
    @Override
    public List<Activity> loadActivities(String body) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(body);
        List<Activity> activities = new ArrayList<>();
        for (Object o : jsonArray) {
            JSONObject activity = (JSONObject) o;
            activities.add(ActivityCreator.createActivity((String) activity.get("name"),
                    (String) activity.get("length")));
        }
        return activities;
    }
}
