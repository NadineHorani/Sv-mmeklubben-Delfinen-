package model;

import model.enums.AgeCategory;
import model.enums.Discipline;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class Club {
    private String name;
    private ArrayList<Member> members;
    private ArrayList<Coach> coaches;
    private ArrayList<Payment> payments;

    public Club(String name) {
        this.name = name;
        this.members = new ArrayList<>();
        this.coaches = new ArrayList<>();
        this.payments = new ArrayList<>();
    }

    public void addMember(Member member) {
        members.add(member);
    }


    public Member findMember(int memberId) {
        for (Member m : members) {
            if (m.getMemberId() == memberId) {
                return m;
            }
        }
        return null;

    }

    public void addCoach(Coach coach) {
        coaches.add(coach);
    }

    public Coach findCoach(int coachId) {
        for (Coach c : coaches) {
            if (c.getCoachID() == coachId) {
                return c;
            }
        }
        return null;
    }



    public ArrayList<Member> getDebtors() {
        ArrayList<Member> debtors = new ArrayList<>();

        for (Payment payment : payments) {
            if (!payment.isPaid()) {
                debtors.add(payment.getMember());
            }
        }
        return debtors;
    }

    public int getTotalExpectedFee() {
        int total = 0;

        for (Member m : members) {
            total += m.calculateFee();
        }
        return total;
    }


    public ArrayList<CompetitionSwimmer> getCompetitionSwimmers() {
        ArrayList<CompetitionSwimmer> competitionSwimmerArrayList = new ArrayList<>();

        for (Member member : members) {
            if (member instanceof CompetitionSwimmer) {
                competitionSwimmerArrayList.add((CompetitionSwimmer) member);
            }
        }
        return competitionSwimmerArrayList;
    }


    public String getTop5s(Discipline discipline, AgeCategory category) {
        StringBuilder sb = new StringBuilder();
        ArrayList<CompetitionSwimmer> result = new ArrayList<>();

        for (CompetitionSwimmer s : getCompetitionSwimmers()) { //alle objekter der matcher agecategori og som
            if (s.getAgeCategory() == category &&  // har en personlig rekord i disciplinen bliver tilføjet til ny liste
                    s.getPersonalRecord(discipline) != null) { // forhindrer crash og
                // sikrer at objektet har en pr i den givne disciplin
                result.add(s);
            }
        }
        result.sort(new CompetitionSwimmerComparator(discipline));

        sb.append("TOP 5 ").append(discipline).append(" - ").append(category).append("\n___________________________\n");

        for (int i = 0; i < result.size() && i < 5; i++) {
            sb.append(result.get(i).getName()).append(" ").append(result.get(i).getPersonalRecord(discipline)).append("\n");
        }

        return sb.toString();
    }

    public String toString() {
        return "model.Club: " + name +
                "\nMembers: " + members.size() +
                "\nCoaches: " + coaches.size() +
                "\nCompetition swimmers: " + getCompetitionSwimmers().size();
    }
}