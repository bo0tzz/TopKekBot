package com.bo0tzz.topkekbot;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import pro.zackpollard.telegrambot.api.TelegramBot;
import pro.zackpollard.telegrambot.api.chat.message.send.SendableTextMessage;
import pro.zackpollard.telegrambot.api.event.Listener;
import pro.zackpollard.telegrambot.api.event.chat.message.CommandMessageReceivedEvent;

import java.net.URLEncoder;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by bo0tzz
 */
public class TopKekCommandListener implements Listener {

    private final TelegramBot bot;

    public TopKekCommandListener(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void onCommandMessageReceived(CommandMessageReceivedEvent event) {
        switch (event.getCommand()) {
            case "choice": {
                String[] args = event.getArgsString().split(",");
                if (args.length <= 1) {
                    event.getChat().sendMessage("Give me choices!", bot);
                } else {
                    String choice = args[ThreadLocalRandom.current().nextInt(args.length)];
                    event.getChat().sendMessage(SendableTextMessage.builder().message("I say " + choice).replyTo(event.getMessage()).build(), bot);
                }
                break;
            }

            case "define": {
                try  {
                    HttpResponse<String> response = Unirest.get("https://mashape-community-urban-dictionary.p.mashape.com/define?term=" + event.getArgsString().replace(" ", "+"))
                            .header("X-Mashape-Key", Util.KEY_URBAND)
                            .header("Accept", "text/plain")
                            .asString();
                    JSONObject object = new JSONObject(response.getBody());

                    if (object.getJSONArray("list").length() == 0) {
                        event.getChat().sendMessage("No definition found for " + event.getArgsString() + "!", bot);
                        return;
                    }

                    JSONObject definition = object.getJSONArray("list").getJSONObject(0);

                    event.getChat().sendMessage("Definition of " + event.getArgsString() + ": " + definition.getString("definition"), bot);
                    event.getChat().sendMessage(definition.getString("example"), bot);
                } catch (UnirestException ex) {
                    ex.printStackTrace();
                }
                break;
            }

            case "fuckingweather": {
                try {
                    event.getChat().sendMessage(SendableTextMessage.builder()
                            .message(Util.getWeather(event.getArgsString(), event.getChat()))
                            .replyTo(event.getMessage())
                            .build(), bot);
                } catch (Exception e) {
                    event.getChat().sendMessage(SendableTextMessage.builder().message("THE FUCKING WEATHER MODULE FAILED FUCK!").build(), bot);
                    e.printStackTrace();
                }
                break;
            }

            case "8ball": {
                String[] options = new String[]{"It is certain", "It is decidedly so", "Without a doubt", "Yes definitely", "You may rely on it", "As I see it, yes", "Most likely", "Outlook good", "Yes", "Signs point to yes", "Reply hazy try again", "Ask again later", "Better not tell you now", "Cannot predict now", "Concentrate and ask again", "Don't count on it", "My reply is no", "My sources say no", "Outlook not so good", "Very doubtful"};
                int chosen = ThreadLocalRandom.current().nextInt(options.length);
                event.getChat().sendMessage(SendableTextMessage.builder().message(options[chosen]).replyTo(event.getMessage()).build(), bot);
                break;
            }

            case "lmgtfy": {
                event.getChat().sendMessage(SendableTextMessage.builder().message("http://lmgtfy.com/?q=" + URLEncoder.encode(event.getArgsString())).build(), bot);
                break;
            }

            case "lenny": {
                event.getChat().sendMessage("( ͡° ͜ʖ ͡°)", bot);
                break;
            }

            case "idk": {
                event.getChat().sendMessage("¯\\_(ツ)_/¯", bot);
                break;
            }

            case "flip": {
                event.getChat().sendMessage("(╯°□°）╯︵ ┻━┻", bot);
                break;
            }

            case "topkek": {
                event.getChat().sendMessage("http://s.mzn.pw/index.swf Gotta be safe while keking!", bot);
                break;
            }

            case "wat": {
                event.getChat().sendMessage("http://waitw.at 0.o", bot);
                break;
            }

            case "source": {
                event.getChat().sendMessage("The bot's source can be found over on https://github.com/bo0tzz/TopKekBot", bot);
                break;
            }

            case "tweet": {
                String tweet;
                if (event.getMessage().getSender().getUsername().equals("bo0tzz")) {
                    tweet = event.getArgsString();
                } else {
                    tweet = event.getMessage().getSender().getUsername() + " says: " + event.getArgsString();
                }
                System.out.println(("Tweeting: " + tweet));
                Tweeter.getInstance().sendTweet(tweet);
                break;
            }

        case "roll": {
            new Thread(() -> {
                String[] num = event.getArgsString().split("d");
                int count;
                int val;
                StringBuilder out = new StringBuilder("Results: [");
                try {
                    count = Integer.parseInt(num[0]);
                    val = Integer.parseInt(num[1]) + 1;
                    if (count < 1 || val < 1 || count > 1000 || val > 1001) {
                        throw new IllegalArgumentException();
                    }
                    int[] results = new int[count];
                    for (int i = 0; i < count; i++) {
                        results[i] = ThreadLocalRandom.current().nextInt(1, val);
                    }
                    for (int result : results) {
                        out.append(result).append(",");
                    }
                    out.deleteCharAt(out.length() - 1).append("]");
                    event.getChat().sendMessage(SendableTextMessage.builder()
                            .message(out.toString())
                            .replyTo(event.getMessage())
                            .build(), bot);
                } catch (IllegalArgumentException ex) {
                    event.getChat().sendMessage("Incorrect args or numbers too large! Format: /roll 2d10", bot);
                } catch (OutOfMemoryError ex) {
                    event.getChat().sendMessage("Numbers too large - not enough memory!", bot);
                }
            }).start();
            break;
            }
        }
    }
}
