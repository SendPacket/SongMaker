package me.sendpacket.songmaker.handlers;

import me.sendpacket.songmaker.global_values;
import me.sendpacket.songmaker.player.beat.beat;
import me.sendpacket.songmaker.player.player;
import me.sendpacket.songmaker.player.song.song;
import me.sendpacket.songmaker.song_maker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class command_handler {

    public void display_help(Player p)
    {
        song_maker.get_logger_handler().send_message(p, "smk cr [name to rhyme] [name] (Create song)");
        song_maker.get_logger_handler().send_message(p, "smk pl [name] (Start player)");
        song_maker.get_logger_handler().send_message(p, "smk rb name] (Remove beat)");
        song_maker.get_logger_handler().send_message(p, "smk rs name] (Remove song)");
        song_maker.get_logger_handler().send_message(p, "smk cl (Clear player)");
        song_maker.get_logger_handler().send_message(p, "smk sp (Stop player)");
        song_maker.get_logger_handler().send_message(p, "smk ls (List player)");
        song_maker.get_logger_handler().send_message(p, "smk gui (Open GUI)");
        song_maker.get_logger_handler().send_message(p, "smk h (Help)");
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
                                case "cl":
                                    player.clear(p); // Clear ArrayLists
                                    break;
                                case "h":
                                    display_help(p); // Display help
                                    break;
                                case "sp":
                                    player.stop(p); // Stop player
                                    break;
                                case "rb":
                                    if (args[1].length() > 0) {
                                        player.remove_beat(p, args[1]); // Remove beat
                                    }
                                    break;
                                case "rs":
                                    if (args[1].length() > 0) {
                                        player.remove_song(p, args[1]); // Remove song
                                    }
                                    break;
                                case "cr":
                                    if (args[1].length() > 0 && args[2].length() > 0) {
                                        player.create_song(p, args[1], args[2]); // Create new song
                                    }
                                    break;
                                case "ls":
                                    // Display songs
                                    song_maker.get_logger_handler().send_message(p, "--------Song-List--------");
                                    for (song s : global_values.song_list) {
                                        song_maker.get_logger_handler().send_message(p, s.get_name());
                                    }
                                    // Display beats
                                    song_maker.get_logger_handler().send_message(p, "--------Beat-List--------");
                                    for (beat b : global_values.beat_list) {
                                        song_maker.get_logger_handler().send_message(p, b.get_name());
                                    }
                                    break;
                                case "pl":
                                    if (args[1].length() > 0 && args[2].length() > 0) {
                                        player.play(p, args[1], args[2]); // Play song and beat with name
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
