package me.sendpacket.songmaker.player;

import java.util.HashMap;

public class beat {
    public beat(String name)
    {
        for(int i = 0; i < 20; i++)
        {
            this.get_sequence().put(i, 0);
        }
        this.name = name;
        this.playing = false;
    }

    HashMap<Integer, Integer> sequence = new HashMap<>();
    boolean playing;
    String name;

    public boolean isPlaying()
    {
        return this.playing;
    }
    public String get_name()
    {
        return this.name;
    }
    public HashMap<Integer, Integer> get_sequence()
    {
        return this.sequence;
    }
    public void set_sequence(HashMap<Integer, Integer> hash)
    {
        for(int i = 0; i < 20; i++)
        {
            this.sequence.put(i, hash.get(i));
        }
    }
    public void play()
    {
        playing = true;
    }
    public void stop()
    {
        playing = false;
    }
}
