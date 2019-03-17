package com.bin.packing.loader;

import com.bin.packing.model.Activity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityCreatorTest {

    @Test
    public void shouldCreateActivityFromStringMin() {
        String activityString = "Duck Herding 60min";
        Activity activity = new Activity("Duck Herding", Duration.ofMinutes(60));

        Activity activityReceived = ActivityCreator.createActivity(activityString);
        assertThat(activityReceived.getName(), is(activity.getName()));
        assertThat(activityReceived.getLength(), is(activity.getLength()));
    }

    @Test
    public void shouldCreateActivityFromStringSprint() {
        String activityString = "Time Tracker sprint";
        Activity activity = new Activity("Time Tracker", Duration.ofMinutes(15));

        Activity activityReceived = ActivityCreator.createActivity(activityString);
        assertThat(activityReceived.getName(), is(activity.getName()));
        assertThat(activityReceived.getLength(), is(activity.getLength()));
    }

    @Test
    public void shouldCreateActivityFromStringMin2() {
        Activity activity = new Activity("Duck Herding", Duration.ofMinutes(60));

        Activity activityReceived = ActivityCreator.createActivity(activity.getName(), "60min");
        assertThat(activityReceived.getName(), is(activity.getName()));
        assertThat(activityReceived.getLength(), is(activity.getLength()));
    }

    @Test
    public void shouldCreateActivityFromStringSprint2() {

        Activity activity = new Activity("Time Tracker", Duration.ofMinutes(15));

        Activity activityReceived = ActivityCreator.createActivity(activity.getName(), "sprint");
        assertThat(activityReceived.getName(), is(activity.getName()));
        assertThat(activityReceived.getLength(), is(activity.getLength()));
    }
}