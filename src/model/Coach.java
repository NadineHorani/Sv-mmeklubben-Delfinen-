package model;

import java.util.ArrayList;

public class Coach {
    private String name;
    private String phone;
    private String email;
    private int coachID;
    private static int counter = 0;
    private ArrayList<CompetitionSwimmer> swimmers;


    public String getName() {
        return name;
    }

    public Coach(String name, String phone, String email){
        counter++;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.coachID = counter;
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
