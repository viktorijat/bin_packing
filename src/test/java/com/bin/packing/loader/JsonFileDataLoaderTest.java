package com.bin.packing.loader;

import com.bin.packing.model.Activity;
import net.minidev.json.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ActivityCreator.class, JsonFileDataLoaderTest.class})
public class JsonFileDataLoaderTest {

    @InjectMocks
    JsonFileDataLoader jsonFileDataLoader;

    @Test
    public void shouldListActivities() throws ParseException, IOException {

        PowerMockito.mockStatic(ActivityCreator.class);
        Activity testActivity = new Activity();
        PowerMockito.when(ActivityCreator.createActivity(anyString(), anyString())).thenReturn(testActivity);
        List<Activity> activities = jsonFileDataLoader.loadActivities("activities.json");
        assertThat(activities.size(), is(20));
    }
}