package certgen.Certificate;

import lombok.Getter;

import java.util.Base64;
import java.util.Date;

@Getter
public class X509SelfSignedCert extends SelfSignedCert {

    private static long count = 0;
    private long id = 0;

    public X509SelfSignedCert(Date validFrom, Date validTill, byte[] publicKey, String signAlgName, Country country,
                              String state, String locality, String organization, String dns, String ip, String url,
                              String email, String rid, String dirName, String otherName) {
        super(validFrom, validTill, publicKey, signAlgName, country, state, locality, organization, dns, ip, url, email, rid, dirName, otherName);
        this.id = count;
        incrementTheCount();
    }

    private static void incrementTheCount() {

        count++;
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
                + "Issuer: C=" + super.getCountry() + ", ST=" + super.getState() + ", L=" + super.getLocality() + ", O=" + super.getOrganization() + ", OU=" + super.getDns() + ", CN=" + super.getIp() + super.getUrl() + super.getEmail() + super.getRid() + super.getDirName() + super.getOtherName() + "\n"
                + "Validity\n"
                + "Not Before: " + super.getValidFrom() + "\n" // f.e. Feb  2 02:02:00 2021 GMT
                + "Not After :" + super.getValidTill() + "\n" // f.e. Feb  2 02:02:00 2022 GMT
                + "Subject: C=" + super.getCountry() + ", ST=" + super.getState() + ", L=" + super.getLocality() + ", O=" + super.getOrganization() + ", OU=" + super.getDns() + ", CN=" + super.getIp() + super.getUrl() + super.getEmail() + super.getRid() + super.getDirName() + super.getOtherName() + "\n"
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
                + "email:" + super.getEmail() + "\n"
                + "X509v3 CRL Distribution Points:" + "\n"
                + "Full Name:" + "\n"
                + "URI:" + super.getUrl() + "\n"
                + "X509v3 Subject Alternative Name:" + "\n"
                + "email:" + super.getEmail() + "\n"
                + "Signature Algorithm: RSA" + "\n"
                + "Hash Algorithm: sha256" + "\n"
                + "Mask Algorithm: mgf1 with sha256" + "\n"
                + "Salt Length: 0x40" + "\n"
                + "Trailer Field: 0xBC (default)" + "\n"
                /*
                b9:1b:...
                 */
                + "-----BEGIN CERTIFICATE-----\n"
                + Base64.getEncoder().encodeToString(super.getPublicKey()) + "\n"
                + "-----END CERTIFICATE-----\n";
    }
}

