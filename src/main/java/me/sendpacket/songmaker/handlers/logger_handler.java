package me.sendpacket.songmaker.handlers;

import me.sendpacket.songmaker.global_values;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class logger_handler {

    public void send_message(Player p, String msg)
    {
        p.sendMessage(global_values.chat_prefix + " " + ChatColor.WHITE + msg);
    }

    public void broadcast_message(String msg)
    {
        Bukkit.broadcastMessage(global_values.chat_prefix + " " + ChatColor.WHITE + msg);
    }

    public void print_to_console(String msg)
    {
        Bukkit.getServer().getLogger().info(global_values.console_prefix + " " + msg);
    }
}
