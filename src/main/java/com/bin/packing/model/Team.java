package com.bin.packing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team", uniqueConstraints = {@UniqueConstraint(columnNames = "teamId")})
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @OneToMany(mappedBy = "team")
    private List<Activity> activities;

    @JsonIgnore
    private LocalTime startOfDay;

    @JsonIgnore
    private LocalTime currentTime;

    @JsonIgnore
    private LocalTime endOfDay;

    @JsonIgnore
    private LocalTime startOfLunchBreak;

    private LocalTime getStartOfLunchBreak() {
        return startOfLunchBreak;
    }

    @JsonIgnore
    private Duration activitiesTotalTime;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Team() {
    }

    public Team(Long teamId, LocalTime startOfDay, LocalTime currentTime, LocalTime endOfDay, LocalTime startOfLunchBreak, Duration activitiesTotalTime) {
        this.teamId = teamId;
        this.startOfDay = startOfDay;
        this.currentTime = currentTime;
        this.endOfDay = endOfDay;
        this.startOfLunchBreak = startOfLunchBreak;
        this.activitiesTotalTime = activitiesTotalTime;
    }

    public void addActivity(Activity activity) {
        List<Activity> activities = this.getActivities();
        if (activities == null) {
            activities = new ArrayList<>();
        }
        activities.add(activity);
        this.setCurrentTime(this.getCurrentTime().plus(activity.getLength()));
        this.setActivitiesTotalTime(this.getActivitiesTotalTime().plus(activity.getLength()));
        this.setActivities(activities);
    }

    public boolean isLunchTime() {
        return startOfLunchBreak.isBefore(this.currentTime) || startOfLunchBreak.plusHours(1).isAfter(this.currentTime);
    }

    public Duration getActivitiesTotalTime() {
        return activitiesTotalTime;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    private void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    private void setActivitiesTotalTime(Duration activitiesTotalTime) {
        this.activitiesTotalTime = activitiesTotalTime;
    }

    private LocalTime getEndOfDay() {
        return endOfDay;
    }

    public LocalTime getCurrentTime() {
        return currentTime;
    }

    private void setCurrentTime(LocalTime currentTime) {
        this.currentTime = currentTime;
    }

    public boolean isBeforeLunchBreak(Activity activity) {
        return this.getCurrentTime().plus(activity.getLength()).isBefore(this.getStartOfLunchBreak());
    }

    public boolean isAfterLunchBreakAndBeforeEndOfDay(Activity activity) {
        return this.getCurrentTime().plus(activity.getLength()).isAfter(this.getStartOfLunchBreak().plusMinutes(30)) &&
                isBeforeEndOfDay(activity);
    }

    private boolean isBeforeEndOfDay(Activity activity) {
        return this.getCurrentTime().plus(activity.getLength()).isBefore(this.getEndOfDay());
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", activities=" + activities +
                ", activitiesTotalTime=" + activitiesTotalTime +
                '}';
    }
}
