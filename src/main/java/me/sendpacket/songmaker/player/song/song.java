package me.sendpacket.songmaker.player.song;

import java.util.ArrayList;

public class song {
    public song(String name)
    {
        this.lines.clear();
        this.name = name;
        this.playing = false;
        this.current_line = 0;
    }

    String name;
    boolean playing;
    ArrayList<song_line> lines = new ArrayList<song_line>();
    int current_line;

    public String get_name()
    {
        return this.name;
    }
    public ArrayList<song_line> get_lines()
    {
        return lines;
    }
    public boolean isPlaying()
    {
        return this.playing;
    }
    public void add_line(String words, int id)
    {
        lines.add(new song_line(words, id));
    }
    public int get_current_line()
    {
        return this.current_line;
    }
    public void next_line()
    {
        if((this.current_line + 1) > lines.size())
        {
            this.current_line = 0;
            this.stop();
        }else{
            this.current_line += 1;
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
