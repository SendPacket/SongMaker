package me.sendpacket.songmaker;

import me.sendpacket.easyguilib.gui_update;
import me.sendpacket.songmaker.gui.menu;
import me.sendpacket.songmaker.gui.menu_button;
import me.sendpacket.songmaker.handlers.command_handler;
import me.sendpacket.songmaker.handlers.logger_handler;
import me.sendpacket.songmaker.player.player;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class song_maker extends JavaPlugin {

    private static JavaPlugin plugin;
    private static command_handler cmd_handler;
    private static logger_handler logger_handler;
    private static menu menu_handler;

    @Override
    public void onEnable() {
        ////////////////////////////////////
        plugin = this; // Initialize variable
        ////////////////////////////////////
        menu_handler = new menu(); // Initialize variable
        cmd_handler = new command_handler(); // Initialize variable
        logger_handler = new logger_handler(); // Initialize variable
        ////////////////////////////////////
        menu_handler.load(); // Start GUI
        menu_button.menu_button_update(); // Start GUI Button Thread
        utils.load_words(); // Add words from text file to arraylist
        player.update_song(); // Start update thread
        player.update_beat(); // Start update thread
        ////////////////////////////////////
        Bukkit.getServer().getPluginManager().registerEvents(new gui_update(), this); // Register events for InventoryEvents
        ////////////////////////////////////
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        cmd_handler.onCommand(sender,command,label,args); // Redirect to command handler
        return true;
    }

    public static JavaPlugin get_java_plugin()
    {
        return plugin;
    }
    public static command_handler get_command_handler()
    {
        return cmd_handler;
    }
    public static logger_handler get_logger_handler()
    {
        return logger_handler;
    }
}
