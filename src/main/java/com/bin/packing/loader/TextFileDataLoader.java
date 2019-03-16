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

    private static final String SEPARATOR = " ";

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> loadActivities(String fileName) throws IOException {

        return new BufferedReader(new FileReader(fileName))
                .lines()
                .skip(1)
                .map(s -> {
                    Activity activity = createActivity(s);
                    return activityRepository.save(activity);
                })
                .collect(Collectors.toList());
    }

    private Activity createActivity(String activityString) {

        String[] values = activityString.split(SEPARATOR);
        return new Activity(StringUtils.join(ArrayUtils.remove(values, values.length - 1), SEPARATOR),
                createActivityDuration(values[values.length - 1]));

    }

    private Duration createActivityDuration(String time) {

        if (time.equals("sprint")) {
            return Duration.ofMinutes(15);
        }
        return Duration.ofMinutes(Integer.parseInt(time.replace("min", "")));
    }
}
