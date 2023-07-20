package sg.edu.rp.c346.id22021421.songs;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String name;
    private String song;
    private int date;
    private int star;

    public Song(int id,String name, String song, int date,int star) {
        this.id=id;
        this.name = name;
        this.song = song;
        this.date = date;
        this.star = star;
    }


    public int getId(){return id;}
    public String getname() { return name; }

    public String getSong() { return song; }

    public int getDate() { return date;}
    public int getStar() { return star; }
    public void setSong(String song) {
        this.song=song;
    }
    public void setName(String name) {
        this.name=name;
    }

    public void setDate(int date) {
        this.date=date;
    }
    public void setStar(int star) {
        this.star=star;
    }

    public String toString() {
        String stars = null;
        if (star == 5) {
            stars = "*****";

        }
        else if (star == 4) {
            stars = "****";
        }
        else if (star == 3) {
            stars = "***";
        }
        else if (star == 2) {
            stars = "**";
        }
        else if (star == 1) {
            stars = "*";
        }
        return "Song : " + song + "\n" + "Sung by : " + name + "\n" + "Year : " + date + "\n" + "Ratings : " + stars;
    }

}
