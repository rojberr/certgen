package certgen;

import certgen.Certificate.X509SelfSignedCert;
import certgen.Keys.MyKeyPair;
import certgen.Keys.MyKeyPairGeneratorRSA;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        List<MyKeyPair> keyList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        loop:
        while (true) {
            System.out.println("Welcome to 'List my Class'. Press:\n"
                    + "1 - Create a KeyPair and add it to the list\n"
                    + "2 - Show the actual list\n"
                    + "3 - Search for KeyPairs\n"
                    + "4 - Delete KeyPairs with given ID\n"
                    + "5 - Exit program");
            String element = scanner.nextLine();

            switch (element) {
                case "1":
                    System.out.println("Which algorithm do you want to use? RSA/RSASSA/...");
                    String algo = scanner.nextLine();
                    if ("RSA".equals(algo)) {
                        MyKeyPairGeneratorRSA rsaGenerator = new MyKeyPairGeneratorRSA(2048);
                        System.out.println("Give me a KeyPair name");
                        String keyName = scanner.nextLine();
                        keyList.add(rsaGenerator.generateMyKeyPair(keyName));
                        System.out.println("A Key named " + keyName + " was added!\n");
                    } else {
                        System.out.println("Please state a valid certificate name!\n");
                    }
                    break;
                case "2":
                    System.out.println("Here are all saved KeyPairs with their properties: ");
                    System.out.println(keyList);
                    System.out.println();
                    break;
                case "3":
                    System.out.println("Search for certificate with id: \n");
                    try {
//                        String searched = scanner.nextLine();
//                        List<X509SelfSignedCert> founds = searchFor(certList, searched);
//                        System.out.println(founds);
                    } catch (Exception e) {
                        System.out.println("Please state a valid search term!\n");
                    }
                    break;
                case "4":
                    System.out.println("Which cert should be removed?");
                    try {
                        int id = scanner.nextInt();
                        if (id < 0 || id >= keyList.size()) {
                            throw new Exception();
                        }
                        keyList.remove(id);
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
                    System.out.println(Short.MAX_VALUE);
            }
        }
    }

    public static List<X509SelfSignedCert> searchFor(List<X509SelfSignedCert> certList, String searched) {
        List<X509SelfSignedCert> founds = new ArrayList<>();
        for (int i = 0; i < certList.size(); i++) {
            if (certList.get(i).toString().contains(searched)) {
                founds.add(certList.get(i));
            }
        }
        return founds;
    }
}
