package com.bo0tzz.topkekbot.engine.utils;

import java.io.File;

public class Updater implements Runnable {
    @Override
    public void run() {
        while (true) {
            File file = new File("update_available");
            if (file.exists()) {
                System.out.println("File existed. Attempting to restart!");
                boolean res = file.delete();
                if (res)
                    System.exit(0);
            }
            try {
                Thread.sleep(1000 * 5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
