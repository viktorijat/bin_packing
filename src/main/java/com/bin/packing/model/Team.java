package com.bin.packing.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "team", uniqueConstraints = {@UniqueConstraint(columnNames = "competition_id")})
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @OneToMany(mappedBy = "activity")
    private List<Activity> activitiesBeforeLunch;

    @OneToMany(mappedBy = "activity")
    private List<Activity> activitiesAfterLunch;

    public Team() {}
}
