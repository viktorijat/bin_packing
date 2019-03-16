package com.bin.packing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.apache.commons.lang3.time.DurationFormatUtils;


@Entity
@Table(name = "activity", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Activity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Duration length;

    private LocalTime startTime;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="teamId", nullable=true)
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Duration getLength() {
        return length;
    }

    public void setLength(Duration length) {
        this.length = length;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Activity() {
    }

    public Activity(String name, Duration length) {
        this.name = name;
        this.length = length;
    }

    public Activity(String name, Duration length, LocalTime startTime) {
        this.name = name;
        this.length = length;
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return startTime + " : " + name + " : " + length.toMinutes();
    }
}
