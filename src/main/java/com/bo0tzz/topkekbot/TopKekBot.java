package com.bo0tzz.topkekbot;

import pro.zackpollard.telegrambot.api.TelegramBot;
import pro.zackpollard.telegrambot.api.chat.Chat;
import pro.zackpollard.telegrambot.api.chat.message.send.SendableTextMessage;

/**
 * Created by bo0tzz
 */
public class TopKekBot {

    public static TelegramBot bot;
    public static Tweeter twitter;

    public static void main(String[] args) {
        bot = TelegramBot.login(args[0]);
        System.out.println("Bot logged in: " + bot.toString());
        bot.getEventsManager().register(new TopKekListener(bot));
        bot.getEventsManager().register(new TopKekCommandListener(bot));
        System.out.println("Listener Registered");
        twitter = Tweeter.getInstance();
        System.out.println("Twitter API initialised");
        bot.startUpdates(false);
        System.out.println("Updates started.");

        Chat mazenchat = TelegramBot.getChat(-17349250);
        while (true) {
            SendableTextMessage message = SendableTextMessage.builder().message(System.console().readLine()).build();
            bot.sendMessage(mazenchat, message);
        }
    }
}
