package certgen.Certificate;

import lombok.Getter;

import java.util.Base64;
import java.util.Date;

@Getter
public class X509SelfSignedCert {

    private static long count = 0;
    private long id = 0;
    private final Date validFrom;
    private final Date validTill;
    private final byte[] publicKey;
    private final String signAlgName;
    private final Country country;
    private final String state;
    private final String locality;
    private final String organization;
    private final String dns;
    private final String ip;
    private final String url;
    private final String email;
    private final String rid;
    private final String dirName;
    private final String otherName;

    public X509SelfSignedCert(Date validFrom, Date validTill, byte[] publicKey, String signAlgName, Country country,
                              String state, String locality, String organization, String dns, String ip, String url,
                              String email, String rid, String dirName, String otherName) {
        this.id = count++;
        this.validFrom = validFrom;
        this.validTill = validTill;
        this.publicKey = publicKey;
        this.signAlgName = signAlgName;
        this.country = country;
        this.state = state;
        this.locality = locality;
        this.organization = organization;
        this.dns = dns;
        this.ip = ip;
        this.url = url;
        this.email = email;
        this.rid = rid;
        this.dirName = dirName;
        this.otherName = otherName;
    }

    @Override
    public String toString() {
        return "Certificate:\n"
                + "Data: " + "\n"
                + "Version: 3 (0x2)\n"
                + "Serial Number: " + id + "\n" // f.e. 1050518 (0x100796)
                + "Signature Algorithm: RSA \n"
                + "Hash Algorithm: sha256 \n"
                + "Mask Algorithm: mgf1 with sha256 \n"
                + "Salt Length: 0x40 \n"
                + "Trailer Field: 0xBC (default) \n"
                + "Issuer: C=" + country + ", ST=" + state + ", L=" + locality + ", O=" + organization + ", OU=" + dns + ", CN=" + ip + url + email + rid + dirName + otherName + "\n"
                + "Validity\n"
                + "Not Before: " + validFrom + "\n" // f.e. Feb  2 02:02:00 2021 GMT
                + "Not After :" + validTill + "\n" // f.e. Feb  2 02:02:00 2022 GMT
                + "Subject: C=" + country + ", ST=" + state + ", L=" + locality + ", O=" + organization + ", OU=" + dns + ", CN=" + ip + url + email + rid + dirName + otherName + "\n"
                + "Subject Public Key Info: \n"
                + "Public Key Algorithm: rsaEncryption\n"
                + "RSA Public-Key: 2048 bit\n"
                + "Modulus: \n"//
                + "00:ac\n"
                + "Exponent: 65537 (0x10001)" + "\n"
                + "X509v3 extensions:" + "\n"
                + "X509v3 Basic Constraints:" + "\n"
                + "CA:FALSE" + "\n"
                + "X509v3 Key Usage: critical" + "\n"
                + "Digital Signature, Key Encipherment" + "\n"
                + "X509v3 Extended Key Usage:" + "\n"
                + "E-mail Protection" + "\n"
                + "X509v3 Subject Key Identifier:" + "\n"
                + "8C:66" + "\n"
                + "X509v3 Authority Key Identifier:" + "\n"
                + "keyid:...:D5:..." + "\n"
                + "X509v3 Issuer Alternative Name:" + "\n"
                + "email:" + email + "\n"
                + "X509v3 CRL Distribution Points:" + "\n"
                + "Full Name:" + "\n"
                + "URI:" + url + "\n"
                + "X509v3 Subject Alternative Name:" + "\n"
                + "email:" + email + "\n"
                + "Signature Algorithm: rsassaPss" + "\n"
                + "Hash Algorithm: sha256" + "\n"
                + "Mask Algorithm: mgf1 with sha256" + "\n"
                + "Salt Length: 0x40" + "\n"
                + "Trailer Field: 0xBC (default)" + "\n"
                /*
                b9:1b:...
                 */
                + "-----BEGIN CERTIFICATE-----\n"
                + Base64.getEncoder().encodeToString(publicKey) + "\n"
                + "-----END CERTIFICATE-----\n";
    }
}

