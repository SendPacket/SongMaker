package me.sendpacket.songmaker;

import me.sendpacket.songmaker.handlers.command_handler;
import me.sendpacket.songmaker.handlers.gui_handler;
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
    private static gui_handler gui_handler;

    @Override
    public void onEnable() {
        ////////////////////////////////////
        plugin = this; // Initialize variable
        ////////////////////////////////////
        cmd_handler = new command_handler(); // Initialize variable
        logger_handler = new logger_handler(); // Initialize variable
        gui_handler = new gui_handler(); // Initialize variable
        gui_handler.load_menu(); // Initialize variables and set inventory contents
        ////////////////////////////////////
        utils.load_words(); // Add words from text file to arraylist
        player.update_song(); // Start update thread
        player.update_beat(); // Start update thread
        ////////////////////////////////////
        Bukkit.getServer().getPluginManager().registerEvents(gui_handler, this); // Register events for InventoryClickEvent
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
    public static gui_handler get_gui_handler()
    {
        return gui_handler;
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
