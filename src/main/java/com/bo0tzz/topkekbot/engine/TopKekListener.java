package com.bo0tzz.topkekbot.engine;

import com.bo0tzz.topkekbot.TopKekBot;
import com.bo0tzz.topkekbot.bean.TextAction;
import com.google.common.collect.Iterators;
import pro.zackpollard.telegrambot.api.TelegramBot;
import pro.zackpollard.telegrambot.api.chat.message.send.InputFile;
import pro.zackpollard.telegrambot.api.chat.message.send.SendablePhotoMessage;
import pro.zackpollard.telegrambot.api.chat.message.send.SendableTextMessage;
import pro.zackpollard.telegrambot.api.event.Listener;
import pro.zackpollard.telegrambot.api.event.chat.message.TextMessageReceivedEvent;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
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

    private final Pattern assPattern = Pattern.compile("(\\w+)-ass (\\w+)", Pattern.CASE_INSENSITIVE);

    private final Pattern subredditPattern = Pattern.compile("/r/(\\w+)[\\s$]?", Pattern.CASE_INSENSITIVE);

    private final Iterator<String> NO_U = Iterators.cycle("oof", "ouch", "owie");
    private final Iterator<String> U_ON = Iterators.cycle("ǝıʍo", "ɥɔno", "ɟoo");

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

            //it's literally called "topkekbot" so this responds "kek" when someone says "top"
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("top"), (e) -> "kek"));
            add(new TextAction((t, ev) -> t.toLowerCase().contains("good night"), (e) -> "goodnight bb, sweet dreams \uD83D\uDE18\uD83D\uDE18\uD83D\uDE18❤️❤️❤️\uD83D\uDC8B\uD83D\uDC8B\uD83D\uDC8B"));
            add(new TextAction((t, ev) -> t.toLowerCase().contains("pleb"), (e) -> "Pleb, yes"));
            add(new TextAction((t, ev) -> t.toLowerCase().contains("fuck off"), (e) -> "No you fuck off, fucking idiot."));
            add(new TextAction((t, ev) -> t.toLowerCase().contains("humble brag"), (e) -> "It's not their fault that they're better than you."));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("not"), (e) -> "You - Not, Me - Bot"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("bot"), (e) -> "I'm a bot and still smarter than you."));
            add(new TextAction((t, ev) -> t.toLowerCase().contains("blend"), (e) -> "But will it blend?"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("thank mr bot"), (e) -> "may good cpus and dedotated wams come to you"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("nice meme"), (e) -> "http://niceme.me/nicememe.mp3"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("true love"), (e) -> "http://i.imgur.com/nRAZBRs.png"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("hi"), (e) -> "sup"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("no u"), (e) -> NO_U.next()));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("n ou"), (e) -> U_ON.next()));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("sup"), (e) -> "hi"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("oh canada"), (e) -> "http://i.imgur.com/bULAfzE.jpg"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("fat"), (e) -> "Zenexer is fat"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("skinny"), (e) -> "Fatass"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("fish go moo"), (e) -> SendableTextMessage.plain("@TopKek_Bot notices that " + e.getMessage().getSender().getFullName() + " is truly enlightened.").build(), false));
            add(new TextAction((t, ev) -> t.toLowerCase().endsWith("go moo"), (e) -> "What are you, fucking retarded?"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("moo"), (e) -> "woof"));
            add(new TextAction((t, ev) -> t.contains("@topkek_bot"), (e) -> "topkek"));
            add(new TextAction((t, ev) -> t.contains("@shibesquad"), (e) -> "+1"));
            add(new TextAction((t, ev) -> t.contains("UUID"), (e) -> "Be careful with that UUID, you might get a collision!"));
            add(new TextAction((t, ev) -> t.toLowerCase().contains("trident"), (e) -> secureRandom.nextInt(100) <= 15 ? "TridnetSDK is dead." : null));
            add(new TextAction((t, ev) -> t.toLowerCase().contains("topkek"), (e) -> SendableTextMessage.markdown("[Gotta be safe while keking!](http://v.bo0tzz.me/topkek)").build(), false));
            add(new TextAction((t, ev) -> t.toLowerCase().contains("rawr"), (e) -> "xd"));
            add(new TextAction((t, ev) -> t.toLowerCase().contains("trump"), (e) -> {
                int rand = secureRandom.nextInt(100);
                if (rand < 5) {
                    return "Lawdy lawdy lawdy, I dun seen the light, yes I did! He's come to save us all!";
                }
                return null;
            }));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("*sigh*") && ev.getMessage().getSender().getUsername().equalsIgnoreCase("zackpollard"),
                    (e) -> "Yes yes Zack, we get it, you're sick of our shit."));
            add(new TextAction((t, ev) -> t.toLowerCase().contains("girl") && ev.getMessage().getSender().getUsername().equalsIgnoreCase("MazenK"), (e) -> "April is watching..."));
            add(new TextAction((t, ev) -> t.contains("xD"), (e) -> {
                String s = e.getContent().getContent().toLowerCase();
                int index = -1;
                StringBuilder m = new StringBuilder();
                Random r = new SecureRandom(); // we don't want people guessing!!!
                while ((index = s.indexOf("xd", index + 1)) != -1) {
                    m.append("x");
                    for (int i = 0; i < r.nextInt(10); i++) {
                        m.append(xd[r.nextInt(4)]);
                    }
                    m.append("D");
                }
                return m.toString();
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
            add(new TextAction((t, ev) -> assPattern.matcher(t).find(), (e) -> {
                String message = e.getContent().getContent();
                Matcher m = assPattern.matcher(message);
                return m.group(1) + " ass-" + m.group(2);
            }));
            add(new TextAction((t, ev) -> subredditPattern.matcher(t).find(), (e) -> {
                String message = e.getContent().getContent();
                Matcher m = subredditPattern.matcher(message);
                String subreddit = m.group(1);
                String markdown = "[/r/";
                markdown += subreddit;
                markdown += "](https://old.reddit.com/r/";
                markdown += subreddit;
                markdown += ")";
                return SendableTextMessage.markdown(markdown).build();
            }, false));
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
                .map(response -> event.getChat().sendMessage(response));
    }
}
