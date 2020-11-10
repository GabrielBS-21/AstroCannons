package me.gabriel.astrocannons.utils.text;

import org.bukkit.ChatColor;

public class ColorHelper {

    public static char COLOR_CHAR = '&';

    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes(COLOR_CHAR, message);
    }

    public static String format(String message, Object... objects) {
        return String.format(format(message), objects);
    }

}
