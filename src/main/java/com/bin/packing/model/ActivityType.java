package com.bin.packing.model;

public enum ActivityType {

    NORMAL("Normal"),
    LUNCH("Lunch Break"),
    MOTIVATION("Staff Motivation Presentation");


    ActivityType(String type) {
        this.type = type;
    }

    String type;
}
