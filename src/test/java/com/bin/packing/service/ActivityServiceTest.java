package com.bin.packing.service;

import com.bin.packing.loader.DataFactory;
import com.bin.packing.loader.DataImporter;
import com.bin.packing.loader.JsonFileDataLoader;
import com.bin.packing.model.Activity;
import com.bin.packing.model.Team;
import com.bin.packing.processor.BinPackingProcessor;
import com.bin.packing.repository.ActivityRepository;
import net.minidev.json.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DataFactory.class, ActivityService.class})
public class ActivityServiceTest {

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private BinPackingProcessor binPackingProcessor;

    @Mock
    private DataImporter dataImporter;

    @Mock
    private JsonFileDataLoader jsonFileDataLoader;

    @InjectMocks
    private ActivityService activityService;

    @Test
    public void shouldAddActivitiesFromString() throws IOException, ParseException {

        List<Activity> activities = Arrays.asList(new Activity(), new Activity());
        PowerMockito.mockStatic(DataFactory.class);
        PowerMockito.when(DataFactory.getImporter(anyString())).thenReturn(dataImporter);
        when(dataImporter.loadActivities(anyString())).thenReturn(activities);
        List<Team> teams = Arrays.asList(new Team(), new Team());
        when(binPackingProcessor.callBinPacking()).thenReturn(teams);
        assertThat(activityService.addActivities(""), is(teams));
    }
}