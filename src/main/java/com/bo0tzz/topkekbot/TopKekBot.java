package com.bo0tzz.topkekbot;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.TelegramBotRegistry;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.update.PollingUpdateProvider;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bo0tzz
 */
public class TopKekBot {

    private static TopKekBot instance;

    private Map<Long, String> lastCommand;

    private TelegramBot bot;
    private final Configuration configuration;

    private static final Logger LOGGER = LoggerFactory.getLogger(TopKekBot.class);

    private TopKekBot(String[] args) {
        this.configuration = new Configuration(args);

        TelegramBotRegistry registry = TelegramBotRegistry.builder()
                .updateProvider(new PollingUpdateProvider())
                .eventThreadCount(10)
                .build();

        registry.registerBot(configuration.getTelegramKey(), this::setupTelegramBot);

        this.lastCommand = new HashMap<>();

    }

    public static void main(String[] args) {
        TopKekBot.instance = new TopKekBot(args);
    }

    private void setupTelegramBot(com.jtelegram.api.TelegramBot telegramBot, TelegramException error) {

        if (error != null) {
            TopKekBot.handleFatalError(error);
        }

        this.bot = telegramBot;

        //TODO register handlers

    }

    public static void handleFatalError(Exception ex) {
        LOGGER.error("Fatal error occurred!", ex);
        System.exit(1);
    }

    public static void handleError(Exception ex) {
        LOGGER.warn("Error occurred!", ex);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public Map<Long, String> getLastCommand() {
        return lastCommand;
    }
}
