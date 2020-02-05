package me.sendpacket.songmaker.gui;


import me.sendpacket.easyguilib.*;
import me.sendpacket.songmaker.global_values;
import me.sendpacket.songmaker.player.beat.beat;
import me.sendpacket.songmaker.player.beat.note;
import me.sendpacket.songmaker.player.player;
import me.sendpacket.songmaker.player.song.song;
import me.sendpacket.songmaker.song_maker;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
            case "songs_list":
                menu.update_song_window();
                gui_utils.jump_to_window(p, 3);
                break;
        }
    }

    public static void update_pre_beat_buttons(Player p,gui_item item)
    {
        switch (item.get_id()) {
            case "beats_list":
                menu.update_beat_window();
                gui_utils.jump_to_window(p, 4);
                break;
            case "beats_create":
                menu.update_create_beat_window();
                gui_utils.jump_to_window(p, 5);
                break;
        }
    }

    public static void update_song_selection_buttons(Player p,int slot)
    {
        int i = 0;
        for(song s : global_values.song_list)
        {
            if(slot == i)
            {
                global_values.current_song = s;
                song_maker.get_logger_handler().send_message(p, "Selected song: " + s.get_name());
                player.stop(p);
            }
            i += 1;
        }
    }

    public static void update_beat_selection_buttons(Player p,int slot)
    {
        int i = 0;
        for(beat b : global_values.beat_list)
        {
            if(slot == i)
            {
                global_values.current_beat = b;
                song_maker.get_logger_handler().send_message(p, "Selected beat: " + b.get_name());
                player.stop(p);
            }
            i += 1;
        }
    }

    public static void update_beat_creation_buttons(Player p,int slot,gui_item item,int press_type)
    {
        if(slot <= 20)
        {
            if(press_type == 1)
            {
                menu.tmp_beat.get_sequence().get(slot).set_s_num(menu.tmp_beat.get_sequence().get(slot).get_s_num() > 4 ? 0 : (menu.tmp_beat.get_sequence().get(slot).get_s_num() + 1));
            }else {
                if (press_type == 2) {
                    menu.tmp_beat.get_sequence().get(slot).set_pitch(menu.tmp_beat.get_sequence().get(slot).get_pitch() == 1 ? 2 : 1);
                } else {
                    if (press_type == 3) {
                        menu.tmp_beat.get_sequence().get(slot).set_s_num(0);
                        menu.tmp_beat.get_sequence().get(slot).set_pitch(1);
                    }
                }
            }

            switch (menu.tmp_beat.get_sequence().get(slot).get_s_num()) {
                case 1:
                    p.playSound(p.getLocation(), global_values.sound_1, 1.F, menu.tmp_beat.get_sequence().get(slot).get_pitch());
                    break;
                case 2:
                    p.playSound(p.getLocation(), global_values.sound_2, 1.F, menu.tmp_beat.get_sequence().get(slot).get_pitch());
                    break;
                case 3:
                    p.playSound(p.getLocation(), global_values.sound_3, 1.F, menu.tmp_beat.get_sequence().get(slot).get_pitch());
                    break;
                case 4:
                    p.playSound(p.getLocation(), global_values.sound_4, 1.F, menu.tmp_beat.get_sequence().get(slot).get_pitch());
                    break;
                default:
                    break;
            }
        }else{
            switch (item.get_id()) {
                case "test_beat":
                    player.stop(p);
                    global_values.current_beat_timer = 0;
                    global_values.testing_beat = true;
                    global_values.current_beat = menu.tmp_beat;
                    global_values.current_beat.play();
                    break;
                case "save_beat":
                    beat new_beat = new beat("beat" + global_values.beat_list.size());
                    new_beat.set_sequence(menu.tmp_beat.get_sequence());
                    global_values.beat_list.add(new_beat);
                    break;
                default:
                    break;
            }
        }

        menu.update_create_beat_window();
        gui_utils.jump_to_window(p, 5);
    }

    public static void menu_button_update()
    {
        Bukkit.getScheduler().runTaskTimer(song_maker.get_java_plugin(), () -> {
            // GUI 1
            try {
                for (gui_window w : menu.g.get_windows()) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        for (gui_item item : w.get_items()) {
                            if (ArrayUtils.contains(global_values.gui_button_list, item.get_type())) {
                                if (item.pressed_value(p) > 0) {
                                    if (item.get_type().equals(gui_item_type.return_button)) {
                                        gui_utils.jump_to_window(p, ((gui_item_returnbutton)item).get_window_id_to_return());
                                    } else {
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
                                            case 3:
                                                update_song_selection_buttons(p, item.get_slot());
                                                break;
                                            case 4:
                                                update_beat_selection_buttons(p, item.get_slot());
                                                break;
                                            case 5:
                                                update_beat_creation_buttons(p, item.get_slot(), item, item.pressed_value(p));
                                                break;
                                        }
                                    }
                                    item.not_pressed(p);
                                }
                            }
                        }
                    }
                }
            }catch (Exception e){};
        },0L,1L);
    }

}
