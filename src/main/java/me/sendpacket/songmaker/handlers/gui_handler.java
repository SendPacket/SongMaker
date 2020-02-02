package me.sendpacket.songmaker.handlers;

import me.sendpacket.songmaker.global_values;
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

    public void load_menu()
    {
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        main_inv = Bukkit.createInventory(null, 9, global_values.menu_prefix + ChatColor.DARK_GRAY + " Main Menu");
        utils.add_item_background(utils.create_inventory_item(Material.CYAN_STAINED_GLASS_PANE, " "), main_inv);
        main_inv.setItem(0,utils.create_inventory_item(Material.LIME_STAINED_GLASS_PANE,ChatColor.GOLD + "Songs"));
        main_inv.setItem(4,utils.create_inventory_item(Material.LIME_STAINED_GLASS_PANE,ChatColor.GOLD + "Beats"));
        main_inv.setItem(8,utils.create_inventory_item(Material.LIME_STAINED_GLASS_PANE,ChatColor.GOLD + "Stop"));
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        pre_song_inv = Bukkit.createInventory(null, 9, global_values.menu_prefix + ChatColor.DARK_GRAY + "Song - Menu");
        utils.add_item_background(utils.create_inventory_item(Material.CYAN_STAINED_GLASS_PANE, " "), pre_song_inv);
        pre_song_inv.setItem(1,utils.create_inventory_item(Material.LIME_STAINED_GLASS_PANE,ChatColor.GOLD + "List"));
        pre_song_inv.setItem(7,utils.create_inventory_item(Material.LIME_STAINED_GLASS_PANE,ChatColor.GOLD + "Create"));
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        song_inv = Bukkit.createInventory(null, 9, global_values.menu_prefix + ChatColor.DARK_GRAY + " List of songs");
        update_song_menu();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    public void update_song_menu()
    {
        int inv_size = 9;

        if(global_values.song_list.size() > inv_size)
        {
            inv_size += 9;
        }

        if(inv_size > 36)
        {
            inv_size = 36;
        }

        song_inv = Bukkit.createInventory(null, inv_size, global_values.menu_prefix + ChatColor.DARK_GRAY + " Songs");
        utils.add_item_background(utils.create_inventory_item(Material.CYAN_STAINED_GLASS_PANE, " "), song_inv);

        try {
            for (int i = 0; i < 36; i++) {
                song s = global_values.song_list.get(i);
                if (s != null) {
                    song_inv.setItem(i, utils.create_inventory_item(Material.LIME_STAINED_GLASS_PANE, s.get_name()));
                }
            }
        }catch (Exception e){}
    }

    public void change_inventory(Player p, Inventory inv)
    {
        p.closeInventory();
        p.openInventory(inv);
    }

    public void open_main_menu(Player p)
    {
        p.openInventory(main_inv);
    }

    @EventHandler
    public void onInventory(InventoryClickEvent e)
    {
        if(e.getWhoClicked() instanceof Player) {
            Player p = (Player) e.getWhoClicked();
            if (e.getInventory().equals(main_inv)) {
                switch (e.getSlot()) {
                    case 0:
                        change_inventory(p, pre_song_inv);
                        break;
                    case 4:
                        break;
                    case 8:
                        player.stop(p);
                        break;
                    default:
                        break;
                }
                e.setCancelled(true);
            }

            if (e.getInventory().equals(pre_song_inv)) {
                switch (e.getSlot()) {
                    case 1:
                        update_song_menu();
                        change_inventory(p, song_inv);
                        break;
                    default:
                        break;
                }
                e.setCancelled(true);
            }

            if (e.getInventory().equals(song_inv)) {
                ItemStack item = song_inv.getItem(e.getSlot());
                if(item != null) {
                    player.change(item.getItemMeta().getDisplayName());
                }
                e.setCancelled(true);
            }
        }
    }
}
