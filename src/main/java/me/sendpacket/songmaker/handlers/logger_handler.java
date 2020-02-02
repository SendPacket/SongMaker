package me.sendpacket.songmaker.handlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class logger_handler {
    public String chat_prefix = ChatColor.WHITE+"["+ChatColor.GREEN+"SongMaker"+ChatColor.WHITE+"]";

    public void send_message(Player p, String msg)
    {
        p.sendMessage(chat_prefix + " " + ChatColor.WHITE + msg);
    }

    public void broadcast_message(String msg)
    {
        Bukkit.broadcastMessage(chat_prefix + " " + ChatColor.WHITE + msg);
    }

    public void print_to_console(String msg)
    {
        Bukkit.getServer().getLogger().info(chat_prefix + " " + ChatColor.WHITE + msg);
    }
}
