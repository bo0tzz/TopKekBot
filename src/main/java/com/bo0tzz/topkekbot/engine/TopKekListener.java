package com.bo0tzz.topkekbot.engine;

import pro.zackpollard.telegrambot.api.TelegramBot;
import pro.zackpollard.telegrambot.api.chat.message.send.InputFile;
import pro.zackpollard.telegrambot.api.chat.message.send.SendableAudioMessage;
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
        } else if (message.equalsIgnoreCase("nice meme")) {
            InputFile meme = new InputFile("AwADBAADAgAD44X6A_37JQhu46N4Ag");
            event.getChat().sendMessage(SendableAudioMessage.builder().audio(meme).build(), bot);
        } else if (message.equals("fish go moo")) {
            event.getChat().sendMessage("@TopKekBot notices that " + event.getMessage().getSender().getFullName() + " is truly enlightened.", bot);
        } else if (message.equals("tfw")) {
            event.getChat().sendMessage("no gf", bot);
        }
    }
}
