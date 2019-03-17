package com.bin.packing.loader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataFactoryTest {


    @Test
    public void shouldGetImporterJson() {
        DataImporter dataImporter = DataFactory.getImporter("json");
        assertThat(dataImporter.getClass().getName(), is(JsonFileDataLoader.class.getName()));
    }

    @Test
    public void shouldGetImporterTxt() {
        DataImporter dataImporter = DataFactory.getImporter("txt");
        assertThat(dataImporter.getClass().getName(), is(TextFileDataLoader.class.getName()));
    }

    @Test
    public void shouldGetImporterJsonApi() {
        DataImporter dataImporter = DataFactory.getImporter("json_api");
        assertThat(dataImporter.getClass().getName(), is(JsonApiDataLoader.class.getName()));
    }

}