package com.bo0tzz.topkekbot;

import org.apache.commons.io.FileUtils;
import pro.zackpollard.telegrambot.api.TelegramBot;
import pro.zackpollard.telegrambot.api.chat.Chat;
import pro.zackpollard.telegrambot.api.chat.message.send.SendableTextMessage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bo0tzz
 */
public class TopKekBot {

    private final TelegramBot bot;
    private final Map<Long, String> lastCommand;

    private static TopKekBot instance;

    private TopKekBot(String authToken) {
        instance = this;
        this.bot = TelegramBot.login(authToken);
        this.lastCommand = new HashMap<>();
        System.out.println("Bot logged in: " + this.bot.toString());
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Missing auth token.");
            System.exit(0);
        }
        new TopKekBot(args[0]).run();
    }

    public void run() {
        new Thread(new Updater(this)).start();
        this.bot.getEventsManager().register(new TopKekListener(this.bot));
        this.bot.getEventsManager().register(new TopKekCommandListener(this.bot));
        System.out.println("Listener Registered");
        this.bot.startUpdates(false);
        System.out.println("Updates started.");

        Chat mazenchat = bot.getChat(-1001000055116L);
        while (true) {
            String in = System.console().readLine();
            if ("quit".equals(in)) {
                break;
            }
            SendableTextMessage message = SendableTextMessage.builder().message(in).build();
            this.bot.sendMessage(mazenchat, message);
        }
    }

    public void sendToMazen(String message) {
        bot.getChat(-1001000055116L).sendMessage(message);
    }

    public Map<Long, String> getLastCommand() {

        return lastCommand;
    }

    public static TopKekBot getInstance() {

        return instance;
    }

    public static String getGoogleKey() {
        try {
            String key = FileUtils.readFileToString(new File("gkey"));
            return key;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
     }
}
