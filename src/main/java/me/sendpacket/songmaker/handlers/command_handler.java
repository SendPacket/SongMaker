package me.sendpacket.songmaker.handlers;

import me.sendpacket.songmaker.global_values;
import me.sendpacket.songmaker.player.player;
import me.sendpacket.songmaker.player.song.song;
import me.sendpacket.songmaker.song_maker;
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
        song_maker.get_logger_handler().send_message(p, "smk clear");
        song_maker.get_logger_handler().send_message(p, "smk help");
        song_maker.get_logger_handler().send_message(p, "smk stop");
        song_maker.get_logger_handler().send_message(p, "smk list");
        song_maker.get_logger_handler().send_message(p, "smk gui");
        song_maker.get_logger_handler().send_message(p, "---------");
    }

    public void onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("smk")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                try {
                    if (args[0].length() > 0) {
                        try {
                            switch (args[0]) {
                                case "gui":
                                    song_maker.get_gui_handler().open_main_menu(p); // Open GUI
                                    break;
                                case "clear":
                                    player.clear(p); // Clear ArrayLists
                                    break;
                                case "help":
                                    display_help(p); // Display help
                                    break;
                                case "stop":
                                    player.stop(p); // Stop song
                                    break;
                                case "remove":
                                    if (args[1].length() > 0) {
                                        player.remove(p, args[1]); // Remove song
                                    }
                                    break;
                                case "create":
                                    if (args[1].length() > 0 && args[2].length() > 0) {
                                        player.create(p, args[1], args[2]); // Create new song
                                    }
                                    break;
                                case "list":
                                    // Display songs
                                    song_maker.get_logger_handler().send_message(p, "--------Song-List--------");
                                    for (song s : global_values.song_list) {
                                        song_maker.get_logger_handler().send_message(p, s.get_name());
                                    }
                                    break;
                                case "play":
                                    if (args[1].length() > 0) {
                                        player.play(p, args[1]); // Play song with name
                                    }
                                    break;
                                default:
                                    song_maker.get_logger_handler().send_message(p, "Usage: SongMaker [play, remove, create, list, stop, gui, clear] [arguments] [arguments]");
                                    break;
                            }
                        }catch (Exception e)
                        {
                            song_maker.get_logger_handler().send_message(p, "Invalid arguments " + e.getCause());
                        }
                    }
                }catch (Exception e)
                {
                    display_help(p);
                }
            } else {
                song_maker.get_logger_handler().print_to_console("You can't use this command in the console");
            }
        }
    }

}
