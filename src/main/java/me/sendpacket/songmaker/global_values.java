package me.sendpacket.songmaker;

import me.sendpacket.songmaker.player.beat.beat;
import me.sendpacket.songmaker.player.song.song;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

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
    public static boolean testing_beat;
    public static Sound sound_1 = Sound.BLOCK_NOTE_BLOCK_BASS;
    public static Sound sound_2 = Sound.BLOCK_NOTE_BLOCK_BANJO;
    public static Sound sound_3 = Sound.BLOCK_NOTE_BLOCK_CHIME;
    public static Sound sound_4 = Sound.BLOCK_NOTE_BLOCK_HAT;
}
