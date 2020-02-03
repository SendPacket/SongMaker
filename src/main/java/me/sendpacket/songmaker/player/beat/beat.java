package me.sendpacket.songmaker.player.beat;

import java.util.HashMap;

public class beat {
    public beat(String name)
    {
        for(int i = 0; i < 20; i++)
        {
            this.get_sequence().put(i, new note(0,1));
        }
        this.name = name;
        this.playing = false;
    }

    HashMap<Integer, note> sequence = new HashMap<>();
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
    public HashMap<Integer, note> get_sequence()
    {
        return this.sequence;
    }
    public void set_sequence(HashMap<Integer, note> hash)
    {
        for(int i = 0; i < 20; i++)
        {
            this.sequence.put(i, new note(hash.get(i).get_s_num(), hash.get(i).get_pitch()));
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
