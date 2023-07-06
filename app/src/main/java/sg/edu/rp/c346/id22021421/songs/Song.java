package sg.edu.rp.c346.id22021421.songs;

public class Song {
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



    public String getname() { return name; }

    public String getSong() { return song; }

    public int getDate() { return date;}
    public int getStar() { return star; }
    public String toString() {
        return name + "\n" + song + "\n" + date + "\n" + star;
    }

}
