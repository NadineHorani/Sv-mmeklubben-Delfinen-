import enums.Discipline;

import java.time.LocalDate;

public class TrainingResult extends SwimResult {


    public TrainingResult(Discipline discipline, double time, LocalDate date){
        super(discipline,time,date);
    }


    public String toString(){
        return String.format("""
                Discipline: %s
                Time: %.2f
                Date: %s""",getDiscipline(),getTime(),getDate());
    }

}
