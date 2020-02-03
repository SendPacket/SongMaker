package me.sendpacket.songmaker;

import me.sendpacket.songmaker.player.beat;
import me.sendpacket.songmaker.player.song.song;
import org.bukkit.ChatColor;

import java.util.ArrayList;

public class global_values {
    public static ArrayList<String> word_list = new ArrayList<String>();
    public static ArrayList<song> song_list = new ArrayList<song>();
    public static ArrayList<beat> beat_list = new ArrayList<beat>();
    public static String chat_prefix = ChatColor.WHITE+"["+ChatColor.GREEN+"SongMaker"+ChatColor.WHITE+"]";
    public static String console_prefix = "[SongMaker]";
    public static String menu_prefix = ChatColor.GRAY+"["+ChatColor.DARK_GRAY +"SongMaker"+ChatColor.GRAY+"]";
    public static int current_beat_timer;
    public static song current_song;
    public static beat current_beat;
}
