import model.*;
import model.enums.AgeCategory;
import model.enums.Discipline;
import model.enums.MemberType;
import model.enums.MembershipStatus;

import java.time.LocalDate;
import java.util.Scanner;

public class UserInterface {
    private Club club;
    private Scanner scanner;

    public UserInterface(Club club, Scanner scanner) {
        this.club = club;
        this.scanner = scanner;
    }

    public void start() {

        boolean quit = false;
        while (!quit) {
            System.out.println("""
                      HOVEDMENU
                    1. FORMAND
                    2. TRÆNER
                    3. KASSERE
                    4. AFSLUT""");

            int choice = getIntInput();

            switch (choice) {
                case 1 -> formand();
                case 2 -> træner();
                case 3 -> kassere();
                case 4 -> quit = true;
                default -> System.out.println("Ugyldigt input");
            }

        }
    }

    private void formand() {
        System.out.println("""
                1. OPRET NYT MEDLEM
                2. OPDATER MEDLEMSSTATUS
                3. SE ALLE MEDLEMMER
                4. OPRET TRÆNER
                5. SE ALLE TRÆNERE""");
        int choice = getIntInput();
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
                int age = getIntInput();
                System.out.println("MEDLEMS ID: ");
                int id = getIntInput();

                boolean validID = false;
                outer:
                // while-loop til ID validering. sikrer det samme id ikke bliver oprettet to gange
                while (!validID) {
                    for (Member member : club.getMembers()) {
                        if (id == member.getMemberID()) {
                            System.out.println("ID FINDES ALLEREDE. INDTAST ET NYT");
                            id = getIntInput();
                            continue outer;
                        }
                    }
                    validID = true;
                }

                System.out.println("1. MOTIONIST\n2. KONKURRENCESVØMMER");
                int answer = getIntInput();
                while (true) {
                    if (answer == 1) {
                        memberType = MemberType.MOTIONIST;
                        Member member = new Member(name, adress, email, phonenumber, age, id, memberType);
                        club.addMember(member);
                        Payment payment = new Payment(member);
                        club.addPayment(payment);
                        System.out.println("MEDLEM ER NU OPRETTET\n" + member + "\n");
                        break;
                    } else if (answer == 2) {
                        memberType = MemberType.COMPETITION;
                        CompetitionSwimmer competitionSwimmer = new CompetitionSwimmer(name, adress, email, phonenumber, age, id, memberType);
                        System.out.println("HVILKE DISCIPLINER DELTAGER MEDLEMMET I\n1. " +
                                "BUTTERFLY\n2. CRAWL\n3. RYGCRAWL\n4. BRYSTSVØMNING\n5. FÆRDIG");
                        boolean done = false;
                        while (!done) {
                            int choice1 = getIntInput();

                            switch (choice1) {
                                case 1 -> competitionSwimmer.addDisciplin(Discipline.BUTTERFLY);
                                case 2 -> competitionSwimmer.addDisciplin(Discipline.CRAWL);
                                case 3 -> competitionSwimmer.addDisciplin(Discipline.BACKSTROKE);
                                case 4 -> competitionSwimmer.addDisciplin(Discipline.BREASTSTROKE);
                                case 5 -> done = true;
                                default -> System.out.println("UGYLDIGT INPUT");
                            }
                        }

                        club.addMember(competitionSwimmer);
                        Payment payment = new Payment(competitionSwimmer);
                        club.addPayment(payment);
                        System.out.println("MEDLEM ER NU OPRETTET\n" + competitionSwimmer + "\n");
                        break;
                    } else {
                        System.out.println("UGYLDIGT INPUT. PRØV IGEN");
                        answer = getIntInput();
                    }
                }
            }

            case 2 -> {
                System.out.println("MEDLEMS ID: ");
                int id = getIntInput();
                Member member = club.findMember(id);
                if (member == null) {
                    System.out.println("Medlem findes ikke");
                    return;
                }
                System.out.println("MEDLEMMETS NUVÆRENDE STATUS ER:  " + member.getStatus());
                System.out.println("ØNSKER DU AT ÆNDRE MEDLEMMETS STATUS TIL: " + member.getOppositeStatus() +
                        "?\n1. Ja\n2. Annuller");
                int choice1 = getIntInput();
                switch (choice1) {
                    case 1 -> {
                        member.changeStatus();
                        System.out.println("ÆNDRING GENNEMFØRT");
                    }
                    case 2 -> {
                    }
                }

            }
            case 3 -> System.out.println(club.showMembers());
            case 4 -> {
                System.out.println("INDTAST NAVN: ");
                String name = scanner.nextLine();
                System.out.println("TLFNUMMER: ");
                String number = scanner.nextLine();
                System.out.println("EMAIL: ");
                String email = scanner.nextLine();
                System.out.println("TRÆNER ID: ");
                int id = getIntInput();
                Coach coach = new Coach(name, number, email, id);
                club.addCoach(coach);
                System.out.println("TRÆNER OPRETTET\nNAVN: " + coach.getName() + "\nTRÆNER ID: " + coach.getCoachID());
            }
            case 5 -> {
                for (Coach c : club.getCoaches()) {
                    System.out.println(c);
                }
            }
            default -> System.out.println("UGYLDIGT INPUT");
        }
    }

    private void træner() {
        System.out.println("""
                1. REGISTRER TID
                2. SE TOP 5
                3. TILKNYT TRÆNER TIL KONKURRENCESVØMMER""");
        int choice = getIntInput();
        switch (choice) {
            case 1 -> {
                System.out.println("INDTAST MEDELMS ID: ");
                int id = getIntInput();
                Member member = club.findMember(id);
                while (true) {
                    if (member == null) {
                        System.out.println("UGYLDIGT MEDLEMSID. PRØV IGEN");
                        id = getIntInput();
                        member = club.findMember(id);
                    } else if (member.getStatus() == MembershipStatus.PASSIVE) {
                        System.out.println("SYSTEMET KAN KUN REGISTRERE TIDER FOR AKTIVE MEDLEMMER");
                        return;
                    } else if (!(member instanceof CompetitionSwimmer)) {
                        System.out.println("SYSTEMET KAN KUN REGISTRERE TIDER FOR KONKURRENCESVØMMERE");
                        return;
                    } else {
                        break;
                    }

                }
                System.out.println("1. TRÆNINGSTID\n2. STÆVNETID");
                int choice1 = getIntInput();
                switch (choice1) {
                    case 1 -> {
                        Discipline discipline = chooseDiscipline();
                        System.out.println("INDTAST TIDSRESULTAT I SEKUNDER: ");
                        int time = getIntInput();
                        LocalDate date = getLocalDate();
                        TrainingResult trainingResult = new TrainingResult(discipline, time, date);
                        CompetitionSwimmer swimmer = (CompetitionSwimmer) member;
                        swimmer.addTrainingResult(trainingResult);
                        System.out.println("TRÆNINGSRESULTAT BLEV REGISTRERET KORREKT\n" + trainingResult);
                    }
                    case 2 -> {
                        Discipline discipline = chooseDiscipline();
                        System.out.println("INDTAST TIDSRESULTAT I SEKUNDER: ");
                        int time = getIntInput();
                        LocalDate date = getLocalDate();
                        System.out.println("STÆVNE NAVN: ");
                        String competetionName = scanner.nextLine();
                        System.out.println("PLACERING PÅ RANGLISTE: ");
                        int placement = getIntInput();
                        CompetitionResult competitionResult = new CompetitionResult(discipline, time, date,
                                competetionName, placement);
                        CompetitionSwimmer swimmer = (CompetitionSwimmer) member;
                        swimmer.addCompetitionResult(competitionResult);
                        System.out.println("STÆVNERESULTATET BLEV REGISTRERET KORREKT\n" + competitionResult);
                    }
                }
            }
            case 2 -> {
                AgeCategory ageCategory = null;
                System.out.println("1. JUNIOR\n2. SENIOR");
                int choice2 = getIntInput();
                switch (choice2) {
                    case 1 -> ageCategory = AgeCategory.JUNIOR;
                    case 2 -> ageCategory = AgeCategory.SENIOR;
                    default -> System.out.println("UGYLDIGT INPUT");
                }

                Discipline discipline = chooseDiscipline();
                System.out.println(club.getTop5s(discipline, ageCategory));
            }
            case 3 -> {
                System.out.println("MEDLEMS ID: ");
                int memberID = getIntInput();
                Member swimmer = club.findMember(memberID);

                if (!(swimmer instanceof CompetitionSwimmer competitionSwimmer)) {
                    System.out.println("Ikke en gyldig konkurrencesvømmer");
                    return;
                }

                System.out.println("TRÆNER ID: ");
                int coachID = getIntInput();
                Coach coach = club.findCoach(coachID);

                if (coach != null && competitionSwimmer.getCoach() == null) {
                    competitionSwimmer.addCoach(coach);
                    coach.addSwimmer(competitionSwimmer);
                    System.out.println("GENNEMFØRT");
                } else {
                    System.out.println("ENTEN FINDES TRÆNEREN IKKE I SYSTEMET, ELLERS HAR SVØMMEREN ALLEREDE EN TRÆNER");
                }
            }
        }
    }

    private void kassere() {
        System.out.println("""
                1. REGISTRER BETALING
                2. SAMLET FORVENTET INDKOMST
                3. MEDLEMMER I RESTANCE""");

        int choice = getIntInput();

        switch (choice) {
            case 1 -> {
                System.out.println("MEDELMS ID: ");
                int id = getIntInput();
                Member member = club.findMember(id);
                if (member == null) {
                    System.out.println("Medlem findes ikke");
                    return;
                }
                System.out.println("BELØB TIL BETALING: " + member.calculateFee() + "DKK");
                System.out.println("1. BEKRÆFT BETALING\n2. ANNULLER BETALING");
                int paymentChoice = getIntInput();
                switch (paymentChoice) {
                    case 1 -> {
                        club.registerPayment(id);
                        System.out.println("BETALING BEKRÆFTET");
                    }
                    case 2 -> {
                        return;
                    }
                }

            }
            case 2 -> System.out.println(club.getTotalExpectedFee() + " DKK");

            case 3 -> System.out.println(club.getDebtors());

            default -> System.out.println("UGYLDIGT INPUT");
        }

    }

    private int getIntInput() {  //extracted metode for at ungå dubletter
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ugyldigt input - skriv et tal!");
            }
        }
    }

    private LocalDate getLocalDate() {
        System.out.println("INDTAST DATO I TALFORMAT");
        System.out.println("ÅR: ");
        int year = getIntInput();
        System.out.println("MÅNED: ");
        int month = getIntInput();
        System.out.println("DAG: ");
        int day = getIntInput();
        return LocalDate.of(year, month, day);
    }

    private Discipline chooseDiscipline() { //extracted metode for at ungå dubletter
        System.out.println("1. BUTTERFLY\n2. CRAWL\n3. RYGCRAWL\n4. BRYSTSVØMNING");
        while (true) {
            int choice = getIntInput();
            switch (choice) {
                case 1 -> {
                    return Discipline.BUTTERFLY;
                }
                case 2 -> {
                    return Discipline.CRAWL;
                }
                case 3 -> {
                    return Discipline.BACKSTROKE;
                }
                case 4 -> {
                    return Discipline.BREASTSTROKE;
                }
                default -> System.out.println("UGYLDIGT INPUT");
            }
        }
    }

}