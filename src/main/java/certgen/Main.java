package certgen;

import certgen.Certificate.X509SelfSignedCert;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<X509SelfSignedCert> certList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        loop:
        while (true) {
            System.out.println("Welcome to 'List my Class'. Press:\n"
                    + "1 - Create a new cert and add it to the list\n"
                    + "2 - Show the actual list\n"
                    + "3 - Delete\n"
                    + "4 - Exit program");
            String element = scanner.nextLine();

            switch (element) {
                case "1":
                    certList.add(new X509SelfSignedCert());
                    break;
                case "2":
                    System.out.println(certList);
                    break;
                case "3":
                    System.out.println("Which cert should be removed?");
                    int deletionNumber = scanner.nextInt();
                    if (deletionNumber <= certList.size()) {
                        certList.remove(deletionNumber);
                    } else {
                        System.out.println("Please state a valid number withing the range of certificates list\n");
                    }
                    break;
                case "4":
                    System.out.println("Goodbye!");
                    break loop;
                default:
                    System.out.println("Please type in a valid number! :)\n");
            }
        }
    }
}
