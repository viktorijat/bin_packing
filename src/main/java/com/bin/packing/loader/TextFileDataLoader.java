package com.bin.packing.loader;

import com.bin.packing.model.Activity;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TextFileDataLoader implements DataImporter {

    public List<Activity> loadActivities(String fileName) throws IOException {

        return new BufferedReader(new FileReader(fileName))
                .lines()
                .skip(1)
                .map(ActivityCreator::createActivity)
                .collect(Collectors.toList());
    }
}