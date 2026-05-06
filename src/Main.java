import enums.AgeCategory;
import enums.Discipline;
import enums.MemberType;
import enums.MembershipStatus;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Club club = new Club("Delfinen");

        // Coaches
        Coach coach1 = new Coach("Peter Jensen", 12345678, "peter@mail.dk", 101);
        Coach coach2 = new Coach("Lise Hansen", 87654321, "lise@mail.dk", 102);

        club.addCoach(coach1);
        club.addCoach(coach2);

        // Oprindelige svømmere
        CompetitionSwimmer swimmer1 = new CompetitionSwimmer("Mikkel Hansen", "København", "mikkel@mail.dk", "12345678", 18, 1, MembershipStatus.ACTIVE, MemberType.COMPETITION);

        CompetitionSwimmer swimmer2 = new CompetitionSwimmer("Sofie Nielsen", "Aarhus", "sofie@mail.dk", "87654321", 20, 2, MembershipStatus.ACTIVE, MemberType.COMPETITION);

        swimmer1.addCoach(coach1);
        swimmer2.addCoach(coach2);

        coach1.addSwimmer(swimmer1);
        coach2.addSwimmer(swimmer2);

        swimmer1.addDisciplin(Discipline.BACKSTROKE);
        swimmer2.addDisciplin(Discipline.BACKSTROKE);

        swimmer1.addCompetitionResult(new CompetitionResult(Discipline.BACKSTROKE, 52.1, LocalDate.of(2026, 5, 4), "DM 2026", 1));

        swimmer2.addCompetitionResult(new CompetitionResult(Discipline.BACKSTROKE, 57.0, LocalDate.of(2026, 5, 4), "DM 2026", 2));
        swimmer2.addTrainingResult(new TrainingResult(Discipline.BACKSTROKE,23,LocalDate.of(2025,3,10)));

        // 10 ekstra svømmere
        CompetitionSwimmer swimmer3 = new CompetitionSwimmer("Jonas Berg", "Odense", "jonas@mail.dk", "11111111", 25, 3, MembershipStatus.ACTIVE, MemberType.COMPETITION);
        CompetitionSwimmer swimmer4 = new CompetitionSwimmer("Emma Larsen", "Aalborg", "emma@mail.dk", "22222222", 22, 4, MembershipStatus.ACTIVE, MemberType.COMPETITION);
        CompetitionSwimmer swimmer5 = new CompetitionSwimmer("Lucas Holm", "Esbjerg", "lucas@mail.dk", "33333333", 30, 5, MembershipStatus.ACTIVE, MemberType.COMPETITION);
        CompetitionSwimmer swimmer6 = new CompetitionSwimmer("Freja Madsen", "Herning", "freja@mail.dk", "44444444", 19, 6, MembershipStatus.ACTIVE, MemberType.COMPETITION);
        CompetitionSwimmer swimmer7 = new CompetitionSwimmer("Oliver Dahl", "Randers", "oliver@mail.dk", "55555555", 28, 7, MembershipStatus.ACTIVE, MemberType.COMPETITION);
        CompetitionSwimmer swimmer8 = new CompetitionSwimmer("Clara Jensen", "Vejle", "clara@mail.dk", "66666666", 24, 8, MembershipStatus.ACTIVE, MemberType.COMPETITION);
        CompetitionSwimmer swimmer9 = new CompetitionSwimmer("Noah Kristensen", "Kolding", "noah@mail.dk", "77777777", 27, 9, MembershipStatus.ACTIVE, MemberType.COMPETITION);
        CompetitionSwimmer swimmer10 = new CompetitionSwimmer("Laura Sørensen", "Horsens", "laura@mail.dk", "88888888", 21, 10, MembershipStatus.ACTIVE, MemberType.COMPETITION);
        CompetitionSwimmer swimmer11 = new CompetitionSwimmer("William Poulsen", "Roskilde", "william@mail.dk", "99999999", 26, 11, MembershipStatus.ACTIVE, MemberType.COMPETITION);
        CompetitionSwimmer swimmer12 = new CompetitionSwimmer("Ida Nørgaard", "Silkeborg", "ida@mail.dk", "10101010", 23, 12, MembershipStatus.ACTIVE, MemberType.COMPETITION);

        // BACKSTROKE resultater
        swimmer3.addCompetitionResult(new CompetitionResult(Discipline.BACKSTROKE, 54.3, LocalDate.of(2026,10,2), "DM 2026", 2));
        swimmer4.addCompetitionResult(new CompetitionResult(Discipline.BACKSTROKE, 51.9, LocalDate.of(2026,1,9), "DM 2026", 1));
        swimmer5.addCompetitionResult(new CompetitionResult(Discipline.BACKSTROKE, 60.2, LocalDate.of(2026,6,4), "DM 2026", 5));
        swimmer6.addCompetitionResult(new CompetitionResult(Discipline.BACKSTROKE, 56.7, LocalDate.of(2026,3,2), "DM 2026", 3));
        swimmer7.addCompetitionResult(new CompetitionResult(Discipline.BACKSTROKE, 53.5, LocalDate.of(2026,5,7), "DM 2026", 2));
        swimmer8.addCompetitionResult(new CompetitionResult(Discipline.BACKSTROKE, 58.9, LocalDate.of(2026,5,4), "DM 2026", 4));
        swimmer9.addCompetitionResult(new CompetitionResult(Discipline.BACKSTROKE, 52.8, LocalDate.of(2026,7,2), "DM 2026", 1));
        swimmer10.addCompetitionResult(new CompetitionResult(Discipline.BACKSTROKE, 61.0, LocalDate.of(2026,5,1), "DM 2026", 6));
        swimmer11.addCompetitionResult(new CompetitionResult(Discipline.BACKSTROKE, 55.1, LocalDate.of(2026,2,10), "DM 2026", 3));
        swimmer12.addCompetitionResult(new CompetitionResult(Discipline.BACKSTROKE, 57.4, LocalDate.of(2026,8,11), "DM 2026", 4));

        // Tilføj alle til klub
        club.addCompetitionSwimmer(swimmer1);
        club.addCompetitionSwimmer(swimmer2);
        club.addCompetitionSwimmer(swimmer3);
        club.addCompetitionSwimmer(swimmer4);
        club.addCompetitionSwimmer(swimmer5);
        club.addCompetitionSwimmer(swimmer6);
        club.addCompetitionSwimmer(swimmer7);
        club.addCompetitionSwimmer(swimmer8);
        club.addCompetitionSwimmer(swimmer9);
        club.addCompetitionSwimmer(swimmer10);
        club.addCompetitionSwimmer(swimmer11);
        club.addCompetitionSwimmer(swimmer12);

        // TEST
        System.out.println(club.getTop5s(Discipline.BACKSTROKE, AgeCategory.SENIOR));
    }
}