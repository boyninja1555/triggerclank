package com.boyninja1555.triggerclank.commands;

import com.boyninja1555.triggerclank.commands.lib.BotC;
import com.boyninja1555.triggerclank.lib.ChannelReference;
import com.boyninja1555.triggerclank.lib.RuntimeProperties;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class GithubC extends BotC {

    @Override
    public @NotNull String name() {
        return "github";
    }

    @Override
    public @NotNull String description() {
        return "Sends you the official GitHub repository of Triggerclank";
    }

    @Override
    public void execute(String[] args, MessageReceivedEvent event) {
        event.getChannel().sendMessage("Visit the " + ChannelReference.text(RuntimeProperties.githubChannelId()) + " channel for a link to our public source code").queue();
    }
}
