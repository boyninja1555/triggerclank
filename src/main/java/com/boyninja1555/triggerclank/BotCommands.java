package com.boyninja1555.triggerclank;

import com.boyninja1555.triggerclank.commands.lib.BotC;
import com.boyninja1555.triggerclank.lib.ErrorHandler;
import com.boyninja1555.triggerclank.lib.RuntimeProperties;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BotCommands {
    private static final Long[] admins = {1164340823249391706L};
    private static final Map<String, BotC> cachedCommands = new HashMap<>();

    public static List<BotC> asList() {
        return new ArrayList<>(cachedCommands.values());
    }

    public static void handle(MessageReceivedEvent event) {
        String rawMessage = event.getMessage().getContentRaw().strip();
        if (!rawMessage.startsWith("!")) return;

        rawMessage = rawMessage.split("!", 2)[1];
        String[] commandParts = rawMessage.split(" ");
        if (commandParts.length == 0) return;

        String commandName = commandParts[0].toLowerCase();
        if (!cachedCommands.containsKey(commandName)) return;

        String[] commandArgs = new String[commandParts.length - 1];
        if (commandArgs.length > 1) System.arraycopy(commandParts, 1, commandArgs, 0, commandParts.length);

        var command = cachedCommands.get(commandName);
        if (command.isProtected() && !Arrays.asList(admins).contains(event.getAuthor().getIdLong())) return;
        if (!command.isProtected() && event.getChannel().getIdLong() != RuntimeProperties.commandsChannelId()) return;
        command.execute(commandArgs, event);
    }

    public static void register(Class<? extends BotC> commandClass) {
        try {
            BotC command = commandClass.getConstructor().newInstance();
            cachedCommands.put(command.name(), command);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException ex) {
            ErrorHandler.warning("Could not register command named", commandClass.getName() + "!", ex);
        }
    }
}
