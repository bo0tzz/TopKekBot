package com.bo0tzz.topkekbot;

import pro.zackpollard.telegrambot.api.TelegramBot;
import pro.zackpollard.telegrambot.api.event.Listener;
import pro.zackpollard.telegrambot.api.event.chat.message.TextMessageReceivedEvent;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by bo0tzz
 */
public class TopKekListener implements Listener {

    private final TelegramBot bot;
    private final List<TextAction> textActions;
    private final String[] xd = {"x", "X", "d", "D"};

    public TopKekListener(TelegramBot bot) {
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
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("fat"), (e) -> "Mazen is fat"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("fish go moo"), (e) -> "@TopKek_Bot notices that " + e.getMessage().getSender().getFullName() + " is truly enlightened."));
            add(new TextAction((t, ev) -> t.contains("@topkek_bot"), (e) -> "topkek"));
            add(new TextAction((t, ev) -> t.contains("TridentSDK"), (e) -> "TridnetSDK is dead."));
            add(new TextAction((t, ev) -> t.toLowerCase().contains("retarded"), (e) -> "Intriguing"));
            add(new TextAction((t, ev) -> (t.contains("!!") || t.contains("ROFL")) && ev.getMessage().getSender().getUsername().equals("JulianAyy"), (e) -> "Autism!"));
            add(new TextAction((t, ev) -> t.equalsIgnoreCase("*sigh*") && ev.getMessage().getSender().getUsername().equalsIgnoreCase("zackpollard"),
                    (e) -> "Yes yes Zack, we get it, you're sick of our shit."));
            add(new TextAction((t, ev) -> t.toLowerCase().contains("girl") && ev.getMessage().getSender().getUsername().equalsIgnoreCase("MazenK"), (e) -> "April is watching..."));
            add(new TextAction((t, ev) -> t.contains("xD"), (e) -> {
                Random r = new Random();
                String m = "x";
                for (int i = 0; i < r.nextInt(10); i++) {
                    m += xd[r.nextInt(4)];
                }
                m += "D";
                return m;
            }));
            add(new TextAction((t, ev) -> t.equals("tfw"), (e) -> {
                String reply = "no ";
                String lastCommand = TopKekBot.getInstance().getLastCommand().get(e.getMessage().getSender().getId());
                if(lastCommand != null && lastCommand.equals("getgif")) {
                    reply += "gif";
                } else {
                    reply += "gf";
                }
                return reply;
            }));
        }};
        this.bot = bot;
    }

    @Override
    public void onTextMessageReceived(TextMessageReceivedEvent event) {
        String message = event.getContent().getContent();
        textActions.stream().filter(t -> t.test(message, event)).limit(1).forEach(t -> event.getChat().sendMessage(t.apply(event)));
    }
}
