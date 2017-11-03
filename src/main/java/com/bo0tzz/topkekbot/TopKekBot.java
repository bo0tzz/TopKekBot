package com.bo0tzz.topkekbot;

import com.bo0tzz.topkekbot.engine.TopKekCommandListener;
import com.bo0tzz.topkekbot.engine.TopKekListener;
import com.bo0tzz.topkekbot.engine.utils.Updater;
import org.apache.commons.io.FileUtils;
import pro.zackpollard.telegrambot.api.TelegramBot;
import pro.zackpollard.telegrambot.api.chat.Chat;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by bo0tzz
 */
public class TopKekBot {

    private static TopKekBot instance;
    private final TelegramBot bot;
    private final Map<Long, String> lastCommand;

    private TopKekBot(String authToken) {
        instance = this;
        this.bot = TelegramBot.login(authToken);
        this.lastCommand = new HashMap<>();
        System.out.println("Bot logged in: " + this.bot.toString());
    }

    public static void main(String[] args) {
        String key = System.getenv("BOT_KEY");
        if (key == null || key.equals("")) {
            if (args.length < 1) {
                System.out.println("Missing auth token.");
                System.exit(0);
            }
            key = args[0];
        }
        new TopKekBot(key).run();
    }

    public static TopKekBot getInstance() {

        return instance;
    }

    public static String getGoogleKey() {
        String key = System.getenv("GOOGLE_KEY");
        if (key == null || key.equals("")) {
            try {
                key = FileUtils.readFileToString(new File("gkey"));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return key;
    }

    private void run() {
        new Thread(new Updater()).start();
        this.bot.getEventsManager().register(new TopKekListener(this.bot));
        this.bot.getEventsManager().register(new TopKekCommandListener(this.bot));
        System.out.println("Listener Registered");
        this.bot.startUpdates(false);
        System.out.println("Updates started.");
        this.sendToMazen("Bot just updated!\n\nOr maybe it just died and restarted.\n\nIt probably just died...");

        Chat mazenchat = bot.getChat(-1001000055116L);
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String in = scanner.nextLine();
                if ("quit".equals(in)) {
                    break;
                }
                if (mazenchat != null) {
                    mazenchat.sendMessage(in);
                } else {
                    System.out.println("couldn't find mazen's chat :(");
                }
            }
        }
    }

    public void sendToMazen(String message) {
        Chat chat = bot.getChat(-1001000055116L);
        if (chat != null)
            chat.sendMessage(message);
    }

    public Map<Long, String> getLastCommand() {

        return lastCommand;
    }
}
