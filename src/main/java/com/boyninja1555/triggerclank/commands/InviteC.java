package com.boyninja1555.triggerclank.commands;

import com.boyninja1555.triggerclank.commands.lib.BotC;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class InviteC extends BotC {
    private final String[] links = {"https://discord.gg/yt78h3ypeX"};
    private final String[] linkTooltips = {"Main"};

    @Override
    public @NotNull String name() {
        return "invite";
    }

    @Override
    public @NotNull String description() {
        return "Our public invite links";
    }

    @Override
    public void execute(String[] args, MessageReceivedEvent event) {
        List<String> messageLines = new ArrayList<>();
        messageLines.add("**] Invite Links**");

        for (int i = 0; i < links.length; i++)
            messageLines.add(linkTooltips[i] + " — <" + links[i] + ">");

        event.getChannel().sendMessage(String.join("\n", messageLines)).queue();
    }
}
