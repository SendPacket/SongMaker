package me.sendpacket.songmaker.player;

import me.sendpacket.songmaker.global_values;
import me.sendpacket.songmaker.player.song.song;
import me.sendpacket.songmaker.player.song.song_line;
import me.sendpacket.songmaker.song_maker;
import me.sendpacket.songmaker.utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class player {
    public static boolean change(String id)
    {
        for(song s : global_values.song_list) {
            if(s.get_name().equalsIgnoreCase(id)) {
                global_values.current_song = s;
                global_values.current_song.play();
                return true;
            }
        }
        return false;
    }
    public static void create(Player p, String to_rhyme, String song_name)
    {
        song s = new song(song_name);
        String word_to_rhyme = to_rhyme;
        String last_word_add = to_rhyme;
        for (int i = 0; i < 100; i++) {
            String new_w = utils.get_rhyme(word_to_rhyme, last_word_add);
            s.add_line(new_w, i);
            last_word_add = new_w;
        }
        boolean found = false;
        for(song s2 : global_values.song_list) {
            if(s2.get_name().equalsIgnoreCase(s.get_name()))
            {
                found = true;
            }
        }
        if(!found)
        {
            global_values.song_list.add(s);
            song_maker.get_logger_handler().send_message(p, "Created song with the name [" + song_name + "]");
        }else{
            song_maker.get_logger_handler().send_message(p, "A song with the name [" + song_name + "] " + "already exists");
        }
    }
    public static void remove(Player p, String song_name)
    {
        if (song_name.length() > 0) {
            boolean found = false;
            for (song s : global_values.song_list) {
                if (song_name.equalsIgnoreCase(s.get_name()))
                {
                    global_values.song_list.remove(s);
                    song_maker.get_logger_handler().send_message(p, "Song removed");
                    found = true;
                }
            }
            if(!found)
            {
                song_maker.get_logger_handler().send_message(p, "Song not found");
            }
        }
    }
    public static void clear(Player p)
    {
        global_values.song_list.clear();
        song_maker.get_logger_handler().send_message(p, "Songs and beats cleared");
    }
    public static void stop(Player p)
    {
        if (global_values.current_song != null) {
            global_values.current_song.stop();
            song_maker.get_logger_handler().send_message(p, "Song stopped");
        } else {
            song_maker.get_logger_handler().send_message(p, "Currently not playing song");
        }
    }
    public static void play(Player p, String song_name)
    {
        try {
            if(player.change(song_name))
            {
                song_maker.get_logger_handler().send_message(p, "Starting to play song");
            }else{
                song_maker.get_logger_handler().send_message(p, "Song not found");
            }
        } catch (Exception e) {
            song_maker.get_logger_handler().send_message(p, "Error");
        }
    }
    public static void update_song()
    {
        if(global_values.current_song != null)
        {
            if(global_values.current_song.isPlaying())
            {
                song_line line = global_values.current_song.get_lines().get(global_values.current_song.get_current_line());
                Bukkit.broadcastMessage(line.get_words());
                global_values.current_song.next_line();
            }
        }
    }
}
