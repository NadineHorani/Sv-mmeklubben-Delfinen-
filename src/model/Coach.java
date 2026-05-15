package model;

import java.util.ArrayList;
import java.util.List;

public class Coach {
    private String name;
    private String phone;
    private String email;
    private int coachID;
    private List<CompetitionSwimmer> swimmers;

    public Coach(String name, String phone, String email, int coachID) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.coachID = coachID;
        this.swimmers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCoachID() {
        return coachID;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone(){
        return phone;
    }


    public void addSwimmer(CompetitionSwimmer swimmer) {
        swimmers.add(swimmer);
    }

    public String getSwimmerIDsAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < swimmers.size(); i++) {
            sb.append(swimmers.get(i).getMemberID());
            if (i < swimmers.size() - 1){
                sb.append(",");
            }
        }
        return sb.toString();
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName()).append(" (TRÆNER ID: ").append(this.getCoachID()).append(")");
        sb.append("\nTILKNYTTEDE SVØMMERE\n-----------------------\n");
        for (CompetitionSwimmer swimmer : swimmers){
            sb.append(swimmer.getName()).append(" (MEDLEMS ID: ").append(swimmer.getMemberID()).append(")\n");
        }
        return sb.toString();
    }


}
