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
    private final String country;
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

    public X509SelfSignedCert(Date validFrom, Date validTill, byte[] publicKey, String signAlgName, String country,
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
                + "Signature Algorithm: rsassaPss \n"
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
                + "00:bc:90:5a:cb:85:6a:0a:93:4c:2a:26:0c:c9:2b\n"
                + "Exponent: 65537 (0x10001)" + "\n"
                + "X509v3 extensions:" + "\n"
                + "X509v3 Basic Constraints:" + "\n"
                + "CA:FALSE" + "\n"
                + "X509v3 Key Usage: critical" + "\n"
                + "Digital Signature, Key Encipherment" + "\n"
                + "X509v3 Extended Key Usage:" + "\n"
                + "E-mail Protection" + "\n"
                + "X509v3 Subject Key Identifier:" + "\n"
                + "8C:D6:66:96:33:BA:0D:61:AF:37:0D:CA:7F:AA:B1:B7:84:73:8B:D6" + "\n"
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
                        75:39:58:77:5a:3e:59:ff:48:3c:a1:17:13:52:9b:1a:da:7c:
        70:09:9b:be:32:77:fd:d6:de:d2:01:28:36:ab:8b:aa:79:a4:
        4e:2c:24:b5:52:aa:d0:68:60:9e:eb:42:9c:80:c2:2d:6b:60:
        0c:96:5e:df:82:94:b2:f8:e7:ac:e5:c6:e4:7a:23:52:b8:c9:
        90:e7:fe:2f:2d:5a:88:73:77:92:6e:f1:83:ee:a6:c1:df:18:
        bf:32:4d:24:75:a6:96:04:26:76:7c:16:3a:0e:ad:a7:b1:bf:
        b9:1b:64:1c:72:70:a4:7d:4d:b1:44:c6:ad:4a:75:d5:be:3a:
        2c:44
                 */
                + "-----BEGIN CERTIFICATE-----\n"
                + Base64.getEncoder().encodeToString(publicKey) + "\n"
                + "-----END CERTIFICATE-----\n";
    }
}

