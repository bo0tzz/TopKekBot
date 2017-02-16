package com.bo0tzz.topkekbot;

import pro.zackpollard.telegrambot.api.TelegramBot;
import pro.zackpollard.telegrambot.api.chat.message.send.InputFile;
import pro.zackpollard.telegrambot.api.chat.message.send.SendablePhotoMessage;
import pro.zackpollard.telegrambot.api.event.Listener;
import pro.zackpollard.telegrambot.api.event.chat.message.TextMessageReceivedEvent;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by bo0tzz
 */
public class TopKekListener implements Listener {

    private final TelegramBot bot;
    private final List<TextAction> textActions;
    private final String[] xd = {"x", "X", "d", "D"};

    private final Pattern jokesOnYouPattern = Pattern.compile("\\bjoke'?s?\\s+on\\s+you\\b", Pattern.CASE_INSENSITIVE);
    private final String jokesOnYouUrl = "http://i.imgur.com/4y6krel.png";
    private final Pattern jestOnTheePattern = Pattern.compile("\\bjest'?s?\\s+(?:on|(?:be\\s+)?with)\\s+th(?:ee|ou)\\b", Pattern.CASE_INSENSITIVE);
    private final String jestOnTheeUrl = "http://i.imgur.com/SzxKs5a.png";
    private final SecureRandom secureRandom = new SecureRandom();
    private InputFile jokesOnYouFile;
    private InputFile jestOnTheeFile;

    public TopKekListener(TelegramBot bot) {
        try {
            this.jokesOnYouFile = new InputFile(new URL(jokesOnYouUrl));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            this.jestOnTheeFile = new InputFile(new URL(jestOnTheeUrl));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        textActions = new LinkedList<TextAction>() {{
            add(new TextAction((t, ev) -> t.toLowerCase().startsWith("@topkek_bot"), (e) -> e.getContent().getContent().substring(11)));
            add(new TextAction((t, ev) -> t.toLowerCase().contains("pleb"), (e) -> "Pleb, yes"));
            add(new TextAction((t, ev) -> t.toLowerCase().contains("blend"), (e) -> "But will it blend?"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("thank mr bot"), (e) -> "may good cpus and dedotated wams come to you"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("nice meme"), (e) -> "http://niceme.me/nicememe.mp3"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("true love"), (e) -> "http://i.imgur.com/nRAZBRs.png"));
            add(new TextAction((t, ev) -> t.toLowerCase().contains("flickr.com/photos/stuntguy3000"), (e) -> "Nobody likes your photos, Luke"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("hi"), (e) -> "sup"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("sup"), (e) -> "hi"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("oh canada"), (e) -> "http://i.imgur.com/bULAfzE.jpg"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("fat"), (e) -> "Amir is fat"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("fish go moo"), (e) -> "@TopKek_Bot notices that " + e.getMessage().getSender().getFullName() + " is truly enlightened."));
            add(new TextAction((t, ev) -> t.toLowerCase().endsWith("go moo"), (e) -> "What are you, fucking retarded?"));
            add(new TextAction((t, ev) -> t.contains("@topkek_bot"), (e) -> "topkek"));
            add(new TextAction((t, ev) -> t.contains("TridentSDK"), (e) -> "TridnetSDK is dead."));
            add(new TextAction((t, ev) -> t.toLowerCase().contains("topkek"), (e) -> "[Gotta be safe while keking!](http://waitw.at/topkek)")); 
            add(new TextAction((t, ev) -> t.toLowerCase().contains("retarded"), (e) -> "Intriguing"));
            add(new TextAction((t, ev) -> t.toLowerCase().contains("trump"), (e) -> {
                int rand = secureRandom.nextInt(100);
                if (rand < 15) {
                    return "Lawdy lawdy lawdy, I dun seen the light, yes I did! He's come to save us all!";
                }
                return null;
            }));
            add(new TextAction((t, ev) -> (t.contains("!!") || t.contains("ROFL")) && ev.getMessage().getSender().getUsername().equals("JulianAyy"), (e) -> "Autism!"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("*sigh*") && ev.getMessage().getSender().getUsername().equalsIgnoreCase("zackpollard"),
                    (e) -> "Yes yes Zack, we get it, you're sick of our shit."));
            add(new TextAction((t, ev) -> t.toLowerCase().contains("girl") && ev.getMessage().getSender().getUsername().equalsIgnoreCase("MazenK"), (e) -> "April is watching..."));
            add(new TextAction((t, ev) -> t.contains("xD"), (e) -> {
                String s = e.getContent().getContent().toLowerCase();
                int index = -1;
                String m = "";
                Random r = new SecureRandom(); // we don't want people guessing!!!
                while ((index = s.indexOf("xd", index + 1)) != -1) {
                    m += "x";
                    for (int i = 0; i < r.nextInt(10); i++) {
                        m += xd[r.nextInt(4)];
                    }
                    m += "D";
                }
                return m;
            }));
            add(new TextAction((t, ev) -> t.equals("tfw"), (e) -> {
                String reply = "no ";
                String lastCommand = TopKekBot.getInstance().getLastCommand().get(e.getMessage().getSender().getId());
                if (lastCommand != null && lastCommand.equals("getgif")) {
                    reply += "gif";
                } else {
                    reply += "gf";
                }
                return reply;
            }));
            if (jokesOnYouFile != null) {
                add(new TextAction((t, ev) -> jokesOnYouPattern.matcher(t).find(), e -> {
                    e.getChat().sendMessage(SendablePhotoMessage.builder()
                            .photo(jokesOnYouFile)
                            .build());
                    return null;
                }));
            }
            if (jestOnTheeFile != null) {
                add(new TextAction((t, ev) -> jestOnTheePattern.matcher(t).find(), e -> {
                    e.getChat().sendMessage(SendablePhotoMessage.builder()
                            .photo(jestOnTheeFile)
                            .build());
                    return null;
                }));
            }
        }};
        this.bot = bot;
    }

    @Override
    public void onTextMessageReceived(TextMessageReceivedEvent event) {
        String message = event.getContent().getContent();
        textActions.stream()
                .map(t -> t.test(message, event) ? t.apply(event) : null)
                .filter(Objects::nonNull)
                .findFirst()
                .map(event.getChat()::sendMessage);
    }
}
