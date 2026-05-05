import java.util.ArrayList;

public class CompetitionSwimmers {
    private Coach coach;
    private ArrayList<Disciplin> disciplins;
    private ArrayList<TrainingResult> trainingResults;
    private ArrayList<CompetitionResult> competitionResults;


    public class CompetitionSwimmers (){
        this.coach = coach;
        this.disciplins = new ArrayList<>();
        this.trainingResults = new ArrayList<>();
        this.competitionResults = new ArrayList<>();
    }


    public void addDisciplin(Disciplin disciplin){
        if(!disciplins.contains(disciplin)){
            disciplins.add(disciplin);
        }
    }


    public void addTrainingResult(TrainingResult result){
        trainingResults.add(result);
    }


    public void addCompetitionResult(CompetitionResult result){
        CompetitionResults.add(result);
    }


    public TraingResult getBestTrainingResult(Disciplin disciplin) {
        TraningsResult best = null;

        for (TraningResult result : trainingResults) {
            if (result.getDisciplin() == disciplin) {
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
                "\n Disciplin: " + disciplins +
                "\n TrainingResult: " + trainingResults +
                "\n CompetitionResult: " + competitionResults;

    }

}
