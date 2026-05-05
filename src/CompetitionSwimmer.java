import enums.Discipline;

import java.util.ArrayList;

public class CompetitionSwimmer {
    private Coach coach;
    private ArrayList<Discipline> disciplines;
    private ArrayList<TrainingResult> trainingResults;
    private ArrayList<CompetitionResult> competitionResults;


    public class CompetitionSwimmers (){
        this.coach = coach;
        this.disciplines = new ArrayList<>();
        this.trainingResults = new ArrayList<>();
        this.competitionResults = new ArrayList<>();
    }


    public void addDisciplin(Discipline discipline){
        if(!disciplines.contains(discipline)){
            disciplines.add(discipline);
        }
    }


    public void addTrainingResult(TrainingResult result){
        trainingResults.add(result);
    }


    public void addCompetitionResult(CompetitionResult result){
        CompetitionResults.add(result);
    }


    public TraingResult getBestTrainingResult(Discipline discipline) {
        TraningsResult best = null;

        for (TraningResult result : trainingResults) {
            if (result.getDiscipline() == disciplin) {
                if (best == null || result.getTime() < best.getTime()) {
                    best = result;
                }
            }
        }
        return best;
    }


    public String toString(){
        return "COMPETITION SWIMMER: " +
                "\n Coach: " + coach +
                "\n Disciplin: " + disciplines +
                "\n TrainingResult: " + trainingResults +
                "\n CompetitionResult: " + competitionResults;

    }

}
