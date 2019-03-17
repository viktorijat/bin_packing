package com.bin.packing.loader;

import com.bin.packing.model.Activity;
import net.minidev.json.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface DataImporter {
    List<Activity> loadActivities(String fileName) throws IOException, ParseException;
}
