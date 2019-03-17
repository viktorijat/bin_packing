package com.bin.packing.controller;

import com.bin.packing.model.Team;
import com.bin.packing.service.ActivityService;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping
    public List<Team> addActivities(HttpEntity<String> httpEntity) throws IOException, ParseException {
        return activityService.addActivities(httpEntity.getBody());
    }

}
