package me.sendpacket.songmaker.gui;


import me.sendpacket.easyguilib.*;
import me.sendpacket.songmaker.global_values;
import me.sendpacket.songmaker.player.player;
import me.sendpacket.songmaker.song_maker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class menu_button {

    public static void menu_button_update()
    {
        Bukkit.getScheduler().runTaskTimer(song_maker.get_java_plugin(), () -> {
            // GUI 1
            for(gui_window w : menu.g.get_windows())
            {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    p.sendMessage(menu.g.get_player_list().get(p)+"");
                    for (gui_item item : w.get_items()) {
                        if (item.is_pressed(p)) {
                            switch (item.get_id()) {
                                case "song_list":
                                    gui_utils.jump_to_window(p, 1);
                                    break;
                                case "beat_list":

                                    break;
                                case "song_play":
                                    if(global_values.current_beat != null && global_values.current_song != null) {
                                        player.play(p, global_values.current_song.get_name(), global_values.current_beat.get_name()); // Start player
                                    }
                                    break;
                                case "song_stop":
                                    player.stop(p); // Stop player
                                    break;
                            }
                            p.sendMessage("Pressed");
                            item.not_pressed(p);
                        }else{

                        }
                    }
                }
            }
        },0L,1L);
    }

}
