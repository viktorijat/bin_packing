package com.bin.packing.model;

public enum ActivityType {

    NORMAL(""),
    LUNCH("Lunch Break"),
    MOTIVATION("Staff Motivation Presentation");


    ActivityType(String type) {
        this.type = type;
    }

    String type;
}
