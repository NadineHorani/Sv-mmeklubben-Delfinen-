package model;

import model.enums.Discipline;
import java.time.LocalDate;

public class CompetitionResult extends SwimResult {

    private String name;
    private int placement;


    public CompetitionResult (Discipline discipline, double time, LocalDate date, String name, int placement){
        super(discipline, time, date);
        this.name = name;
        this.placement = placement;
    }

    public String getName(){
        return name;
    }

    public int getPlacement(){
        return placement;
    }

    public String toString(){
        return "COMPETITION RESULT:" +
                "\n Descipline: " + getDiscipline() +
                "\n Time: " + getTime() + " sec" +
                "\n Date: " + getDate() +
                "\n Name: " + getName() +
                "\n Placement: " + getPlacement();
    }
}
