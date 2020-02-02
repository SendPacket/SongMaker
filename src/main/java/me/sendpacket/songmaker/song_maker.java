package me.sendpacket.songmaker;

import me.sendpacket.songmaker.handlers.command_handler;
import me.sendpacket.songmaker.handlers.gui_handler;
import me.sendpacket.songmaker.handlers.logger_handler;
import me.sendpacket.songmaker.player.player;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class song_maker extends JavaPlugin {

    private static JavaPlugin plugin;
    private static command_handler cmd_handler;
    private static logger_handler logger_handler;
    private static gui_handler gui_handler;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        cmd_handler = new command_handler();
        logger_handler = new logger_handler();
        gui_handler = new gui_handler();
        Bukkit.getServer().getPluginManager().registerEvents(gui_handler, this);
        gui_handler.load_menu();
        utils.load_words();

        update();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void update()
    {
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            player.update_song();
        },0L,40L);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        cmd_handler.onCommand(sender,command,label,args);
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
