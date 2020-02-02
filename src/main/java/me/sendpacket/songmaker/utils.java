package me.sendpacket.songmaker;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.*;
import java.util.ArrayList;

public class utils {
    public static void load_words()
    {
        global_values.word_list.clear(); // We clear the word arraylist

        File f = song_maker.get_java_plugin().getDataFolder(); // We get the folder of the plugin

        if(!f.exists()) // If folder can't be found
        {
            f.mkdir(); // We create the plugin folder
        }

        f = new File(song_maker.get_java_plugin().getDataFolder() + "/words.txt"); // We point to the words file

        if(f.exists())
        {
            // We read line by line the file and save the text in the arraylist
            BufferedReader reader;
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
                String line = reader.readLine();
                while (line != null) { // If not empty continue
                    global_values.word_list.add(line); // We add the word to array
                    Bukkit.getServer().getLogger().info(line); // Write to console (only for debug purposes)
                    line = reader.readLine(); // Next line
                }
                reader.close(); // File closed
            } catch (IOException e) { }
        }
    }

    public static String get_rhyme(String word, String ignore_words_until)
    {
        String rhyme_end = ""; // Initialize variable
        if(word.length() > 3) // If the word is long enough to use substring
        {
            rhyme_end = word.substring(word.length() - 3); // We get the 3 letters at the end
        }else{
            rhyme_end = word; // We use the whole word
        }

        boolean found = false; // Initialize variable
        if(ignore_words_until.length() == 0) // If we don't ignore words (first time searching rhyme)
        {
            found = true; // No need to skip words
        }

        for(String w : global_values.word_list) // Words from file
        {
            if(w.equalsIgnoreCase(ignore_words_until)) // If the word is the one that we want to avoid
            {
                found = true; // We found the word
            }else {
                if (found) { // If the word was found
                    String word_rhyme = ""; // Initialize variable
                    if (w.length() > 3) { // If the word is long enough to use substring
                        word_rhyme = w.substring(w.length() - 3); // We get the 3 letters at the end
                    } else {
                        word_rhyme = w; // We use the whole word
                    }
                    if (word_rhyme.equals(rhyme_end)) { // If the last 3 letters are the same as the rhyme word
                        return w;
                    }
                }
            }
        }

        return ""; // Rhyme was not found
    }

    public static ItemStack create_inventory_item(Material material, String name, String...lore) {
        ItemStack item = new ItemStack(material, 1); // We initialize the variable
        ItemMeta meta = item.getItemMeta(); // We initialize the variable
        meta.setDisplayName(name); // We change the name of the item
        ArrayList<String> metaLore = new ArrayList<String>(); // We create arraylist for the lore
        for(String loreComments : lore) {
            metaLore.add(loreComments); // We add the lore to the arraylist
        }
        meta.setLore(metaLore); // We set the lore
        item.setItemMeta(meta); // We set the meta
        return item; // We return the create item
    }

    public static void add_item_background(ItemStack item, Inventory inv) {
        for (int i = 0; i < inv.getSize(); i++) { // For the inventory size
            inv.setItem(i,item); // We set an item for every slot
        }
    }
}
