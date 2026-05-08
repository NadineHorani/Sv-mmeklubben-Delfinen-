package model;

import model.enums.Discipline;

import java.time.LocalDate;

public abstract class SwimResult implements Comparable <SwimResult> {

    private final Discipline discipline;
    private double time;
    private LocalDate date;

    public SwimResult (Discipline discipline, double time, LocalDate date){
        this.discipline = discipline;
        this.time = time;
        this.date = date;
    }

    public Discipline getDiscipline(){
        return discipline;
    }

    public double getTime(){
        return time;
    }

    public LocalDate getDate(){
        return date;
    }

    public int compareTo(SwimResult other){
        return Double.compare(this.time, other.time);
    }

}
