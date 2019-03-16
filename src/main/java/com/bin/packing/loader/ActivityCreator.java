package com.bin.packing.loader;

import com.bin.packing.model.Activity;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;

public class ActivityCreator {

    private static final String SEPARATOR = " ";

    public Activity createActivity(String activityString) {

        String[] values = activityString.split(SEPARATOR);
        return new Activity(StringUtils.join(ArrayUtils.remove(values, values.length - 1), SEPARATOR),
                createActivityDuration(values[values.length - 1]));

    }

    public Activity createActivity(String activityString, String timeString) {
        return new Activity(activityString, createActivityDuration(timeString));

    }

    private Duration createActivityDuration(String time) {

        if (time.equals("sprint")) {
            return Duration.ofMinutes(15);
        }
        return Duration.ofMinutes(Integer.parseInt(time.replace("min", "")));
    }
}
