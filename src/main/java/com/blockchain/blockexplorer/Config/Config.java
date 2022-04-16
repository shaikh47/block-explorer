package com.blockchain.blockexplorer.Config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class Config {
    Properties properties;

    public Config(String path) throws FileNotFoundException, IOException {
        properties = new Properties();

        InputStream inp = null;

        try {
            inp = new FileInputStream(path);
            properties.load(inp);
        } catch (FileNotFoundException e) {
            properties.load(getClass().getClassLoader().getResourceAsStream(path));
        }
    }

    public void load() throws IllegalArgumentException, IllegalAccessException {
        for (Field field : this.getClass().getFields()) {
            field.set(this, properties.get(field.getName()));
            Object val = properties.get(field.getName());
            if (val != null) {
                field.set(this, val);
                System.out.println(field.getName() + "=" + properties.get(field.getName()));
            }
        }
    }
    public static String ETHEREUM_URL;
//    public static String CONTRACT_ADDRESS_SCOPE1;
//    public static String CONTRACT_ADDRESS_SCOPE2;
}
