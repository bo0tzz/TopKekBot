package com.bo0tzz.topkekbot;

import com.bo0tzz.topkekbot.engine.TopKekCommandListener;
import com.bo0tzz.topkekbot.engine.TopKekListener;
import com.bo0tzz.topkekbot.engine.Tweeter;
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
        log("Bot logged in: %s", bot.toString());
        bot.getEventsManager().register(new TopKekListener(bot));
        bot.getEventsManager().register(new TopKekCommandListener(bot));
        log("Listener Registered");
        twitter = Tweeter.getInstance();
        log("Twitter API initialised");
        // Specify this if you want to do updates or not
        boolean doUpdates = false;

        if (doUpdates) {
            log("Updates started");
            bot.startUpdates(true);
            log("Updates finished");
        } else {
            bot.startUpdates(false);
            log("Updates skipped.");
        }


        Chat mazenChat = TelegramBot.getChat(-17349250);
        while (true) {
            SendableTextMessage message = SendableTextMessage.builder().message(System.console().readLine()).build();
            bot.sendMessage(mazenChat, message);
        }
    }

    public static void log(String s, Object... args) {
        String resultingString = String.format(s, args);
        System.out.println(resultingString);
    }
}
