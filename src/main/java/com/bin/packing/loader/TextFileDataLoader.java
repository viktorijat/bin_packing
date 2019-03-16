package com.bin.packing.loader;

import com.bin.packing.model.Activity;
import com.bin.packing.repository.ActivityRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Service
public class TextFileDataLoader implements DataImporter {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityCreator activityCreator;

    public List<Activity> loadActivities(String fileName) throws IOException {

        return new BufferedReader(new FileReader(fileName))
                .lines()
                .skip(1)
                .map(a -> activityCreator.createActivity(a))
                .collect(Collectors.toList());
    }
}
