package com.bo0tzz.topkekbot;

import pro.zackpollard.telegrambot.api.TelegramBot;
import pro.zackpollard.telegrambot.api.event.Listener;
import pro.zackpollard.telegrambot.api.event.chat.message.TextMessageReceivedEvent;

/**
 * Created by bo0tzz
 */
public class TopKekListener implements Listener {

    private final TelegramBot bot;

    public TopKekListener(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void onTextMessageReceived(TextMessageReceivedEvent event) {
        String message = event.getContent().getContent();

        if (message.toLowerCase().contains("pleb")) {
            event.getChat().sendMessage("Pleb, yes.", bot);
        } else if (message.equalsIgnoreCase("thank mr bot")) {
            event.getChat().sendMessage("may good cpus and dedotated wams come to you", bot);
        }
    }
}
