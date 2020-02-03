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
    public static boolean change_song(String id, boolean play)
    {
        for(song s : global_values.song_list) {
            if(s.get_name().equalsIgnoreCase(id)) { // If song exists
                global_values.current_song = s; // Set current song
                if(play)global_values.current_song.play(); // Play song
                return true; // Song found
            }
        }
        return false; // Song not found (can't play it)
    }
    public static boolean change_beat(String id, boolean play)
    {
        for(beat b : global_values.beat_list) {
            if(b.get_name().equalsIgnoreCase(id)) { // If beat exists
                global_values.current_beat = b; // Set current beat
                if(play)global_values.current_beat.play(); // Play beat
                return true; // Beat found
            }
        }
        return false; // Beat not found (can't play it)
    }
    public static void create_song(Player p, String to_rhyme, String song_name)
    {
        song s = new song(song_name); // Create new song
        String word_to_rhyme = to_rhyme; // Initialize variable
        String last_word_add = to_rhyme; // Initialize variable
        for (int i = 0; i < 100; i++) { // Create 100 words that rhyme
            String new_w = utils.get_rhyme(word_to_rhyme, last_word_add); // Get new rhyme
            if(new_w.length() > 0) { // If rhyme lenght is more than 3
                s.add_line(new_w, i); // Add rhyme to new song
            }
            last_word_add = new_w; // Set ignore rhyme to last rhyme found
        }
        boolean found = false; // Initialize variable
        for(song s2 : global_values.song_list) {
            if(s2.get_name().equalsIgnoreCase(s.get_name())) // If existing song has same name
            {
                found = true; // We found it
            }
        }
        if(!found) // If song doesn´t exist
        {
            global_values.song_list.add(s); // Add song to arraylist
            song_maker.get_logger_handler().send_message(p, "Created song with the name [" + song_name + "]");
        }else{ // Don't add song
            song_maker.get_logger_handler().send_message(p, "A song with the name [" + song_name + "] " + "already exists");
        }
    }
    public static void remove_song(Player p, String song_name)
    {
        if (song_name.length() > 0) { // Song long enough
            boolean found = false; // Initialize variable
            for (song s : global_values.song_list) {
                if (song_name.equalsIgnoreCase(s.get_name())) // If song with given name found
                {
                    global_values.song_list.remove(s); // Remove song from arraylist
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
    public static void remove_beat(Player p, String beat_name)
    {
        if (beat_name.length() > 0) { // Song long enough
            boolean found = false; // Initialize variable
            for (beat b : global_values.beat_list) {
                if (beat_name.equalsIgnoreCase(b.get_name())) // If song with given name found
                {
                    global_values.beat_list.remove(b); // Remove song from arraylist
                    song_maker.get_logger_handler().send_message(p, "Beat removed");
                    found = true;
                }
            }
            if(!found)
            {
                song_maker.get_logger_handler().send_message(p, "Beat not found");
            }
        }
    }
    public static void clear(Player p)
    {
        global_values.song_list.clear(); // Clean arraylist
        global_values.beat_list.clear(); // Clean arraylist
        song_maker.get_logger_handler().send_message(p, "Songs and beats cleared");
    }
    public static void stop(Player p)
    {
        if(global_values.current_beat != null){ // If playing
            global_values.current_beat.stop(); // Stop
        }
        if (global_values.current_song != null) { // If playing
            global_values.current_song.stop(); // Stop
        }
        song_maker.get_logger_handler().send_message(p, "Song and beat stopped");
    }
    public static void play(Player p, String song_name, String beat_name)
    {
        try {
            if(player.change_song(song_name, true) && player.change_beat(beat_name, true)) // If song and beat was found
            {
                song_maker.get_logger_handler().send_message(p, "Starting to play beat & song");
            }else{ // Song not found
                song_maker.get_logger_handler().send_message(p, "Beat or song not found");
            }
        } catch (Exception e) { // Error
            song_maker.get_logger_handler().send_message(p, "Error");
        }
    }
    public static void update_song()
    {
        Bukkit.getScheduler().runTaskTimer(song_maker.get_java_plugin(), () -> {
            if(global_values.current_song != null)
            {
                if(global_values.current_song.isPlaying())
                {
                    song_line line = global_values.current_song.get_lines().get(global_values.current_song.get_current_line()); // Get words to display
                    Bukkit.broadcastMessage(line.get_words()); // Display words
                    global_values.current_song.next_line(); // Set next words
                }
            }
        },0L,40L);
    }
    public static void update_beat()
    {
        Bukkit.getScheduler().runTaskTimer(song_maker.get_java_plugin(), () -> {
            if(global_values.current_beat != null)
            {
                if(global_values.current_beat.isPlaying())
                {
                    global_values.current_beat_timer += 1;
                    if(global_values.current_beat_timer > 19)
                    {
                        global_values.current_beat_timer = 0;
                    }

                    for(Player p : Bukkit.getOnlinePlayers()) {
                        switch (global_values.current_beat.get_sequence().get(global_values.current_beat_timer)) {
                            case 1:
                                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.F, 1.F);
                                break;
                            case 2:
                                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.F, 1.F);
                                break;
                            case 3:
                                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1.F, 1.F);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        },0L,1L);
    }
}
