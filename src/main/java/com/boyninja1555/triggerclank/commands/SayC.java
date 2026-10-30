package com.boyninja1555.triggerclank.commands;

import com.boyninja1555.triggerclank.commands.lib.BotC;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class SayC extends BotC {

    @Override
    public boolean isProtected() {
        return true;
    }

    @Override
    public @NotNull String name() {
        return "say";
    }

    @Override
    public @NotNull String description() {
        return "Says a message as the bot";
    }

    @Override
    public void execute(String[] args, MessageReceivedEvent event) {
        event.getChannel().sendMessage(event.getMessage().getContentRaw().substring(name().length() + 1)).queue();
        event.getMessage().delete().queue();
    }
}
