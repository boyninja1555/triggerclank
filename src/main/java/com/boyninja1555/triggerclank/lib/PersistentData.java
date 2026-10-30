package com.boyninja1555.triggerclank.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PersistentData {
    private static final String FILE_PATH = "bot.dpd";
    private static final Properties properties = new Properties();

    public static String get(String key) {
        return get(key, null);
    }

    public static String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static void set(String key, String value) {
        properties.setProperty(key, value);
        save();
    }

    public static void load() {
        var file = new File(FILE_PATH);
        if (!file.exists()) {
            save();
            return;
        }

        try (var in = new FileInputStream(file)) {
            properties.load(in);
        } catch (IOException ex) {
            ErrorHandler.error("Could not load database!", ex);
        }
    }

    public static void save() {
        try (var out = new FileOutputStream(FILE_PATH)) {
            properties.store(out, "Triggerclank Bot Storage");
        } catch (IOException ex) {
            ErrorHandler.error("Could not save database!", ex);
        }
    }
}
