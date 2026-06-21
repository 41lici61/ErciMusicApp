package com.example.ercimusic.models;

public class Question {

    private static int i=0;
    private int id_question;
    private int level;
    private String texto;
    private String canswer;
    private String answer1;

    private String answer2;

    private String answer3;

    private String answer4;
    private String song;

    public Question() {
    }

    public Question(int id_question, int level, String texto, String answer, String answer1,
                    String answer2, String answer3, String answer4, String song) {
        this.id_question = id_question;
        this.level = level;
        this.texto = texto;
        this.canswer = answer;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.song = song;
    }
    public Question(int level, String texto, String answer, String answer1,
                    String answer2, String answer3, String answer4, String song) {
        i++;
        this.id_question = i;
        this.level = level;
        this.texto = texto;
        this.canswer = answer;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.song = song;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getCanswer() {
        return canswer;
    }

    public void setCanswer(String canswer) {
        this.canswer = canswer;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public boolean validarRespuesta(String r){//se usará en la clase usuario
        if(r.equalsIgnoreCase(this.canswer)){
            return true;
        }else return false;
    }



    @Override
    public String toString() {
        return "Question{" +
                "id_question=" + id_question +
                ", level=" + level +
                ", texto='" + texto + '\'' +
                ", answer='" + canswer + '\'' +
                ", answer1='" + answer1 + '\'' +
                ", answer2='" + answer2 + '\'' +
                ", answer3='" + answer3 + '\'' +
                ", answer4='" + answer4 + '\'' +
                ", song='" + song + '\'' +
                '}';
    }
}//
