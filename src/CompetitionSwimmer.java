import enums.Discipline;
import enums.MemberType;
import enums.MembershipStatus;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CompetitionSwimmer extends Member {
    private Coach coach;
    private ArrayList<Discipline> disciplines;
    private ArrayList<TrainingResult> trainingResults;
    private ArrayList<CompetitionResult> competitionResults;


    public CompetitionSwimmer (String name, String address, String email, String phoneNumber, int age, int memberId, MembershipStatus status,
                               MemberType memberType){
        super(name, address, email, phoneNumber, age, memberId, status, memberType);
        this.disciplines = new ArrayList<>();
        this.trainingResults = new ArrayList<>();
        this.competitionResults = new ArrayList<>();
    }

    public void addCoach(Coach coach){
        this.coach = coach;
    }

    @Override
    public String getName() {
        return super.getName();
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
        competitionResults.add(result);
    }


    public TrainingResult getBestTrainingResult(Discipline discipline) {
        TrainingResult best = null;

        for (TrainingResult result : trainingResults) {
            if (result.getDiscipline() == discipline) {
                if (best == null || result.getTime() < best.getTime()) {
                    best = result;
                }
            }
        }
        return best;
    }

    public SwimResult getPersonalRecord(Discipline discipline){
        SwimResult best = null;

        for (SwimResult result : trainingResults) {
            if (result.getDiscipline() == discipline) {
                if (best == null || result.getTime() < best.getTime()) {
                    best = result;
                }
            }
        }

        for (SwimResult result : competitionResults){
            if (result.getDiscipline() == discipline) {
                if (best == null || result.getTime() < best.getTime()) {
                    best = result;
                }
            }
        }
        return best;
    }


//get PR
    //compare to o.get PR

    public String toString(){
        return "COMPETITION SWIMMER: " +
                "\n Coach: " + coach +
                "\n Disciplin: " + disciplines +
                "\n TrainingResult: " + trainingResults +
                "\n CompetitionResult: " + competitionResults;

    }


}
