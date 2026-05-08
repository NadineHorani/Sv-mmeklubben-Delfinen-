package model;

import java.util.ArrayList;

public class Coach {
    private String name;
    private int phone;
    private String email;
    private int coachID;
    private ArrayList<CompetitionSwimmer> swimmers;


    public Coach(String name, int phone, String email, int coachID){
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.coachID = coachID;
        this.swimmers = new ArrayList<>();

    }

    public int getCoachID(){
        return coachID;
    }


    public void addSwimmer(CompetitionSwimmer swimmer){
        swimmers.add(swimmer);
    }

    public void removeSwimmer(CompetitionSwimmer swimmer){
        swimmers.remove(swimmer);
    }

    public String toString(){
        return "COACH" +
                "\n Name: " + name +
                "\n Phone number: " + phone +
                "\n Email: " + email +
                "\n model.Coach ID: " + coachID +
                "\n Swimmers: " + swimmers;
    }





}
