package com.bin.packing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalTime;


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
    @JoinColumn(name = "teamId")
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
        return name + " : " + length.toMinutes();
    }
}
