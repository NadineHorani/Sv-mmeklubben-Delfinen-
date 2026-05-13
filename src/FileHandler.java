import model.*;
import model.enums.Discipline;
import model.enums.MemberType;
import model.enums.MembershipStatus;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {


    public void load(Club club) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader("members.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Member m = parseMember(line);
                club.addMember(m);
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("payments.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Payment p = parsePayment(line, club);
                if (p != null) {
                    club.addPayment(p);
                }
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("swimresults.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(";", 2);

                int memberId = Integer.parseInt(parts[0]);
                SwimResult result = parseSwimResult(parts[1]);

                Member m = club.findMember(memberId);

                if (m instanceof CompetitionSwimmer swimmer) {

                    if (result instanceof TrainingResult tr) {
                        swimmer.addTrainingResult(tr);
                    } else if (result instanceof CompetitionResult cr) {
                        swimmer.addCompetitionResult(cr);
                    }
                }
            }
        }
    }

    public void save(Club club) throws IOException {
        saveMembers(club.getMembers());
        savePayments(club.getPayments());
        saveSwimResults(club);
    }


    //returnerer et member objekt pba. en linje i csv
    private Member parseMember(String text) {
        String[] parts = text.split(";");

        String name = parts[0];
        String address = parts[1];
        String email = parts[2];
        String phone = parts[3];
        int age = Integer.parseInt(parts[4]);
        int id = Integer.parseInt(parts[5]);
        MembershipStatus status = MembershipStatus.valueOf(parts[6]);
        MemberType type = MemberType.valueOf(parts[7]);

        if (type == MemberType.COMPETITION) {

            CompetitionSwimmer swimmer = new CompetitionSwimmer(name, address, email, phone, age, id,
                    status, type);

            if (parts.length > 8 && !parts[8].isEmpty()) { //hvis disciplin data findes i linjen
                String[] disciplines = parts[8].split(",");
                for (String d : disciplines) {
                    swimmer.addDisciplin(Discipline.valueOf(d));
                }
            }

            return swimmer;

        } else {
            return new Member(name, address, email, phone, age, id, status, type);
        }
    }
    //udskriver liste af members til csv
    private void saveMembers(List<Member> members) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.csv"))) {
            for (Member member : members) {
                writer.write(printMember(member));
                writer.newLine();
            }
        }
    }

    private String printMember(Member member) {
        if (member instanceof CompetitionSwimmer swimmer) {
            return String.format("%s;%s;%s;%s;%d;%d;%s;%s;%s", member.getName(), member.getAddress(), member.getEmail(),
                    member.getPhoneNumber(), member.getAge(), member.getMemberID(), member.getStatus(), member.getMemberType(),
                    swimmer.getDisciplinesAsString());
        } else {
            return String.format("%s;%s;%s;%s;%d;%d;%s;%s", member.getName(), member.getAddress(), member.getEmail(),
                    member.getPhoneNumber(), member.getAge(), member.getMemberID(), member.getStatus(), member.getMemberType());
        }
    }

    private Payment parsePayment(String text, Club club) {
        String[] parts = text.split(";");

        int memberId = Integer.parseInt(parts[0]);
        boolean isPaid = Boolean.parseBoolean(parts[1]);

        Member member = club.findMember(memberId);

        if (member == null) {
            System.out.println("FEJL i payments.csv: Ingen member med ID " + memberId + " fundet i systemet.");
            return null;
        }

        return new Payment(member, isPaid);
    }

    private void savePayments(List<Payment> payments) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("payments.csv"))) {
            for (Payment p : payments) {
                writer.write(printPayment(p));
                writer.newLine();
            }
        }
    }

    private String printPayment(Payment payment) {
        return String.format("%s;%s", payment.getMember().getMemberID(), payment.isPaid());
    }

    private SwimResult parseSwimResult(String text) {
        String[] parts = text.split(";");
        Discipline discipline = Discipline.valueOf(parts[0]);
        double time = Double.parseDouble(parts[1]);
        LocalDate date = LocalDate.parse(parts[2]);

        if (parts.length > 3) {
            String competetionName = parts[3];
            int placement = Integer.parseInt(parts[4]);

            return new CompetitionResult(discipline, time, date, competetionName, placement);
        }
        return new TrainingResult(discipline, time, date);
    }

    private void saveSwimResults(Club club) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("swimresults.csv"))) {

            for (CompetitionSwimmer swimmer : club.getCompetitionSwimmers()) {

                int memberId = swimmer.getMemberID();

                // Training results
                for (TrainingResult tr : swimmer.getTrainingResults()) {
                    writer.write(memberId + ";" + printSwimResult(tr));
                    writer.newLine();
                }

                // Competition results
                for (CompetitionResult cr : swimmer.getCompetitionResults()) {
                    writer.write(memberId + ";" + printSwimResult(cr));
                    writer.newLine();
                }
            }
        }
    }

    private String printSwimResult(SwimResult swimResult) {
        if (swimResult instanceof CompetitionResult competitionResult) {
            return String.format("%s;%s;%s;%s;%s", swimResult.getDiscipline(), swimResult.getTime(), swimResult.getDate(),
                    competitionResult.getName(), competitionResult.getPlacement());
        }
        return String.format("%s;%s;%s", swimResult.getDiscipline(), swimResult.getTime(), swimResult.getDate());
    }
}
