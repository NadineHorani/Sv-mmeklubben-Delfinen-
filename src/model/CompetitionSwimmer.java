package model;

import model.enums.Discipline;
import model.enums.MemberType;
import model.enums.MembershipStatus;
import java.util.ArrayList;
import java.util.List;

public class CompetitionSwimmer extends Member {
    private Coach coach;
    private List<Discipline> disciplines;
    private List<TrainingResult> trainingResults;
    private List<CompetitionResult> competitionResults;


    public CompetitionSwimmer(String name, String address, String email, String phoneNumber, int age, int memberID,
                              MemberType memberType) {
        super(name, address, email, phoneNumber, age, memberID, memberType);

        this.disciplines = new ArrayList<>();
        this.trainingResults = new ArrayList<>();
        this.competitionResults = new ArrayList<>();
    }
           public CompetitionSwimmer(String name, String address, String email, String phoneNumber, int age, int memberID, MembershipStatus status,
        MemberType memberType) {
            super(name, address, email, phoneNumber, age, memberID, status, memberType);

            this.disciplines = new ArrayList<>();
            this.trainingResults = new ArrayList<>();
            this.competitionResults = new ArrayList<>();
    }

    public List<TrainingResult> getTrainingResults() {
        return trainingResults;
    }

    public List<CompetitionResult> getCompetitionResults() {
        return competitionResults;
    }
    public String getDisciplinesAsString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < disciplines.size(); i++){
            sb.append(disciplines.get(i));
            if (i < disciplines.size() - 1){
                sb.append(",");
            }
        }
        return sb.toString();
    }


    public void addCoach(Coach coach) {
        this.coach = coach;
    }

    public Coach getCoach() {
        return coach;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public void addDisciplin(Discipline discipline) {
        if (!disciplines.contains(discipline)) {
            disciplines.add(discipline);
        }
    }


    public void addTrainingResult(TrainingResult result) {
        trainingResults.add(result);
    }


    public void addCompetitionResult(CompetitionResult result) {
        competitionResults.add(result);
    }

    public SwimResult getPersonalRecord(Discipline discipline) {
        SwimResult best = null;

        for (SwimResult result : trainingResults) {
            if (result.getDiscipline() == discipline) { //filter der finder den valgte disciplin
                if (best == null || result.getTime() < best.getTime()) {  //sikrer at best ikke bliver null
                    best = result;                               //hvis svømmeren har et resultat i den valgte disciplin
                }
            }
        }

        for (SwimResult result : competitionResults) {
            if (result.getDiscipline() == discipline) {
                if (best == null || result.getTime() < best.getTime()) {
                    best = result;
                }
            }
        }
        return best;
    }


    public String toString() {
        return "COMPETITION SWIMMER: " +
                "\n Coach: " + coach +
                "\n Disciplin: " + disciplines +
                "\n TrainingResult: " + trainingResults +
                "\n CompetitionResult: " + competitionResults;

    }

}
