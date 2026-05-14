package model;

import model.enums.AgeCategory;
import model.enums.Discipline;
import java.util.ArrayList;
import java.util.List;

public class Club {
    private String name;
    private List<Member> members;
    private List<Coach> coaches;
    private List<Payment> payments;


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
            if (m.getMemberID() == memberId) {
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

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public String showMembers() {
        StringBuilder sb = new StringBuilder();
        for (Member m : members) {
            if (!(m instanceof CompetitionSwimmer)){
            sb.append(m.getName()).append("\nID: ").append(m.getMemberID()).append("\nALDER: ").append
                            (m.getAge()).append("\nSTATUS: ").append(m.getStatus()).append("\nNIVEAU: ")
                    .append(m.getMemberType()).append("\n");
            }
            if (m instanceof CompetitionSwimmer swimmer){
                sb.append(swimmer.getName()).append("\nID: ").append(swimmer.getMemberID()).append("\nALDER: ").append
                                (swimmer.getAge()).append("\nSTATUS: ").append(swimmer.getStatus()).append("\nNIVEAU: ")
                        .append(swimmer.getMemberType()).append("\nTRÆNER: ").append(swimmer.getCoachName()).append("\n");
            }

        }
        return sb.toString();
    }

    public String getDebtors() {
        StringBuilder sb = new StringBuilder();

        List<Member> debtors = new ArrayList<>();

        for (Payment payment : payments) {
            if (!payment.isPaid()) {
                debtors.add(payment.getMember());
            }
        }

        for (Member m : debtors) {
            sb.append("MEDLEMSID: ").append(m.getMemberID()).append(" (").append(m.getStatus()).append(")")
                    .append("\nNAVN: ").append(m.getName()).append(" (").append(m.getAgeCategory()).append(")").
                    append("\nALDER: ").append(m.getAge())  .append("\nMANGLER AT BETALE: ").
                    append(m.calculateFee()).append(" DKK\n\n");
        }

        return sb.toString();
    }

    public int getTotalExpectedFee() {
        int total = 0;

        for (Member m : members) {
            total += m.calculateFee();
        }
        return total;
    }

    public void registerPayment(int memberID) {
        for (Payment payment : payments) {
            if (payment.getMember().getMemberID() == memberID) {
                payment.markAsPaid();
                return;
            }
        }
    }

    public List<CompetitionSwimmer> getCompetitionSwimmers() {
        List<CompetitionSwimmer> competitionSwimmerArrayList = new ArrayList<>();

        for (Member member : members) {
            if (member instanceof CompetitionSwimmer) {
                competitionSwimmerArrayList.add((CompetitionSwimmer) member);
            }
        }
        return competitionSwimmerArrayList;
    }


    public String getTop5s(Discipline discipline, AgeCategory category) {
        StringBuilder sb = new StringBuilder();
        List<CompetitionSwimmer> result = new ArrayList<>();

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
            sb.append(result.get(i).getName()).append(" ID: ").append(result.get(i).getMemberID()).append("\n").
                    append(result.get(i).getPersonalRecord(discipline)).append("\n");
        }

        return sb.toString();
    }

    public CompetitionSwimmer findCompetitionSwimmerById(int id){
        for (Member m : members){
            if (m instanceof CompetitionSwimmer swimmer && id == swimmer.getMemberID()){
                return swimmer;
            }
        }
        return null;
    }



    public String toString() {
        return "Club: " + name +
                "\nMembers: " + members.size() +
                "\nCoaches: " + coaches.size() +
                "\nCompetition swimmers: " + getCompetitionSwimmers().size();
    }
}