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
        return "-----BEGIN CERTIFICATE-----\n"
                + id + "\n"
                + Base64.getEncoder().encodeToString(publicKey) + "\n"
                + "-----END CERTIFICATE-----\n";
    }
}
