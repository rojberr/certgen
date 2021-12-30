package certgen;

import certgen.Certificate.X509CertGen;
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
                    X509CertGen x509CertGen = new X509CertGen();
                    certList.add(x509CertGen.generate());
                    System.out.println("A certificate was added!\n");
                    break;
                case "2":
                    System.out.println("Here are all saved certificates with their properties: ");
                    System.out.println(certList);
                    System.out.println();
                    break;
                case "3":
                    System.out.println("Search for certificate with id: \n");
                    try {
                        String searched = scanner.nextLine();
                        List<X509SelfSignedCert> founds = searchFor(certList, searched);
                        System.out.println(founds);
                    } catch (Exception e) {
                        System.out.println("Please state a valid search term!\n");
                    }
                    break;
                case "4":
                    System.out.println("Which cert should be removed?");
                    try {
                        int id = scanner.nextInt();
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

    public static List<X509SelfSignedCert> searchFor(List<X509SelfSignedCert> certList, String searched) {
        List<X509SelfSignedCert> founds = new ArrayList<>();
        for (int i = 0; i < certList.size(); i++) {
            if ( certList.get(i).toString().contains(searched)) {
                founds.add(certList.get(i));
            }
        }
        return founds;
    }
}
