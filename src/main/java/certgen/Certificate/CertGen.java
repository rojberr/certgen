package certgen.Certificate;

import certgen.Keys.KeyPair;
import certgen.Keys.KeyPairGeneratorRSA;
import certgen.Keys.PublicKey;
import lombok.Data;

import java.io.*;
import java.util.Date;

@Data
public class CertGen {

    private Date validFrom;
    private Date validTill;
    private PublicKey publicKey;
    private String signAlgName;
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

    public X509SelfSignedCert generate() throws IOException {
        KeyPairGeneratorRSA keyPairGeneratorRSA = new KeyPairGeneratorRSA();
        KeyPair keyPair = keyPairGeneratorRSA.generateKeyPair();
        X509SelfSignedCert cert = new X509SelfSignedCert(validFrom, validTill, keyPair.getPublic(), keyPairGeneratorRSA.getAlgorithm(), country, state, locality, organization, dns, ip, url, email, rid, dirName, otherName);
        saveToPEM(cert);
        return cert;
    }

    private void saveToPEM(X509SelfSignedCert cert) throws IOException {
        try {
            Writer output = null;
            File file = new File(cert.getId() + ".pem");
            output = new BufferedWriter(new FileWriter(file));
            output.write(String.valueOf(cert));
            output.close();
        } catch (Exception e) {
            System.out.println("Could not create file");
        }
    }
}
