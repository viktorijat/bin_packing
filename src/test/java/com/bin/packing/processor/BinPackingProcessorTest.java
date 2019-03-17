package com.bin.packing.processor;

import com.bin.packing.model.Activity;
import com.bin.packing.model.Team;
import com.bin.packing.repository.ActivityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BinPackingProcessorTest {

    @Mock
    private ActivityRepository activityRepository;

    @InjectMocks
    private BinPackingProcessor binPackingProcessor;

    @Test
    public void shouldPackActivities() {

        Iterable<Activity> activities = createActivities();
        when(activityRepository.findAll()).thenReturn(activities);
        List<Team> teams = binPackingProcessor.callBinPacking();

        assertThat(teams.size(), is(2));
        assertThat(teams.get(0).getActivities().size(), is(12));
        assertThat(teams.get(1).getActivities().size(), is(12));
        assertThat(teams.get(0).getActivities().get(0).getName(), is("Duck Herding"));
        assertThat(teams.get(1).getActivities().get(0).getName(), is("Giant Digital Graffiti"));
        assertThat(teams.get(0).getActivitiesTotalTime(), is(Duration.parse("PT8H45M")));
        assertThat(teams.get(1).getActivitiesTotalTime(), is(Duration.parse("PT8H45M")));
    }

    private Iterable<Activity> createActivities() {

        return Arrays.asList(
                new Activity("Duck Herding", Duration.ofMinutes(60)),
                new Activity("Archery", Duration.ofMinutes(45)),
                new Activity("Learning Magic Tricks", Duration.ofMinutes(40)),
                new Activity("Laser Clay Shooting", Duration.ofMinutes(50)),
                new Activity("Human Table Football", Duration.ofMinutes(30)),
                new Activity("Buggy Driving", Duration.ofMinutes(30)),
                new Activity("Salsa & Pickles", Duration.ofMinutes(15)),
                new Activity("2-wheeled Segways", Duration.ofMinutes(45)),
                new Activity("Viking Axe Throwing", Duration.ofMinutes(60)),
                new Activity("Giant Puzzle Dinosaurs", Duration.ofMinutes(30)),
                new Activity("Giant Digital Graffiti", Duration.ofMinutes(45)),
                new Activity("Cricket 2020", Duration.ofMinutes(60)),
                new Activity("Wine Tasting", Duration.ofMinutes(15)),
                new Activity("Arduino Bonanza", Duration.ofMinutes(30)),
                new Activity("Digital Tresure Hunt", Duration.ofMinutes(60)),
                new Activity("Enigma Challenge", Duration.ofMinutes(45)),
                new Activity("Monti Carlo or Bust", Duration.ofMinutes(60)),
                new Activity("New Zealand Haka", Duration.ofMinutes(30)),
                new Activity("Time Tracker", Duration.ofMinutes(15)),
                new Activity("Indiano Drizzle", Duration.ofMinutes(45))
        );
    }
}