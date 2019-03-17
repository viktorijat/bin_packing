package com.bin.packing.controller;

import com.bin.packing.model.Team;
import com.bin.packing.service.ActivityService;
import net.minidev.json.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityControllerTest {

    @Mock
    private ActivityService activityService;

    @InjectMocks
    private ActivityController activityController;

    @Test
    public void shouldReturnTeams() throws IOException, ParseException {

        List<Team> teams = Arrays.asList(new Team(), new Team());

        when(activityService.addActivities(anyString())).thenReturn(teams);
        HttpEntity<String> httpEntity = new HttpEntity<>("");
        assertThat(activityController.addActivities(httpEntity), is(teams));

    }

}