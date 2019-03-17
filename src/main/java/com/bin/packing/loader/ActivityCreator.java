package com.bin.packing.loader;

import com.bin.packing.model.Activity;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;

class ActivityCreator {

    private static final String SEPARATOR = " ";

    static Activity createActivity(String activityString) {

        String[] values = activityString.split(SEPARATOR);
        return createActivity(StringUtils.join(ArrayUtils.remove(values, values.length - 1), SEPARATOR),
                values[values.length - 1]);
    }

    static Activity createActivity(String activityString, String timeString) {
        return new Activity(activityString, createActivityDuration(timeString));
    }

    private static Duration createActivityDuration(String time) {
        if (time.equals("sprint")) {
            return Duration.ofMinutes(15);
        }
        return Duration.ofMinutes(Integer.parseInt(time.replace("min", "")));
    }
}
