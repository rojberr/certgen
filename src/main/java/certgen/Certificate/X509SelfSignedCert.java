package certgen.Certificate;

import certgen.Keys.KeyPair;
import certgen.Keys.PublicKey;
import lombok.Getter;
import lombok.Setter;

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

    public X509SelfSignedCert() {
        this.id = count++;
    }
}
