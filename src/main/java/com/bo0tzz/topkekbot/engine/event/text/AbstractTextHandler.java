package com.bo0tzz.topkekbot.engine.event.text;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.chat.id.ChatId;
import com.jtelegram.api.requests.message.framework.ParseMode;
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
     * @param chat The chat to send the message to
     * @param message The message to send
     * @param parseMode The ParseMode to use
     */
    private void sendMessage(ChatId chat, String message, ParseMode parseMode) {

        SendText.SendTextBuilder builder = SendText.builder()
                .chatId(chat)
                .text(message)
                .parseMode(ParseMode.MARKDOWN);

        bot.perform(builder.build());

    }

    protected String getHandlerName() {
        return handlerName;
    }
}
