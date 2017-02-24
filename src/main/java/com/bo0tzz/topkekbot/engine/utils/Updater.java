package com.bo0tzz.topkekbot.engine.utils;

import java.io.File;

public class Updater implements Runnable {
    @Override
    public void run() {
        while (true) {
            File file = new File("update_available");
            if (file.exists()) {
                boolean res = file.delete();
                if (res)
                    System.exit(-1);
            }
            try {
                Thread.sleep(1000 * 30);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
