package com.bin.packing.model;

import java.time.Duration;
import javax.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Table(name = "activity", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Activity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Duration length;

    private ActivityType type;

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

    public Activity() {}

    public Activity(String name, Duration length) {
        this.name = name;
        this.length = length;
    }
}
