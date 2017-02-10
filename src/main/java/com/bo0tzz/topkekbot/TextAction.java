package com.bo0tzz.topkekbot;

import pro.zackpollard.telegrambot.api.event.chat.message.TextMessageReceivedEvent;

import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * Created by bo0tzz on 23-6-2016.
 */
public class TextAction {

    private final BiPredicate<String, TextMessageReceivedEvent> predicate;
    private final Function<TextMessageReceivedEvent, String> function;

    TextAction(BiPredicate<String, TextMessageReceivedEvent> predicate, Function<TextMessageReceivedEvent, String> function) {
        this.predicate = predicate;
        this.function = function;
    }

    public String apply(TextMessageReceivedEvent event) {
        return function.apply(event);
    }

    public boolean test(String s, TextMessageReceivedEvent c) {
        return predicate.test(s, c);
    }

}
