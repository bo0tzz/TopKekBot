package com.bo0tzz.topkekbot.engine.utils;

import com.bo0tzz.topkekbot.TopKekBot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by amir on 2/23/17.
 */
public class Updater implements Runnable {
    private final TopKekBot instance;

    public Updater(TopKekBot instance) {
        this.instance = instance;
    }


    @Override
    public void run() {
        int currentBuild = setupBuildFile();
    }

    private Integer setupBuildFile() {
        File file = new File("file");

        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                file.createNewFile();
                writer.write("-1");
                writer.flush();
                writer.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            if (lines == null || lines.isEmpty()) {
                return -1;
            }
            String line1 = lines.get(0);
            return Integer.valueOf(line1);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
