package com.bin.packing;

import com.bin.packing.processor.ArgumentsProcessor;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableJpaRepositories
public class PackingApplication {

    private static ArgumentsProcessor argumentsProcessor;

    @Autowired
    public PackingApplication(ArgumentsProcessor argumentsProcessor) {
        PackingApplication.argumentsProcessor = argumentsProcessor;
    }

    public static void main(String[] args) throws IOException, ParseException {
        SpringApplication.run(PackingApplication.class, args);
        argumentsProcessor.processArguments(args);

    }

}
