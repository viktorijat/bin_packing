package com.bin.packing.loader;

import com.bin.packing.model.Activity;
import net.minidev.json.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ActivityCreator.class, JsonApiDataLoader.class})
public class JsonApiDataLoaderTest {

    @InjectMocks
    JsonApiDataLoader jsonApiDataLoader;

    @Test
    public void shouldListActivities() throws ParseException {

        PowerMockito.mockStatic(ActivityCreator.class);
        Activity testActivity = new Activity();
        PowerMockito.when(ActivityCreator.createActivity(anyString(), anyString())).thenReturn(testActivity);
        List<Activity> activities = jsonApiDataLoader.loadActivities("[{'name': 'Duck Herding', 'length': '60min'}]");
        assertThat(activities.size(), is(1));
    }
}