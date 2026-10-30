package com.boyninja1555.triggerclank.commands.lib;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public abstract class BotC {

    public abstract @NotNull String name();

    public abstract @NotNull String description();

    public abstract void execute(String[] args, MessageReceivedEvent event);
}
