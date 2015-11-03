package com.bo0tzz.topkekbot;

import pro.zackpollard.telegrambot.api.TelegramBot;
import pro.zackpollard.telegrambot.api.chat.message.send.InputFile;
import pro.zackpollard.telegrambot.api.chat.message.send.SendableAudioMessage;
import pro.zackpollard.telegrambot.api.chat.message.send.SendableTextMessage;
import pro.zackpollard.telegrambot.api.event.Listener;
import pro.zackpollard.telegrambot.api.event.chat.message.TextMessageReceivedEvent;

import java.net.MalformedURLException;
import java.net.URL;

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
            InputFile meme = null;
            try {
                meme = new InputFile(new URL("http://niceme.me/nicememe.mp3"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            event.getChat().sendMessage(SendableAudioMessage.builder().audio(meme).build(), bot);
        } else if (message.equals("fish go moo")) {
            event.getChat().sendMessage("@TopKek_Bot notices that " + event.getMessage().getSender().getFullName() + " is truly enlightened.", bot);
        } else if (message.equals("tfw")) {
            event.getChat().sendMessage("no gf", bot);
        } else if (message.toLowerCase().contains("flickr.com/photos/stuntguy3000")) {
            event.getChat().sendMessage("Nobody likes your photos, Luke.", bot);
        } else if (event.getMessage().getSender().getUsername().equals("zackpollard") && message.equals("*Sigh*")) {
            event.getChat().sendMessage(SendableTextMessage.builder().message("Yes yes Zack, we get it. You're tired of our shit.").replyTo(event.getMessage()).build(), bot);
        }
    }
}
