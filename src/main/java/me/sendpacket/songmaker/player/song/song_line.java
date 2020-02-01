package me.sendpacket.songmaker.player.song;

public class song_line {
    public song_line(String words, int id)
    {
        this.words = words;
        this.id = id;
    }
    String words;
    int id;

    public String get_words()
    {
        return words;
    }
    public int get_id()
    {
        return id;
    }
}
