import model.Club;
import model.Member;
import model.enums.AgeCategory;
import model.enums.MemberType;
import model.enums.MembershipStatus;

import java.util.Scanner;

public class UserInterface {
    private Club club;
    private Scanner scanner;

    public UserInterface(Club club, Scanner scanner) {
        this.club = club;
        this.scanner = scanner;
    }

    public void start() {
        Member member = new Member("Owen", "hej","hej2","321231",25, MemberType.COMPETITION);
        club.addMember(member);
        while (true) {
            System.out.println("""
                      HOVEDMENU
                    1. Formand
                    2. Træner
                    3. Kassere
                    4. Afslut""");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    formand();
                case 2: træner();
                case 3:
                case 4: return;
                default:
                    System.out.println("Ugyldigt input");
            }


        }
    }



    private void formand() {
        System.out.println("1. Opret nyt medlem\n" + "2. Opdater medlemsstatus");
        int choice = Integer.parseInt(scanner.nextLine());
        MemberType memberType = null;
        switch (choice) {
            case 1: {
                System.out.println("Navn:");
                String name = scanner.nextLine();
                System.out.println("Adresse: ");
                String adress = scanner.nextLine();
                System.out.println("Email: ");
                String email = scanner.nextLine();
                System.out.println("Telefon Nummer: ");
                String phonenumber = scanner.nextLine();
                System.out.println("Alder: ");
                int age = Integer.parseInt(scanner.nextLine());
                System.out.println("1. Motionist\n2. KonkurrenceSvømmer");
                int answer = Integer.parseInt(scanner.nextLine());
                while (true) {
                    if (answer == 1) {
                        memberType = MemberType.MOTIONIST;
                        break;
                    } else if (answer == 2) {
                        memberType = MemberType.COMPETITION;
                        break;
                    } else {
                        System.out.println("Forkert input. Prøv igen");
                        answer = Integer.parseInt(scanner.nextLine());
                    }
                }
                Member member = new Member(name, adress, email, phonenumber, age, memberType);
                club.addMember(member);
                System.out.println("Medlem er nu oprettet\n" + member + "\n");
            }

            case 2: {
                System.out.println("Medlemsid: ");
                int id = Integer.parseInt(scanner.nextLine());
                Member member = club.findMember(id);
                System.out.println("Medlemmets nuværende status er: " + member.getStatus());
                System.out.println("Ønsker du at ændre medlemmets status til: " + member.getOppositeStatus() + "?\n1. Ja\n2. Annuller");
                int choice1 = Integer.parseInt(scanner.nextLine());
                switch (choice1){
                    case 1: member.changeStatus();
                    case 2: return;
                }

            }
            default:
                System.out.println("Forkert input");
        }
    }
    private void træner() {

    }
}
