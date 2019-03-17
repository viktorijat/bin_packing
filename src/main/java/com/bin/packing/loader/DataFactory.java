package com.bin.packing.loader;

public abstract class DataFactory {

    public static DataImporter getImporter(String type) {
        DataImporter dataImporter = null;
        switch (type) {
            case "json":
                dataImporter = new JsonFileDataLoader();
                break;
            case "txt":
                dataImporter = new TextFileDataLoader();
                break;
            case "json_api":
                dataImporter = new JsonApiDataLoader();
                break;
        }
        return dataImporter;
    }
}