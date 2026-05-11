import model.*;
import model.enums.AgeCategory;
import model.enums.Discipline;
import model.enums.MemberType;
import model.enums.MembershipStatus;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.SortedMap;

public class UserInterface {
    private Club club;
    private Scanner scanner;

    public UserInterface(Club club, Scanner scanner) {
        this.club = club;
        this.scanner = scanner;
    }

    public void start() {
        CompetitionSwimmer member = new CompetitionSwimmer("Owen", "hej", "hej2", "321231", 25, MemberType.COMPETITION);
        member.addCompetitionResult(new CompetitionResult(Discipline.BREASTSTROKE, 120, LocalDate.now(), "DM", 3));
        member.addDisciplin(Discipline.BREASTSTROKE);
        club.addMember(member);
        boolean quit = false;
        while (!quit) {
            System.out.println("""
                      HOVEDMENU
                    1. FORMAND
                    2. TRÆNER
                    3. KASSERE
                    4. AFSLUT""");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> formand();
                case 2 -> træner();
//                case 3 -> kassere();
                case 4 -> quit = true;
                default -> System.out.println("Ugyldigt input");
            }

        }
    }


    private void formand() {
        System.out.println("1. OPRET NYT MEDLEM\n" + "2. OPDATER MEDLEMSSTATUS");
        int choice = Integer.parseInt(scanner.nextLine());
        MemberType memberType;
        switch (choice) {
            case 1 -> {
                System.out.println("NAVN:");
                String name = scanner.nextLine();
                System.out.println("ADRESSE: ");
                String adress = scanner.nextLine();
                System.out.println("EMAIL: ");
                String email = scanner.nextLine();
                System.out.println("TLF: ");
                String phonenumber = scanner.nextLine();
                System.out.println("ALDER: ");
                int age = Integer.parseInt(scanner.nextLine());
                System.out.println("1. MOTIONIST\n2. KONKURRENCESVØMMER");
                int answer = Integer.parseInt(scanner.nextLine());
                while (true) {
                    if (answer == 1) {
                        memberType = MemberType.MOTIONIST;
                        Member member = new Member(name, adress, email, phonenumber, age, memberType);
                        club.addMember(member);
                        System.out.println("MEDLEM ER NU OPRETTET\n" + member + "\n");
                        break;
                    } else if (answer == 2) {
                        memberType = MemberType.COMPETITION;
                        CompetitionSwimmer competitionSwimmer = new CompetitionSwimmer(name, adress, email, phonenumber, age, memberType);
                        System.out.println("HVILKE DISCIPLINER DELTAGER MEDLEMMET I\n1. " +
                                "BUTTERFLY\n2. CRAWL\n3. RYGCRAWL\n4. BRYSTSVØMNING\n5. FÆRDIG");
                        boolean done = false;
                        int choice1 = Integer.parseInt(scanner.nextLine());
                        while (!done) {
                            switch (choice1) {
                                case 1 -> competitionSwimmer.addDisciplin(Discipline.BUTTERFLY);
                                case 2 -> competitionSwimmer.addDisciplin(Discipline.CRAWL);
                                case 3 -> competitionSwimmer.addDisciplin(Discipline.BACKSTROKE);
                                case 4 -> competitionSwimmer.addDisciplin(Discipline.BREASTSTROKE);
                                case 5 -> done = true;
                                default -> System.out.println("UGYLDIGT INPUT");
                            }
                        }
                        System.out.println("MEDLEM ER NU OPRETTET\n" + competitionSwimmer + "\n");
                        break;
                    } else {
                        System.out.println("UGYLDIGT INPUT. PRØV IGEN");
                        answer = Integer.parseInt(scanner.nextLine());
                    }
                }
            }

            case 2 -> {
                System.out.println("MEDLEMS ID: ");
                int id = Integer.parseInt(scanner.nextLine());
                Member member = club.findMember(id);
                System.out.println("MEDLEMMETS NUVÆRENDE STATUS ER:  " + member.getStatus());
                System.out.println("ØNSKER DU AT ÆNDRE MEDLEMMETS STATUS TIL: " + member.getOppositeStatus() +
                        "?\n1. Ja\n2. Annuller");
                int choice1 = Integer.parseInt(scanner.nextLine());
                switch (choice1) {
                    case 1 -> {
                        member.changeStatus();
                        System.out.println("ÆNDRING GENNEMFØRT");
                    }
                    case 2 -> {
                    }
                }

            }
            default -> System.out.println("UGYLDIGT INPUT");
        }
    }

    private void træner() {
        System.out.println("1. REGISTRER TID\n" + "2. SE TOP 5");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1 -> {
                System.out.println("INDTAST MEDELMS ID: ");
                int id = Integer.parseInt(scanner.nextLine());
                Member member = club.findMember(id);
                while (true) {
                    if (member == null) {
                        System.out.println("UGYLDIGT MEDLEMSID. PRØV IGEN");
                        id = Integer.parseInt(scanner.nextLine());
                        member = club.findMember(id);
                    } else {
                        break;
                    }
                }

                System.out.println("1. TRÆNINGSTID\n2. STÆVNETID");
                int choice1 = Integer.parseInt(scanner.nextLine());
                switch (choice1) {
                    case 1 -> {
                        Discipline discipline = null;
                        System.out.println("1. BUTTERFLY\n2. CRAWL\n3. RYGCRAWL\n4. BRYSTSVØMNING");
                        int choice2 = Integer.parseInt(scanner.nextLine());
                        switch (choice2) {
                            case 1 -> discipline = Discipline.BUTTERFLY;
                            case 2 -> discipline = Discipline.CRAWL;
                            case 3 -> discipline = Discipline.BACKSTROKE;
                            case 4 -> discipline = Discipline.BREASTSTROKE;
                        }
                        System.out.println("INDTAST TIDSRESULTAT I SEKUNDER: ");
                        int time = Integer.parseInt(scanner.nextLine());
                        System.out.println("INDTAST DATO I TALFORMAT");
                        System.out.println("ÅR: ");
                        int year = Integer.parseInt(scanner.nextLine());
                        System.out.println("MÅNED: ");
                        int month = Integer.parseInt(scanner.nextLine());
                        System.out.println("DAG: ");
                        int day = Integer.parseInt(scanner.nextLine());
                        LocalDate date = LocalDate.of(year, month, day);
                        TrainingResult trainingResult = new TrainingResult(discipline, time, date);
                        if (member instanceof CompetitionSwimmer swimmer) {
                            swimmer.addTrainingResult(trainingResult);
                        }
                        System.out.println("TRÆNINGSRESULTAT BLEV REGISTRERET KORREKT\n" + trainingResult);
                    }
                }


            }
            case 2 -> {
                AgeCategory ageCategory = null;
                System.out.println("1. JUNIOR\n2. SENIOR");
                int choice2 = Integer.parseInt(scanner.nextLine());
                switch (choice2) {
                    case 1 -> ageCategory = AgeCategory.JUNIOR;
                    case 2 -> ageCategory = AgeCategory.SENIOR;
                    default -> System.out.println("UGYLDIGT INPUT");
                }

                Discipline discipline = null;
                System.out.println("1. BUTTERFLY\n2. CRAWL\n3. RYGCRAWL\n4. BRYSTSVØMNING");
                int choice3 = Integer.parseInt(scanner.nextLine());
                switch (choice3) {
                    case 1 -> discipline = Discipline.BUTTERFLY;
                    case 2 -> discipline = Discipline.CRAWL;
                    case 3 -> discipline = Discipline.BACKSTROKE;
                    case 4 -> discipline = Discipline.BREASTSTROKE;
                    default -> System.out.println("UGYLDIGT INPUT");
                }
                System.out.println(club.getTop5s(discipline, ageCategory));
            }
        }
    }
}
