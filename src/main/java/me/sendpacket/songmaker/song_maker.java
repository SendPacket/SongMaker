package me.sendpacket.songmaker;

import me.sendpacket.songmaker.player.player;
import me.sendpacket.songmaker.player.song.song;
import me.sendpacket.songmaker.player.song.song_line;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class song_maker extends JavaPlugin {

    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        update();
        utils.load_words();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void update()
    {
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            player.update();
        },0L,40L);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (command.getName())
        {
            case "stopp":
                if(global_values.current_song != null)
                {
                    global_values.current_song.stop();
                }
                break;
            case "create":
                if(args[0] != null) {
                    song s = new song("rimas-"+args[0]);
                    String word_to_rhyme = args[0];
                    String last_word_add = args[0];
                    for (int i = 0; i < 100; i++) {
                        String new_w = utils.get_rhyme(word_to_rhyme, last_word_add);
                        s.add_line(new_w, i);
                        last_word_add = new_w;
                    }
                    global_values.song_list.add(s);
                    sender.sendMessage("created song with the word -> " + args[0]);
                }
                break;
            case "list":
                if(args[0] != null)
                {
                    boolean found = false;
                    for(song s : global_values.song_list)
                    {
                        if(s.get_name().equalsIgnoreCase(args[0])) {
                            for (song_line lin : s.get_lines()) {
                                sender.sendMessage(lin.get_words());
                            }
                            found = true;
                        }
                    }
                    if(!found)
                    {
                        sender.sendMessage("Not found");
                    }
                }else{
                    for(song ss : global_values.song_list)
                    {
                        sender.sendMessage(ss.get_name() + " - lines:" + ss.get_lines().size());
                    }
                }
                break;
            case "play":
                if(args[0] != null) {
                    try {
                        song ss = global_values.song_list.get(Integer.parseInt(args[0]));
                        if (ss != null) {
                            sender.sendMessage("starting to play");
                            global_values.current_song = ss;
                            global_values.current_song.play();
                        }
                    }catch (Exception e)
                    {
                        sender.sendMessage("Error");
                    }
                }
                break;
        }
        return false;
    }
}
