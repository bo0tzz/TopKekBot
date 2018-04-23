package com.bo0tzz.topkekbot.engine.event.text;

import com.jtelegram.api.events.EventHandler;
import com.jtelegram.api.events.message.TextMessageEvent;

public interface TextHandler extends EventHandler<TextMessageEvent> {

    /**
     * Handle an event
     * @return true if the event was successfully handled, false otherwise
     */
    boolean handle(TextMessageEvent event);

}
