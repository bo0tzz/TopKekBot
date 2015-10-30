package com.bo0tzz.topkekbot.engine;


import com.bo0tzz.topkekbot.TopKekBot;
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
    private Twitter twitter;
    private Configuration config;

    private Tweeter() {
        config = new ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
                .setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET)
                .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(TWITTER_ACCESS_SECRET)
                .build();
        twitter = new TwitterFactory(config).getInstance();
        createListener();
    }

    public static Tweeter getInstance() {
        if (instance == null)
            instance = new Tweeter();
        return instance;
    }

    public void sendTweet(String content) {
        try {
            twitter.updateStatus(content);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

    private void createListener() {
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                if (status.getText().contains("@topkekbot")) {
                    SendableTextMessage message = SendableTextMessage.builder()
                            .message("Bot was mentioned in a tweet!\n"
                                    + "@" + status.getUser().getScreenName()
                                    + " (" + status.getUser().getName() + ")"
                                    + ": " + status.getText()).build();
                    TopKekBot.bot.sendMessage(TelegramBot.getChat(-17349250), message);
                } else if ((status.getUser().getScreenName().equalsIgnoreCase("telegram") || status.getUser().getScreenName().equalsIgnoreCase("bo0tzzz")) && !status.getText().contains("@")) {
                    SendableTextMessage message = SendableTextMessage.builder()
                            .message("Tweet by @" + status.getUser().getScreenName() + ": "
                            + status.getText()).build();
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
        };
        TwitterStream twitterStream = new TwitterStreamFactory(config).getInstance();
        twitterStream.addListener(listener);
        String[] track = {"@topkekbot"};
        long[] follow = {1689053928L, 780156121L};
        twitterStream.filter(new FilterQuery(0, follow, track));
    }
}
