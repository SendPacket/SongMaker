package me.sendpacket.songmaker.handlers;

import me.sendpacket.songmaker.global_values;
import me.sendpacket.songmaker.player.song.song;
import me.sendpacket.songmaker.player.song.song_line;
import me.sendpacket.songmaker.song_maker;
import me.sendpacket.songmaker.utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class command_handler {

    public void display_help(Player p)
    {
        song_maker.get_logger_handler().send_message(p, "---------");
        song_maker.get_logger_handler().send_message(p, "smk create [name to rhyme] [name]");
        song_maker.get_logger_handler().send_message(p, "smk remove name]");
        song_maker.get_logger_handler().send_message(p, "smk play [name]");
        song_maker.get_logger_handler().send_message(p, "smk help");
        song_maker.get_logger_handler().send_message(p, "smk stop");
        song_maker.get_logger_handler().send_message(p, "smk list");
        song_maker.get_logger_handler().send_message(p, "---------");
    }

    public void onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("smk")) {
            if (Bukkit.getServer().getPlayer(sender.getName()) != null) {
                Player p = (Player) sender;
                try {
                    if (args[0].length() > 0) {
                        switch (args[0]) {
                            case "help":
                                display_help(p);
                                break;
                            case "stop":
                                if (global_values.current_song != null) {
                                    global_values.current_song.stop();
                                    song_maker.get_logger_handler().send_message(p, "Song stopped.");
                                } else {
                                    song_maker.get_logger_handler().send_message(p, "Currently not playing song.");
                                }
                                break;
                            case "remove":
                                try {
                                    if (args[1].length() > 0) {
                                        boolean found = false;
                                        for (song s : global_values.song_list) {
                                            if (args[1].equalsIgnoreCase(s.get_name()))
                                            {
                                                global_values.song_list.remove(s);
                                                song_maker.get_logger_handler().send_message(p, "Song removed.");
                                                found = true;
                                            }
                                        }
                                        if(!found)
                                        {
                                            song_maker.get_logger_handler().send_message(p, "Song not found.");
                                        }
                                    }
                                }catch (Exception e)
                                {
                                    song_maker.get_logger_handler().send_message(p, "Usage: remove [name]");
                                }
                                break;
                            case "create":
                                try {
                                    if (args[1].length() > 0 && args[2].length() > 0) {
                                        song s = new song(args[2]);
                                        String word_to_rhyme = args[1];
                                        String last_word_add = args[1];
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
                                            song_maker.get_logger_handler().send_message(p, "Created song with the name [" + args[2] + "]");
                                        }else{
                                            song_maker.get_logger_handler().send_message(p, "A song with the name [" + args[2] + "] " + "already exists.");
                                        }
                                    }
                                } catch (Exception e) {
                                    song_maker.get_logger_handler().send_message(p, "Usage: create [name to rhyme] [name]");
                                }
                                break;
                            case "list":
                                for (song s : global_values.song_list) {
                                    song_maker.get_logger_handler().send_message(p, s.get_name());
                                }
                                break;
                            case "play":
                                try {
                                    if (args[1].length() > 0) {
                                        try {
                                            for(song s : global_values.song_list) {
                                                if(s.get_name().equalsIgnoreCase(args[1])) {
                                                    song_maker.get_logger_handler().send_message(p, "Starting to play song");
                                                    global_values.current_song = s;
                                                    global_values.current_song.play();
                                                }
                                            }
                                        } catch (Exception e) {
                                            song_maker.get_logger_handler().send_message(p, "Error");
                                        }
                                    }
                                } catch (Exception e) {
                                    song_maker.get_logger_handler().send_message(p, "Usage: play [index of song]");
                                }
                                break;
                            default:
                                song_maker.get_logger_handler().send_message(p, "Usage: SongMaker [play, remove, create, list, stop] [arguments]");
                                break;
                        }
                    }
                }catch (Exception e)
                {
                    display_help(p);
                }
            } else {
                song_maker.get_logger_handler().print_to_console("You can't use this command in the console.");
            }
        }
    }

}
