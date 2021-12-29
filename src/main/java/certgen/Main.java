package certgen;

import certgen.Certificate.X509SelfSignedCert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        List<X509SelfSignedCert> certList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        loop:
        while (true) {
            System.out.println("Welcome to 'List my Class'. Press:\n"
                    + "1 - Create a new cert and add it to the list\n"
                    + "2 - Show the actual list\n"
                    + "3 - Search for certificates\n"
                    + "4 - Delete certificate with ID\n"
                    + "5 - Exit program");
            String element = scanner.nextLine();

            switch (element) {
                case "1":
                    certList.add(new X509SelfSignedCert());
                    System.out.println("A certificate was added!\n");
                    break;
                case "2":
                    System.out.println("Here are all saved certificates with their properties: ");
                    System.out.println(certList);
                    System.out.println();
                    break;
                case "3":
                    System.out.println("SEARCH\n");
                    break;
                case "4":
                    System.out.println("Which cert should be removed?");
                    int id = 0;
                    try {
                        id = scanner.nextInt();
                        if (id < 0 || id >= certList.size()) {
                            throw new Exception();
                        }
                        certList.remove(id);
                        File deleteFile = new File(id + ".pem");
                        deleteFile.delete();
                    } catch (Exception e) {
                        System.out.println("Please state a valid number within the list!\n");
                    }
                    break;
                case "5":
                    System.out.println("Goodbye! EXIT\n");
                    break loop;
                default:
                    System.out.println("Please type in a valid number! :)\n");
            }
        }
    }
}
