package com.bin.packing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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

    public LocalTime getStartOfLunchBreak() {
        return startOfLunchBreak;
    }

    public void setStartOfLunchBreak(LocalTime startOfLunchBreak) {
        this.startOfLunchBreak = startOfLunchBreak;
    }

    @JsonIgnore
    private Duration activitiesTotalTime;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Team() {}

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
        System.out.println(startOfLunchBreak + " - " + currentTime);
        return startOfLunchBreak.isBefore(this.currentTime) || startOfLunchBreak.plusHours(1).isAfter(this.currentTime);
    }

    public boolean isEndOfDay() {
        return endOfDay.isBefore(startOfDay.plus(activitiesTotalTime));
    }

    public LocalTime getLocalDateTime() {
        return startOfLunchBreak;
    }

    public Duration getActivitiesTotalTime() {
        return activitiesTotalTime;
    }

    public void setLocalDateTime(LocalTime startOfLunchBreak) {
        this.startOfLunchBreak = startOfLunchBreak;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void setActivitiesTotalTime(Duration activitiesTotalTime) {
        this.activitiesTotalTime = activitiesTotalTime;
    }

    public LocalTime getEndOfDay() {
        return endOfDay;
    }

    public void setEndOfDay(LocalTime endOfDay) {
        this.endOfDay = endOfDay;
    }

    public LocalTime getStartOfDay() {
        return startOfDay;
    }

    public void setStartOfDay(LocalTime startOfDay) {
        this.startOfDay = startOfDay;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", activities=" + activities +
                ", activitiesTotalTime=" + activitiesTotalTime +
                '}';
    }

    public LocalTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalTime currentTime) {
        this.currentTime = currentTime;
    }

    public boolean isBeforeLunchBreak(Activity activity) {
        return this.getCurrentTime().plus(activity.getLength()).isBefore(this.getStartOfLunchBreak());
    }

    public boolean isBeforeEndOfDay(Activity activity) {
        return this.getCurrentTime().plus(activity.getLength()).isBefore(this.getEndOfDay());
    }

    public void isViableActivityTime(Activity activity) {
        currentTime.plus(activity.getLength()).isBefore(this.getStartOfLunchBreak());
    }
}
