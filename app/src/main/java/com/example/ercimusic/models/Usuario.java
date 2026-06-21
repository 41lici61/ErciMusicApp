package com.example.ercimusic.models;

public class Usuario {

    private int id;              // id_usuario
    private String pfp;
    private String nickname;
    private String name;
    private String lastname;
    private String pass;
    private int score;
    private int life;
    private int current_lvl;

    public Usuario() {}

    public Usuario(int id, String pfp, String nickname, String name,
                   String lastname, String pass,
                   int score, int life, int current_lvl) {

        this.id = id;
        this.pfp = pfp;
        this.nickname = nickname;
        this.name = name;
        this.lastname = lastname;
        this.pass = pass;
        this.score = score;
        this.life = life;
        this.current_lvl = current_lvl;
    }

    public Usuario(String pfp, String nickname, String name,
                   String lastname, String pass,
                   int score, int life, int current_lvl) {

        this.pfp = pfp;
        this.nickname = nickname;
        this.name = name;
        this.lastname = lastname;
        this.pass = pass;
        this.score = score;
        this.life = life;
        this.current_lvl = current_lvl;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPfp() { return pfp; }
    public void setPfp(String pfp) { this.pfp = pfp; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getPass() { return pass; }
    public void setPass(String pass) { this.pass = pass; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public int getLife() { return life; }
    public void setLife(int life) { this.life = life; }

    public int getCurrent_lvl() { return current_lvl; }
    public void setCurrent_lvl(int current_lvl) { this.current_lvl = current_lvl; }

    @Override
    public String toString() {
        return nickname + " - Score: " + score;
    }
}
