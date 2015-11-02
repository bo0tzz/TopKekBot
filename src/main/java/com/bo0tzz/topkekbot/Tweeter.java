package com.bo0tzz.topkekbot;


import pro.zackpollard.telegrambot.api.TelegramBot;
import pro.zackpollard.telegrambot.api.chat.message.send.SendableTextMessage;
import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by bo0tzz
 */
public class Tweeter {

    public static final String TWITTER_CONSUMER_KEY = "B6zyj5WJmGjCf4Iya5T0l3CDn";
    public static final String TWITTER_CONSUMER_SECRET = "T8f2mLGP4RnejFUHumkCHBRqhcfGORDfPM2AjWGts7ulpyVRdh";
    public static final String TWITTER_ACCESS_TOKEN = "3761163975-y8kkpPdGmMQx1sH8tMaTXoGIQSeghpj3sM4SH7r";
    public static final String TWITTER_ACCESS_SECRET = "kke6IKo7haOF6pz5t5ICN1ArCqsNxbFGgb9zqh1IPgWb5";
    private static Tweeter instance;
    private final TelegramBot bot;

    private final Twitter twitter;

    private final Configuration config;

    private Tweeter(TelegramBot bot) {
        this.bot = bot;
        this.config = new ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
                .setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET)
                .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(TWITTER_ACCESS_SECRET)
                .build();
        this.twitter = new TwitterFactory(config).getInstance();
        createListener();
    }

    public static Tweeter getInstance(TelegramBot bot) {
        if (instance == null) {
            synchronized (Tweeter.class) {
                if (instance == null) {
                    instance = new Tweeter(bot);
                }
            }
        }
        return instance;
    }

    public void sendTweet(String content) {
        try {
            this.twitter.updateStatus(content);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

    private void createListener() {
        StatusListener listener = new BotStatusListener();
        TwitterStream twitterStream = new TwitterStreamFactory(this.config).getInstance();
        twitterStream.addListener(listener);
        String[] track = {"@topkekbot"};
        long[] follow = {1689053928L, 780156121L};
        twitterStream.filter(new FilterQuery(0, follow, track));
    }

    private class BotStatusListener implements StatusListener {
        @Override
        public void onStatus(Status status) {
            if (status.getText().contains("@topkekbot")) {
                SendableTextMessage message = SendableTextMessage.builder()
                        .message(new StringBuilder("Bot was mentioned in a tweet!\n")
                                .append("@").append(status.getUser().getScreenName())
                                .append(" (").append(status.getUser().getName()).append(")")
                                .append(": ").append(status.getText()).toString()).build();
                Tweeter.this.bot.sendMessage(TelegramBot.getChat(-17349250), message);
            } else if ((status.getUser().getScreenName().equalsIgnoreCase("telegram") || status.getUser().getScreenName().equalsIgnoreCase("bo0tzzz")) && !status.getText().contains("@")) {
                SendableTextMessage message = SendableTextMessage.builder()
                        .message("Tweet by @" + status.getUser().getScreenName() + ": "
                                + status.getText()).build();
                Tweeter.this.bot.sendMessage(TelegramBot.getChat(-17349250), message);
            }
        }

        @Override
        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
        }

        @Override
        public void onTrackLimitationNotice(int i) {
        }

        @Override
        public void onScrubGeo(long l, long l1) {
        }

        @Override
        public void onStallWarning(StallWarning stallWarning) {
        }

        @Override
        public void onException(Exception e) {
        }
    }
}
