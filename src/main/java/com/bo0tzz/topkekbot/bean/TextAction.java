package com.bo0tzz.topkekbot.bean;

import pro.zackpollard.telegrambot.api.chat.message.send.SendableMessage;
import pro.zackpollard.telegrambot.api.chat.message.send.SendableTextMessage;
import pro.zackpollard.telegrambot.api.event.chat.message.TextMessageReceivedEvent;

import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * Created by bo0tzz on 23-6-2016.
 */
public class TextAction {

    private final BiPredicate<String, TextMessageReceivedEvent> predicate;
    private final Function<TextMessageReceivedEvent, SendableMessage> function;

    public TextAction(BiPredicate<String, TextMessageReceivedEvent> predicate, Function<TextMessageReceivedEvent, SendableMessage> function, boolean whatever) {
        this.predicate = predicate;
        this.function = function;
    }

    public TextAction(BiPredicate<String, TextMessageReceivedEvent> predicate, Function<TextMessageReceivedEvent, String> function) {
        this.predicate = predicate;
        this.function = textMessageReceivedEvent -> SendableTextMessage.plain(function.apply(textMessageReceivedEvent)).build();
    }


    public SendableMessage apply(TextMessageReceivedEvent event) {
        return function.apply(event);
    }

    public boolean test(String s, TextMessageReceivedEvent c) {
        return predicate.test(s, c);
    }

}
