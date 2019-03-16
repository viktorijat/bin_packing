package com.bin.packing;

import com.bin.packing.processor.BinPackingProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableJpaRepositories
public class PackingApplication {

	private static BinPackingProcessor binPackingProcessor;

	@Autowired
	public PackingApplication(BinPackingProcessor binPackingProcessor) {
		PackingApplication.binPackingProcessor = binPackingProcessor;
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(PackingApplication.class, args);
		binPackingProcessor.calculate(args);

	}

}
