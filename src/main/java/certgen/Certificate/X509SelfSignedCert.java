package certgen.Certificate;

import certgen.Keys.PublicKey;
import lombok.Getter;

import java.io.*;
import java.util.Date;

@Getter
public class X509SelfSignedCert {

    private static long count = 0;
    private long id = 0;
    private Date validFrom;
    private Date validTill;
    private PublicKey publicKey;
    private String signAlgName;
    private long serialNumber;
    private String country;
    private String state;
    private String locality;
    private String organization;
    private String dns;
    private String ip;
    private String url;
    private String email;
    private String rid;
    private String dirName;
    private String otherName;

    public X509SelfSignedCert() throws IOException {
        this.id = count++;
        this.saveToPEM();
    }

    private void saveToPEM() throws IOException {
        try {
            Writer output = null;
            File file = new File(id + ".pem");
            output = new BufferedWriter(new FileWriter(file));
            output.write(String.valueOf(this));
            output.close();
        } catch (Exception e) {
            System.out.println("Could not create file");
        }
    }

    @Override
    public String toString() {
        return "-----BEGIN CERTIFICATE-----\n"
                + id + "\n"
                + "-----END CERTIFICATE-----\n";
    }
}
