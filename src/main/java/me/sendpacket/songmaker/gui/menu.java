package me.sendpacket.songmaker.gui;

import me.sendpacket.easyguilib.gui;
import me.sendpacket.easyguilib.gui_item;
import me.sendpacket.easyguilib.gui_values;
import me.sendpacket.easyguilib.gui_window;
import org.bukkit.Material;

public class menu {

    public static gui g = new gui("gui_1", "[Menu]");

    public static void main_window()
    {
        gui_window main_menu = new gui_window("main_menu",0,9,g);

        main_menu.get_items().add(new gui_item("pre_song","Songs","",1, Material.ORANGE_STAINED_GLASS_PANE));
        main_menu.get_items().add(new gui_item("pre_beat","Beats","",4, Material.ORANGE_STAINED_GLASS_PANE));
        main_menu.get_items().add(new gui_item("song_play","Play","",7, Material.LIME_STAINED_GLASS_PANE));
        main_menu.get_items().add(new gui_item("song_stop","Stop","",8, Material.RED_STAINED_GLASS_PANE));

        g.set_main_window(main_menu);
    }

    public static void pre_beat_window()
    {
        gui_window window = new gui_window(("pre_beat"), 2, 9, g);

        window.get_items().add(new gui_item("pre_beats", "Beats", "",1, Material.BLUE_STAINED_GLASS_PANE));
        window.get_items().add(new gui_item("", "Return", "",8, 0));

        g.add_window(window);
    }

    public static void pre_song_window()
    {
        gui_window window = new gui_window(("pre_song"), 1, 9, g);

        window.get_items().add(new gui_item("pre_songs", "Songs", "",1, Material.BLUE_STAINED_GLASS_PANE));
        window.get_items().add(new gui_item("", "Return", "",8, 0));

        g.add_window(window);
    }

    public static void load()
    {
        gui_values.gui_list.add(g);
        main_window();
        pre_beat_window();
        pre_song_window();
    }

}
