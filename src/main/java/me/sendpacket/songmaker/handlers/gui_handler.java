package me.sendpacket.songmaker.handlers;

import me.sendpacket.songmaker.global_values;
import me.sendpacket.songmaker.player.beat;
import me.sendpacket.songmaker.player.player;
import me.sendpacket.songmaker.player.song.song;
import me.sendpacket.songmaker.song_maker;
import me.sendpacket.songmaker.utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class gui_handler implements Listener {
    static Inventory main_inv;

    static Inventory pre_song_inv;
    static Inventory song_inv;

    static Inventory create_beat_inv;
    static Inventory pre_beat_inv;
    static Inventory beat_inv;

    static beat tmp_beat;

    public void load_menu()
    {
        tmp_beat = new beat("tmp_beat");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        main_inv = Bukkit.createInventory(null, 9, global_values.menu_prefix + ChatColor.DARK_GRAY + " Main Menu");
        utils.add_item_background(utils.create_inventory_item(Material.CYAN_STAINED_GLASS_PANE, " "), main_inv);
        main_inv.setItem(0,utils.create_inventory_item(Material.LIME_STAINED_GLASS_PANE,ChatColor.GOLD + " Songs"));
        main_inv.setItem(4,utils.create_inventory_item(Material.LIME_STAINED_GLASS_PANE,ChatColor.GOLD + " Beats"));
        main_inv.setItem(7,utils.create_inventory_item(Material.LIME_STAINED_GLASS_PANE,ChatColor.GREEN + " Play"));
        main_inv.setItem(8,utils.create_inventory_item(Material.RED_STAINED_GLASS_PANE,ChatColor.RED + " Stop"));
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        pre_song_inv = Bukkit.createInventory(null, 9, global_values.menu_prefix + ChatColor.DARK_GRAY + " Song - Menu");
        utils.add_item_background(utils.create_inventory_item(Material.CYAN_STAINED_GLASS_PANE, " "), pre_song_inv);
        pre_song_inv.setItem(1,utils.create_inventory_item(Material.LIME_STAINED_GLASS_PANE,ChatColor.GOLD + " List"));
        pre_song_inv.setItem(3,utils.create_inventory_item(Material.LIME_STAINED_GLASS_PANE,ChatColor.GOLD + " Create"));
        pre_song_inv.setItem(8,utils.create_inventory_item(Material.RED_STAINED_GLASS_PANE,ChatColor.RED + " Return"));
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        song_inv = Bukkit.createInventory(null, 9, global_values.menu_prefix + ChatColor.DARK_GRAY + " List of songs");
        update_song_menu();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        pre_beat_inv = Bukkit.createInventory(null, 9, global_values.menu_prefix + ChatColor.DARK_GRAY + " Beat - Menu");
        utils.add_item_background(utils.create_inventory_item(Material.CYAN_STAINED_GLASS_PANE, " "), pre_beat_inv);
        pre_beat_inv.setItem(1,utils.create_inventory_item(Material.LIME_STAINED_GLASS_PANE,ChatColor.GOLD + " List"));
        pre_beat_inv.setItem(3,utils.create_inventory_item(Material.LIME_STAINED_GLASS_PANE,ChatColor.GOLD + " Create"));
        pre_beat_inv.setItem(8,utils.create_inventory_item(Material.RED_STAINED_GLASS_PANE,ChatColor.RED + " Return"));
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        beat_inv = Bukkit.createInventory(null, 9, global_values.menu_prefix + ChatColor.DARK_GRAY + " List of beats");
        update_beat_menu();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        create_beat_inv = Bukkit.createInventory(null, 36, global_values.menu_prefix + ChatColor.DARK_GRAY + " Create beat");
        update_create_beat_menu();
    }

    public void update_create_beat_menu()
    {
        create_beat_inv = Bukkit.createInventory(null, 36, global_values.menu_prefix + ChatColor.DARK_GRAY + " Create beat");
        utils.add_item_background(utils.create_inventory_item(Material.GRAY_STAINED_GLASS_PANE, " "), create_beat_inv);
        create_beat_inv.setItem(35, utils.create_inventory_item(Material.ORANGE_STAINED_GLASS_PANE, ChatColor.GREEN+" Save"));
        create_beat_inv.setItem(34, utils.create_inventory_item(Material.RED_STAINED_GLASS_PANE, ChatColor.RED+" Return"));
        for(int i = 0; i < 20; i++)
        {
            Material mat = Material.RED_STAINED_GLASS_PANE;
            switch(tmp_beat.get_sequence().get(i))
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
                default:
                    break;
            }
            create_beat_inv.setItem(i, utils.create_inventory_item(mat, "Sound " + tmp_beat.get_sequence().get(i)));
        }
    }

    public void update_song_menu()
    {
        int inv_size = 9; // Initialize variable

        while(global_values.song_list.size() > inv_size) // If song amount higher than inventory size
        {
            inv_size += 9; // Make inventory bigger
        }

        if(inv_size > 36) // If inventory size too high
        {
            inv_size = 36; // Limit inventory size
        }

        song_inv = Bukkit.createInventory(null, inv_size, global_values.menu_prefix + ChatColor.DARK_GRAY + " Songs");
        utils.add_item_background(utils.create_inventory_item(Material.CYAN_STAINED_GLASS_PANE, " "), song_inv);

        try {
            for (int i = 0; i < 36; i++) { // Fill inventory with songs in arraylist
                song s = global_values.song_list.get(i);
                if (s != null) {
                    song_inv.setItem(i, utils.create_inventory_item(Material.LIME_STAINED_GLASS_PANE, s.get_name()));
                }
            }
        }catch (Exception e){}

        song_inv.setItem(inv_size - 1, utils.create_inventory_item(Material.RED_STAINED_GLASS_PANE, ChatColor.RED+"Return"));
    }

    public void update_beat_menu()
    {
        int inv_size = 9; // Initialize variable

        while(global_values.beat_list.size() > inv_size) // If song amount higher than inventory size
        {
            inv_size += 9; // Make inventory bigger
        }

        if(inv_size > 36) // If inventory size too high
        {
            inv_size = 36; // Limit inventory size
        }

        beat_inv = Bukkit.createInventory(null, inv_size, global_values.menu_prefix + ChatColor.DARK_GRAY + " Beats");
        utils.add_item_background(utils.create_inventory_item(Material.CYAN_STAINED_GLASS_PANE, " "), beat_inv);

        try {
            for (int i = 0; i < 36; i++) { // Fill inventory with songs in arraylist
                beat b = global_values.beat_list.get(i);
                if (b != null) {
                    String sequence_string = "[";
                    for(int x = 0; x < 20; x++)
                    {
                        sequence_string += (x == 19 ? b.get_sequence().get(x) + "]" : b.get_sequence().get(x) + ",");
                    }
                    beat_inv.setItem(i, utils.create_inventory_item(Material.LIME_STAINED_GLASS_PANE, b.get_name(), ChatColor.GOLD+sequence_string));
                }
            }
        }catch (Exception e){}

        beat_inv.setItem(inv_size - 1, utils.create_inventory_item(Material.RED_STAINED_GLASS_PANE, ChatColor.RED+"Return"));
    }


    public void change_inventory(Player p, Inventory inv)
    {
        // Close current and open new inventory
        p.closeInventory();
        p.openInventory(inv);
    }

    public void open_main_menu(Player p)
    {
        // Open main inventory
        p.openInventory(main_inv);
    }

    @EventHandler
    public void onInventory(InventoryClickEvent e)
    {
        if(e.getWhoClicked() instanceof Player) { // If player
            Player p = (Player) e.getWhoClicked();

            if (e.getInventory().equals(main_inv)) { // If inventory is main menu
                switch (e.getSlot()) {
                    case 0:
                        change_inventory(p, pre_song_inv);
                        break;
                    case 4:
                        change_inventory(p, pre_beat_inv);
                        break;
                    case 7:
                        if(global_values.current_beat != null && global_values.current_song != null) {
                            player.play(p, global_values.current_song.get_name(), global_values.current_beat.get_name()); // Start player
                        }
                        break;
                    case 8:
                        player.stop(p); // Stop player
                        break;
                    default:
                        break;
                }
                e.setCancelled(true);
            }

            if (e.getInventory().equals(pre_song_inv)) {
                switch (e.getSlot()) {
                    case 1:
                        update_song_menu(); // Update song inventory
                        change_inventory(p, song_inv);
                        break;
                    case 8:
                        change_inventory(p, main_inv);
                        break;
                    default:
                        break;
                }
                e.setCancelled(true);
            }

            if (e.getInventory().equals(song_inv)) {
                if(e.getSlot() == (beat_inv.getSize() - 1))
                {
                    change_inventory(p, pre_song_inv);
                }else {
                    ItemStack item = song_inv.getItem(e.getSlot());
                    if (item != null) {
                        player.change_song(item.getItemMeta().getDisplayName(), false); // Change song to slot name
                        player.stop(p);
                    }
                }
                e.setCancelled(true);
            }

            if(e.getInventory().equals(pre_beat_inv))
            {
                switch (e.getSlot()) {
                    case 1:
                        update_beat_menu(); // Update beat inventory
                        change_inventory(p, beat_inv);
                        break;
                    case 3:
                        update_create_beat_menu();
                        change_inventory(p, create_beat_inv);
                        break;
                    case 8:
                        change_inventory(p, main_inv);
                        break;
                    default:
                        break;
                }
                e.setCancelled(true);
            }

            if(e.getInventory().equals(beat_inv))
            {
                if(e.getSlot() == (beat_inv.getSize() - 1))
                {
                    change_inventory(p, pre_beat_inv);
                }else {
                    ItemStack item = beat_inv.getItem(e.getSlot());
                    if (item != null) {
                        for (beat b : global_values.beat_list) {
                            if (b.get_name().equals(item.getItemMeta().getDisplayName())) {
                                global_values.current_beat = b;
                                player.stop(p);
                            }
                        }
                    }
                }
                e.setCancelled(true);
            }

            if(e.getInventory().equals(create_beat_inv))
            {
                if(e.getSlot() == (create_beat_inv.getSize() - 2))
                {
                    change_inventory(p, pre_beat_inv);
                }else {
                    if (e.getSlot() <= 20) {
                        int next_i = tmp_beat.get_sequence().get(e.getSlot()) + 1;
                        if (e.isShiftClick()) {
                            tmp_beat.get_sequence().put(e.getSlot(), 0);
                        } else {
                            if (next_i > 3) next_i = 0;
                            tmp_beat.get_sequence().put(e.getSlot(), next_i);
                        }
                        update_create_beat_menu();
                    } else {
                        switch (e.getSlot()) {
                            case 35:
                                beat new_beat = new beat("beat" + global_values.beat_list.size());
                                new_beat.set_sequence(tmp_beat.get_sequence());
                                global_values.beat_list.add(new_beat);
                                update_create_beat_menu();
                                break;
                            default:
                                break;
                        }
                    }
                    change_inventory(p, create_beat_inv);
                }
                e.setCancelled(true);
            }
        }
    }
}
