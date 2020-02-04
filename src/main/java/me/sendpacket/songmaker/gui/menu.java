package me.sendpacket.songmaker.gui;

import me.sendpacket.easyguilib.gui;
import me.sendpacket.easyguilib.gui_item;
import me.sendpacket.easyguilib.gui_values;
import me.sendpacket.easyguilib.gui_window;
import org.bukkit.Material;

public class menu {

    public static gui g = new gui("gui_1", "[Menu]");

    public static void load()
    {
        gui_values.gui_list.add(g);

        gui_window main_menu = new gui_window("main_menu",0,9,g);

        main_menu.get_items().add(new gui_item("song_list","Songs","",1, Material.ORANGE_STAINED_GLASS_PANE));
        main_menu.get_items().add(new gui_item("beat_list","Beats","",4, Material.ORANGE_STAINED_GLASS_PANE));
        main_menu.get_items().add(new gui_item("song_play","Play","",7, Material.LIME_STAINED_GLASS_PANE));
        main_menu.get_items().add(new gui_item("song_stop","Stop","",8, Material.RED_STAINED_GLASS_PANE));

        g.set_main_window(main_menu);

        gui_window pre_beat_window = new gui_window(("pre_beat"), 1, 9, g);

        pre_beat_window.get_items().add(new gui_item("list_beats", "Beats", "",1, Material.BLUE_STAINED_GLASS_PANE));

        g.add_window(pre_beat_window);
    }

}
