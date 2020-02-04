package me.sendpacket.songmaker.gui;


import me.sendpacket.easyguilib.*;
import me.sendpacket.songmaker.global_values;
import me.sendpacket.songmaker.player.player;
import me.sendpacket.songmaker.song_maker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class menu_button {

    public static void update_main_window_buttons(Player p,gui_item item)
    {
        switch (item.get_id()) {
            case "pre_song":
                gui_utils.jump_to_window(p, 1);
                break;
            case "pre_beat":
                gui_utils.jump_to_window(p, 2);
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
    }

    public static void update_pre_song_buttons(Player p,gui_item item)
    {
        switch (item.get_id()) {
            case "Songs":
                gui_utils.jump_to_window(p, 3);
                break;
        }
    }

    public static void update_pre_beat_buttons(Player p,gui_item item)
    {
        switch (item.get_id()) {
            case "Beats":
                gui_utils.jump_to_window(p, 4);
                break;
        }
    }

    public static void menu_button_update()
    {
        Bukkit.getScheduler().runTaskTimer(song_maker.get_java_plugin(), () -> {
            // GUI 1
            for(gui_window w : menu.g.get_windows())
            {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    for (gui_item item : w.get_items()) {
                        if (item.is_pressed(p)) {
                            if(item.is_return_button())
                            {
                                gui_utils.jump_to_window(p, item.get_window_id_to_return());
                            }else {
                                switch (w.get_id()) {
                                    case 0:
                                        update_main_window_buttons(p, item);
                                        break;
                                    case 1:
                                        update_pre_song_buttons(p, item);
                                        break;
                                    case 2:
                                        update_pre_beat_buttons(p, item);
                                        break;
                                }
                            }
                            item.not_pressed(p);
                        }
                    }
                }
            }
        },0L,1L);
    }

}
