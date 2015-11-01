package com.bo0tzz.topkekbot;

import pro.zackpollard.telegrambot.api.TelegramBot;
import pro.zackpollard.telegrambot.api.chat.Chat;
import pro.zackpollard.telegrambot.api.chat.message.send.SendableTextMessage;

/**
 * Created by bo0tzz
 */
public class TopKekBot {

    private final TelegramBot bot;

    private final Tweeter twitter;

    private TopKekBot(String authToken) {
        this.bot = TelegramBot.login(authToken);
        System.out.println("Bot logged in: " + this.bot.toString());
        this.twitter = Tweeter.getInstance(this.bot);
        System.out.println("Twitter API initialised");
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Missing auth token.");
            System.exit(0);
        }
        new TopKekBot(args[0]).run();
    }

    public void run() {
        new Thread(new Updater()).run();
        this.bot.getEventsManager().register(new TopKekListener(this.bot));
        this.bot.getEventsManager().register(new TopKekCommandListener(this.bot, this.twitter));
        System.out.println("Listener Registered");
        this.bot.startUpdates(false);
        System.out.println("Updates started.");

        Chat mazenchat = TelegramBot.getChat(-17349250);
        while (true) {
            String in = System.console().readLine();
            if ("quit".equals(in)) {
                break;
            }
            SendableTextMessage message = SendableTextMessage.builder().message(in).build();
            this.bot.sendMessage(mazenchat, message);
        }
    }
}
