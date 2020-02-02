package me.sendpacket.songmaker;

import org.bukkit.Bukkit;

import java.io.*;

public class utils {
    public static void load_words()
    {
        global_values.word_list.clear();

        File f = song_maker.get_java_plugin().getDataFolder();

        if(!song_maker.get_java_plugin().getDataFolder().exists())
        {
            song_maker.get_java_plugin().getDataFolder().mkdir();
        }

        f = new File(song_maker.get_java_plugin().getDataFolder() + "/words.txt");

        if(f.exists())
        {
            BufferedReader reader;
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
                String line = reader.readLine();
                while (line != null) {
                    global_values.word_list.add(line);
                    Bukkit.getServer().getLogger().info(line);
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) { }
        }
    }

    public static String get_rhyme(String word, String ignore_words_until)
    {
        String rhyme_end = "";
        if(word.length() > 3)
        {
            rhyme_end = word.substring(word.length() - 3);
        }else{
            rhyme_end = word;
        }

        boolean found = false;
        if(ignore_words_until.length() == 0)
        {
            found = true;
        }

        for(String w : global_values.word_list)
        {
            if(w.equalsIgnoreCase(ignore_words_until))
            {
                found = true;
            }else {
                if (found) {
                    String word_rhyme = "";
                    if (w.length() > 3) {
                        word_rhyme = w.substring(w.length() - 3);
                    } else {
                        word_rhyme = w;
                    }
                    if (word_rhyme.equals(rhyme_end)) {
                        return w;
                    }
                }
            }
        }

        return "";
    }

}
