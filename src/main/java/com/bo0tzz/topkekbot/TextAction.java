package com.bo0tzz.topkekbot;

import pro.zackpollard.telegrambot.api.event.chat.message.TextMessageReceivedEvent;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by bo0tzz on 23-6-2016.
 */
public class TextAction {

    private final Predicate<String> predicate;
    private final Function<TextMessageReceivedEvent, String> function;

    TextAction(Predicate<String> predicate, Function<TextMessageReceivedEvent, String> function) {
        this.predicate = predicate;
        this.function = function;
    }

    public String execute(TextMessageReceivedEvent event) {
        return function.apply(event);
    }

    public boolean test(String s) {
        return predicate.test(s);
    }

}
