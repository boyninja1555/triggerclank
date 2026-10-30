package com.boyninja1555.triggerclank;

import com.boyninja1555.triggerclank.channelshit.Counting;
import com.boyninja1555.triggerclank.commands.*;
import com.boyninja1555.triggerclank.lib.PersistentData;
import com.boyninja1555.triggerclank.lib.RuntimeProperties;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Triggerclank extends ListenerAdapter {

    public void init() {
        BotCommands.register(HelpC.class);
        BotCommands.register(InviteC.class);
        BotCommands.register(DownloadC.class);
        BotCommands.register(GithubC.class);
        BotCommands.register(SayC.class);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        if (event.getChannel().getIdLong() == RuntimeProperties.countingChannelId()) {
            new Counting().onMessageReceived(event);
            return;
        }

        BotCommands.handle(event);
    }

    private static JDA bot;

    public static JDA bot() {
        return bot;
    }

    static void main() {
        RuntimeProperties.init();
        PersistentData.load();

        var triggerclank = new Triggerclank();
        bot = JDABuilder.createDefault(RuntimeProperties.botToken()).enableIntents(GatewayIntent.MESSAGE_CONTENT).setActivity(Activity.playing("Obeying your every command")).addEventListeners(triggerclank).build();
        triggerclank.init();
    }
}
