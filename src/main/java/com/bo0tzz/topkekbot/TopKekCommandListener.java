package com.bo0tzz.topkekbot;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import pro.zackpollard.telegrambot.api.TelegramBot;
import pro.zackpollard.telegrambot.api.chat.message.send.SendableTextMessage;
import pro.zackpollard.telegrambot.api.event.Listener;
import pro.zackpollard.telegrambot.api.event.chat.message.CommandMessageReceivedEvent;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

/**
 * Created by bo0tzz
 * Enjoyed by stuntguy3000
 */
public class TopKekCommandListener implements Listener {

    private static final String[] OPTIONS_8BALL = {
            "It is certain",
            "It is decidedly so",
            "Without a doubt",
            "Yes definitely",
            "You may rely on it",
            "As I see it, yes",
            "Most likely",
            "Outlook good",
            "Yes",
            "Signs point to yes",
            "Reply hazy try again",
            "Ask again later",
            "Better not tell you now",
            "Cannot predict now",
            "Concentrate and ask again",
            "Don't count on it",
            "My reply is no",
            "My sources say no",
            "Outlook not so good",
            "Very doubtful"
    };
    private static final String[] TINY_LETTERS = {
            "ᵃ", "ᵇ", "ᶜ", "ᵈ", "ᵉ", "ᶠ", "ᵍ", "ʰ", "ᶦ", "ʲ", "ᵏ", "ˡ", "ᵐ", "ᶰ", "ᵒ", "ᵖ", "q", "ʳ", "ˢ", "ᵗ", "ᵘ",
            "ᵛ", "ʷ", "ˣ", "ʸ", "ᶻ"
    };
    private static final String[] BUBBLE_LETTERS = {
            "Ⓐ ", "Ⓑ", "Ⓒ", "Ⓓ", "Ⓔ", "Ⓕ", "Ⓖ", "Ⓗ", "Ⓘ", "Ⓙ", "Ⓚ", "Ⓛ", "Ⓜ", "Ⓝ", "Ⓞ", "Ⓟ", "Ⓠ", "Ⓡ", "Ⓢ", "Ⓣ", "Ⓤ", "Ⓥ", "Ⓦ", "Ⓧ", "Ⓨ",
            "Ⓩ", "[", "\\", "]", "^", "_", "`", "ⓐ", "ⓑ", "ⓒ", "ⓓ", "ⓔ", "ⓕ", "ⓖ", "ⓗ", "ⓘ", "ⓙ", "ⓚ", "ⓛ", "ⓜ", "ⓝ", "ⓞ", "ⓟ",
            "ⓠ", "ⓡ", "ⓢ", "ⓣ", "ⓤ", "ⓥ", "ⓦ", "ⓧ", "ⓨ", "ⓩ"
    };
    private static final String[] UPSIDEDOWN_LETTERS = {
            "ɐ", "q", "ɔ", "p", "ǝ", "ɟ", "ƃ", "ɥ", "ᴉ", "ɾ", "ʞ", "l", "ɯ", "u", "o", "d", "b", "ɹ", "s",
            "ʇ", "n", "ʌ", "ʍ", "x", "ʎ", "z"
    };
    private static final String[] DEADBABY_JOKES = {
            "What is funnier than a dead baby?\nA dead baby in a clown costume.",
            "What is the difference between a baby and a onion?\nNo one cries when you chop up the baby.",
            "What is the difference between a dead baby and a water melon?\nOne's fun to hit with a sledge hammer, the other one's a water melon.",
            "What is the difference between a baby and a dart-board?\nDart-boards don't bleed.",
            "What is the difference between a baby and a mars bar?\nAbout 500 calories.",
            "Why did the family take the dead baby along on the cookout?\nSo they could light it and toast their marshmallows.",
            "Why was the dead baby kept in the kitchen drawer?\nThe family used it to crack nuts.",
            "Why do people keep dead babies in the rec. room?\nThey cut off one leg and use it as a ping pong paddle.",
            "Why do you put babies into blenders feet first?\nSo you can see the expression on their faces.",
            "Why do they boil water when a baby is being born?\nSo that if its born dead they can make soup.",
            "Why did the baby cross the road?\nIt was stapled to the chicken.",
            "How many babies does it take to make a bottle of baby oil?\nIt depends on how hard you squeeze them.",
            "How many babies fit in a blender?\nDepends on how powerful the blender is.",
            "How do you know when a baby is dead?\nIt doesn't cry if you nail its feet to the ceiling.",
            "How do you find the live baby in a pile of dead ones?\nJab 'em all with a pitchfork.",
            "How do you save a drowning baby?\nHarpoon it.",
            "How do you turn a baby into a dog?\nPour gas over it and light a match. Woof.",
            "How do you turn a baby into a cat?\nFreeze it solid, then run it through a bandsaw. Meeow.",
            "How do you get 100 babies into a bucket?\nWith a blender.\nHow do you get them out again?\nWith Doritos.",
            "How do you make a dead baby float?\nTake your foot off its head. or: A glass of soda water and 2 scoops of baby.",
            "What do you call two abortions in a bucket?\nBlood brothers.",
            "What is red and is creeping up your leg?\nAn abortion with homesickness.",
            "What is a foot long and can make a woman scream?\nStillbirth.",
            "What is a foot long, blue, and makes women scream in the morning?\nCrib death.",
            "What do you call a dead baby pinned to your wall?\nArt.",
            "What is red, bubbly, and scratches at the window before exploding?\nA baby in a microwave.",
            "What is blue and yellow and sits at the bottom of the pool?\nBaby with slashed floaties.",
            "What is red and yellow and floats at the top of the pool?\nFloaties with a slashed baby.",
            "What is red and hangs around trees?\nA baby hit by a snow blower.",
            "What is green and hangs around trees?\nSame baby 3 weeks later.",
            "What is pink and red and silver and crawls into walls?\nA baby with forks in its eyes.",
            "What is pink and goes black with a \"hiss.\"?\nA baby thrown into a furnace.",
            "What is brown and gurgles?\nA baby in a casserole.",
            "What is purple, covered in pus, and squeals?\nA peeled baby in a bag of salt.",
            "What is black and goes up and down?\nA baby in a toaster.",
            "What is red and hangs out of the back of a train?\nA miscarriage.",
            "What is red and goes round and round?\nA baby in a garbage disposal.",
            "What is red and swings back and forth?\nA baby on a meat hook.",
            "What is red, screams, and goes around in circles?\nA baby nailed to the floor.",
            "What is red and sits in the corner?\nA baby with razor blades.",
            "What is blue and sits in the corner?\nA baby in a baggie.",
            "What is black and sits in a corner?\nA baby with it's finger in a power socket.",
            "What is green and sits in the corner?\nSame baby two weeks later.",
            "What is black and charred?\nA baby chewing on an extension cord.",
            "What is black and white, runs around the room, and smokes?\nA baby with his hair on fire.",
            "What is blue and flies around the room at high speeds?\nA baby with a punctured lung.",
            "What is cold, blue and doesn't move?\nA baby in your freezer.",
            "What is pink, flies and squeals?\nA baby fired from a catapult.",
            "What do you call the baby when it lands?\nFree pizza.",
            "What is red and has more brains than the baby you just shot?\nThe wall behind it.",
            "What is white and glows pink?\nA dead baby with an electrode up its ass.",
            "What is more fun than nailing a baby to a wall?\nRipping it off again.",
            "What is more fun than throwing a baby off the cliff?\nCatching it with a pitchfork.",
            "What is more fun than swinging babies around on a clothesline?\nStopping them with a shovel.",
            "What is more fun than shoveling dead babies off your porch?\nDoing it with a snow blower.",
            "What sits in the kitchen and keeps getting smaller and smaller?\nA baby combing it's hair with a potato peeler.",
            "What bounces up and down at 100mph?\nA baby tied to the back of a truck.",
            "What goes plop, plop, fizz, fizz?\nTwins in an acid bath.",
            "What is red and pink and can't turn round in a corridor?\nA baby with a javelin through its throat.",
            "What is little and can't fit through a door?\nA baby with a spear in its head.",
            "What is the definition of fun?\nPlaying fetch with a pitbull and a baby.",
            "What has 4 legs and one arm?\nA doberman on a children's playground.",
            "What has 10 arms and blood all over it?\nA pitbull in front of a pile of dead babies.",
            "What is red and pink and hanging out of your dog's mouth?\nYour baby's leg.",
            "What present do you get for a dead baby?\nA dead puppy.",
            "What is grosser than ten dead babies nailed to a tree?\nOne dead baby nailed to ten trees.",
            "What is the worst part about killing a baby?\nGetting blood on your clown suit."
    };
    private final TelegramBot bot;
    private final Tweeter tweeter;
    private final Map<String, Consumer<CommandMessageReceivedEvent>> commands;

    public TopKekCommandListener(TelegramBot bot, Tweeter tweeter) {
        this.bot = bot;
        this.tweeter = tweeter;
        this.commands = new HashMap<String, Consumer<CommandMessageReceivedEvent>>() {{
            TopKekCommandListener that = TopKekCommandListener.this;
            put("choice", that::choice);
            put("define", that::define);
            put("fuckingweather", that::fuckingweather);
            put("8ball", that::eightball);
            put("lmgtfy", that::lmgtfy);
            put("lenny", (event) -> event.getChat().sendMessage("( ͡° ͜ʖ ͡°)", bot));
            put("idk", (event) -> event.getChat().sendMessage("¯\\_(ツ)_/¯", bot));
            put("flip", (event) -> event.getChat().sendMessage("(╯°□°）╯︵ ┻━┻", bot));
            put("topkek", (event) -> event.getChat().sendMessage("http://s.mzn.pw/index.swf Gotta be safe while keking!", bot));
            put("wat", (event) -> event.getChat().sendMessage("http://waitw.at 0.o", bot));
            put("source", (event) -> event.getChat().sendMessage("The bot's source can be found over on https://github.com/bo0tzz/TopKekBot", bot));
            put("tweet", that::tweet);
            put("roll", that::roll);
            put("lucky", that::lucky);
            put("tiny", that::tiny);
            put("bubble", that::bubble);
            put("upsidedown", that::upsidedown);
            put("deadbaby", that::deadBabyJoke);
        }};
    }

    @Override
    public void onCommandMessageReceived(CommandMessageReceivedEvent event) {
        this.commands.getOrDefault(event.getCommand(), (c) -> {
        }).accept(event);
    }

    private void choice(CommandMessageReceivedEvent event) {
        String[] args = event.getArgsString().split(",");
        if (args.length <= 1) {
            event.getChat().sendMessage("Give me choices!", bot);
        } else {
            String choice = args[ThreadLocalRandom.current().nextInt(args.length)].trim();
            event.getChat().sendMessage(SendableTextMessage.builder().message("I say " + choice).replyTo(event.getMessage()).build(), bot);
        }
    }

    private void define(CommandMessageReceivedEvent event) {
        try {
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
            event.getChat().sendMessage("Failed to find definition of " + event.getArgsString(), bot);
            ex.printStackTrace();
        }
    }

    private void fuckingweather(CommandMessageReceivedEvent event) {
        try {
            for (String message : Util.getWeather(event.getArgsString())) {
                event.getChat().sendMessage(SendableTextMessage.builder()
                        .message(message)
                        .replyTo(event.getMessage())
                        .build(), bot);
            }
        } catch (Exception e) {
            event.getChat().sendMessage(SendableTextMessage.builder().message("THE FUCKING WEATHER MODULE FAILED FUCK!").build(), bot);
            e.printStackTrace();
        }
    }

    private void eightball(CommandMessageReceivedEvent event) {
        int chosen = ThreadLocalRandom.current().nextInt(OPTIONS_8BALL.length);
        event.getChat().sendMessage(SendableTextMessage.builder().message(OPTIONS_8BALL[chosen]).replyTo(event.getMessage()).build(), bot);
    }

    private void lmgtfy(CommandMessageReceivedEvent event) {
        String encoded = URLEncoder.encode(event.getArgsString());
        if (encoded.isEmpty()) {
            event.getChat().sendMessage(SendableTextMessage.builder().message("Here, use this to help resolve your life's issues.").build(), bot);
            event.getChat().sendMessage(SendableTextMessage.builder().message("http://lmgtfy.com/").build(), bot);
        } else {
            event.getChat().sendMessage(SendableTextMessage.builder().message("http://lmgtfy.com/?q=" + encoded).build(), bot);
        }
    }

    private void tweet(CommandMessageReceivedEvent event) {
        String tweet;
        if (event.getMessage().getSender().getUsername().equals("bo0tzz")) {
            tweet = event.getArgsString();
        } else {
            tweet = event.getMessage().getSender().getUsername() + " says: " + event.getArgsString();
        }
        System.out.println(("Tweeting: " + tweet));
        this.tweeter.sendTweet(tweet);
    }

    private void roll(CommandMessageReceivedEvent event) {
        new Thread(() -> {
            String[] num = event.getArgsString().split("d");
            StringBuilder out = new StringBuilder("Results: [");
            try {
                int count = Integer.parseInt(num[0]);
                int val = Integer.parseInt(num[1]) + 1;
                if (count < 1 || val < 1 || count > 1000 || val > 1001) {
                    throw new IllegalArgumentException("topkek");
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
    }

    private void lucky(CommandMessageReceivedEvent event) {
        String url = "https://www.googleapis.com/customsearch/v1?key=" + TopKekBot.getGoogleKey() + "&cx=016322137100648159445:_tfnpvfyqok&q=";
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.get(url + event.getArgsString().replace(" ", "+"))
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        JSONArray array = response.getBody().getObject().getJSONArray("items");
        if (array.length() == 0) {
            event.getChat().sendMessage("No result found!", bot);
            return;
        }
        String result = array.getJSONObject(0).getString("link");
        event.getChat().sendMessage(SendableTextMessage.builder().message(result).replyTo(event.getMessage()).build(), bot);
    }

    private void tiny(CommandMessageReceivedEvent event) {
        StringBuilder sb = new StringBuilder();
        for (char c : event.getArgsString().toLowerCase().toCharArray()) {
            int index = ((int) c) - 97; //Character code "a" starts at 97
            if (index >= 0 && index <= 25) {
                sb.append(TINY_LETTERS[index]);
                continue;
            }
            sb.append(c);
        }
        event.getChat().sendMessage(SendableTextMessage.builder().message(sb.toString()).replyTo(event.getMessage()).build(), bot);
    }

    private void bubble(CommandMessageReceivedEvent event) {
        StringBuilder sb = new StringBuilder();
        for (char c : event.getArgsString().toCharArray()) {
            int index = ((int) c) - 65; //Character code "A" starts at 65
            if (index >= 0 && index <= 57) {
                sb.append(BUBBLE_LETTERS[index]);
                continue;
            }
            sb.append(c);
        }
        event.getChat().sendMessage(SendableTextMessage.builder().message(sb.toString()).replyTo(event.getMessage()).build(), bot);
    }

    private void upsidedown(CommandMessageReceivedEvent event) {
        String in = new StringBuilder(event.getArgsString().toLowerCase()).reverse().toString();
        StringBuilder sb = new StringBuilder();
        for (char c : in.toCharArray()) {
            int index = ((int) c) - 97; //Character code "a" starts at 97
            if (index >= 0 && index <= 25) {
                sb.append(UPSIDEDOWN_LETTERS[index]);
                continue;
            }
            sb.append(c);
        }
        event.getChat().sendMessage(SendableTextMessage.builder().message(sb.toString()).replyTo(event.getMessage()).build(), bot);
    }

    private void deadBabyJoke(CommandMessageReceivedEvent event) {
        Random random = new Random();
        event.getChat().sendMessage(SendableTextMessage.builder().message(DEADBABY_JOKES[random.nextInt(DEADBABY_JOKES.length - 1)]).build(), bot);
    }
}
