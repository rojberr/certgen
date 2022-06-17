package certgen.Certificate;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class SelfSignedCert {

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
}
