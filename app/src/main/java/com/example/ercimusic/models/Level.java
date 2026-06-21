package com.example.ercimusic.models;

public class Level {

    private static int i=0;
    private int id_level;
    private int unlock;

    public Level(int id_level, int unlock) {
        this.id_level = id_level;
        this.unlock = unlock;
    }

    public Level(int unlock) {
        i++;
        this.id_level = i;
        this.unlock = unlock;
    }

    public Level() {
    }

    public int getId_level() {
        return id_level;
    }

    public void setId_level(int id_level) {
        this.id_level = id_level;
    }

    public int getUnlock() {
        return unlock;
    }

    public void setUnlock(int unlock) {
        this.unlock = unlock;
    }

    @Override
    public String toString() {
        return "Level{" +
                "id_level=" + id_level +
                ", unlock=" + unlock +
                '}';
    }
}
