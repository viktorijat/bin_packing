package com.bin.packing.loader;

public class DataFactory {

    public DataImporter getImporter(String fileType) {
        if (fileType.equals("json")) {
            return new JsonFileDataLoader();
        } else if (fileType.equals("txt")) {
            return new TextFileDataLoader();
        }
        return null;
    }
}
