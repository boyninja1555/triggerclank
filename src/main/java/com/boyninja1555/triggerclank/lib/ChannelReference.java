package com.boyninja1555.triggerclank.lib;

import org.jetbrains.annotations.NotNull;

public class ChannelReference {
    public static @NotNull String text(long id) {
        return "<#" + id + ">";
    }
}
