package me.sendpacket.songmaker.player;

import me.sendpacket.songmaker.global_values;
import me.sendpacket.songmaker.player.song.song;
import me.sendpacket.songmaker.player.song.song_line;
import org.bukkit.Bukkit;

public class player {
    public static boolean change(int id)
    {
        int id_ = id - 1;
        if(global_values.song_list.size() >= id_) {
            global_values.current_song = global_values.song_list.get(id_);
            return true;
        }
        return false;
    }
    public static void play()
    {
        if(global_values.current_song != null)
        {
            global_values.current_song.play();
        }
    }
    public static void stop()
    {
        if(global_values.current_song != null)
        {
            global_values.current_song.stop();
        }
    }
    public static void update()
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
