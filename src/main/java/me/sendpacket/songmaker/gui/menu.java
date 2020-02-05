package me.sendpacket.songmaker.gui;

import me.sendpacket.easyguilib.*;
import me.sendpacket.songmaker.global_values;
import me.sendpacket.songmaker.player.beat.beat;
import me.sendpacket.songmaker.player.song.song;
import me.sendpacket.songmaker.song_maker;
import org.bukkit.Material;

import java.util.HashMap;

public class menu {

    public static gui g = new gui("gui_1", "[Menu]");
    public static beat tmp_beat = new beat("tmp_beat");

    public static void main_window()
    {
        gui_window main_menu = new gui_window("main_menu",0,9,g);

        main_menu.get_items().add(new gui_item_button("pre_song","Songs","",1, Material.ORANGE_STAINED_GLASS_PANE));
        main_menu.get_items().add(new gui_item_button("pre_beat","Beats","",3, Material.ORANGE_STAINED_GLASS_PANE));
        main_menu.get_items().add(new gui_item_button("song_play","Play","",7, Material.LIME_STAINED_GLASS_PANE));
        main_menu.get_items().add(new gui_item_button("song_stop","Stop","",8, Material.RED_STAINED_GLASS_PANE));

        g.set_main_window(main_menu);
    }

    public static void update_beat_window()
    {
        gui_window window = gui_utils.get_window_by_id(g, 4);
        window.clear();

        int slot = 0;
        for(beat b : global_values.beat_list)
        {
            window.get_items().add(new gui_item_button("s_"+b.get_name(), b.get_name(), "",slot, Material.LIME_STAINED_GLASS_PANE));
            slot += 1;
        }
        window.get_items().add(new gui_item_returnbutton("", "Return", "",53, 2));
    }

    public static void update_song_window()
    {
        gui_window window = gui_utils.get_window_by_id(g, 3);
        window.clear();

        int slot = 0;
        for(song s : global_values.song_list)
        {
            window.get_items().add(new gui_item_button("s_"+s.get_name(), s.get_name(), "",slot, Material.LIME_STAINED_GLASS_PANE));
            slot += 1;
        }
        window.get_items().add(new gui_item_returnbutton("", "Return", "",53, 1));
    }

    public static void list_beat_window()
    {
        gui_window window = new gui_window(("list_beat"), 4, 54, g);

        g.add_window(window);

        update_beat_window();
    }

    public static void list_song_window()
    {
        gui_window window = new gui_window(("list_song"), 3, 54, g);

        g.add_window(window);

        update_song_window();
    }

    public static void pre_beat_window()
    {
        gui_window window = new gui_window(("pre_beat"), 2, 9, g);

        window.get_items().add(new gui_item_button("beats_list", "Beats", "",1, Material.BLUE_STAINED_GLASS_PANE));
        window.get_items().add(new gui_item_button("beats_create", "Create", "",3, Material.BLUE_STAINED_GLASS_PANE));
        window.get_items().add(new gui_item_returnbutton("", "Return", "",8, 0));

        g.add_window(window);
    }

    public static void pre_song_window()
    {
        gui_window window = new gui_window(("pre_song"), 1, 9, g);

        window.get_items().add(new gui_item_button("songs_list", "Songs", "",1, Material.BLUE_STAINED_GLASS_PANE));
        window.get_items().add(new gui_item_returnbutton("", "Return", "",8, 0));

        g.add_window(window);
    }

    public static void update_create_beat_window()
    {
        gui_window window = gui_utils.get_window_by_id(g, 5);
        window.clear();

        for (int i = 0; i < 20; i++) {

            Material mat = Material.GRAY_STAINED_GLASS_PANE;
            switch(tmp_beat.get_sequence().get(i).get_s_num())
            {
                case 1:
                    mat = Material.PURPLE_STAINED_GLASS_PANE;
                    break;
                case 2:
                    mat = Material.BLUE_STAINED_GLASS_PANE;
                    break;
                case 3:
                    mat = Material.CYAN_STAINED_GLASS_PANE;
                    break;
                case 4:
                    mat = Material.GREEN_STAINED_GLASS_PANE;
                    break;
                default:
                    break;
            }

            window.get_items().add(new gui_item_button("beat_tick_"+i,  tmp_beat.get_sequence().get(i).get_s_num() > 0 ? "Sound " + tmp_beat.get_sequence().get(i).get_s_num() : " ", tmp_beat.get_sequence().get(i).get_s_num() > 0 ?"Pitch " + tmp_beat.get_sequence().get(i).get_pitch() : " ", i,mat));
        }

        window.get_items().add(new gui_item_button("test_beat", "Test", "",23, Material.LIME_STAINED_GLASS_PANE));
        window.get_items().add(new gui_item_button("save_beat", "Save", "",24, Material.ORANGE_STAINED_GLASS_PANE));
        window.get_items().add(new gui_item_returnbutton("", "Return", "",26, 2));
    }

    public static void create_beat_window()
    {
        gui_window window = new gui_window(("create_beat"), 5, 27, g);

        g.add_window(window);

        update_create_beat_window();
    }

    public static void load()
    {
        gui_values.gui_list.add(g);

        main_window();

        pre_beat_window();
        pre_song_window();

        list_beat_window();
        list_song_window();

        create_beat_window();
    }

}
