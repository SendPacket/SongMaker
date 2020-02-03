package me.sendpacket.songmaker.player.beat;

public class note {
    public note(int s_num, int pitch)
    {
        this.s_num = s_num;
        this.pitch = pitch;
    }

    int s_num, pitch;

    public int get_s_num()
    {
        return this.s_num;
    }

    public int get_pitch()
    {
        return this.pitch;
    }

    public void set_s_num(int num)
    {
        this.s_num = num;
    }

    public void set_pitch(int pitch)
    {
        this.pitch = pitch;
    }
}
