package com.bo0tzz.topkekbot;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class Configuration {

    private final String telegramKey;
    private final String googleKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);

    public Configuration(String[] args) {

        this.telegramKey = getTelegramKey(args);
        this.googleKey = getGoogleKey(args);

    }

    private String getTelegramKey(String[] args) {
        String apiKey = System.getenv("BOT_KEY");
        if (apiKey == null || apiKey.equals("")) {
            if (args.length < 1) {
                LOGGER.error("Missing auth token");
                System.exit(0);
            }
            apiKey = args[0];
        }
        return apiKey;
    }

    private String getGoogleKey(String[] args) {
        String key = System.getenv("GOOGLE_KEY");
        if (key == null || key.equals("")) {
            if (args.length > 2) {
                key = args[1];
            } else {
                File f = new File("gkey");
                if (f.exists()) {
                    try {
                        key = FileUtils.readFileToString(f);
                    } catch (IOException e) {
                        LOGGER.error("Could not find google key!");
                        TopKekBot.handleError(e);
                    }
                }
            }
        }
        return key;
    }

    public String getTelegramKey() {
        return telegramKey;
    }

    public String getGoogleKey() {
        return googleKey;
    }
}
