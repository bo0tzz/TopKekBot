package com.bo0tzz.topkekbot.engine.event.text;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.requests.message.send.SendText;

public abstract class AbstractTextHandler implements TextHandler {

    private final String handlerName;

    private final TelegramBot bot;

    AbstractTextHandler(TelegramBot bot, String handlerName) {
        this.bot = bot;
        this.handlerName = handlerName;
    }

    /**
     * Send a message in reply to an event
     * @param chat
     * @param message
     * @param markdown
     */
    private void sendMessage(ChatId chat, String message, boolean markdown) {



    }

    protected String getHandlerName() {
        return handlerName;
    }
}
