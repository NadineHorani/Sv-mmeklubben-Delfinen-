import model.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Club delfinen = new Club("Delfinen");
        FileHandler fileHandler = new FileHandler();
        try {
            fileHandler.load(delfinen); //loader data fra filer programmet starter
        } catch (IOException e) {
            System.out.println("Den er helt gal med load");
        }

        UserInterface ui = new UserInterface(delfinen, scanner);
        ui.start();

        try { //gemmer data før programmet slutter
            fileHandler.save(delfinen);
        } catch (IOException e) {
            System.out.println("Den er helt gal med save");
        }
    }
}

