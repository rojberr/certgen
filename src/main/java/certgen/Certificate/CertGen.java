package certgen.Certificate;

import certgen.Keys.KeyPair;
import certgen.Keys.KeyPairGeneratorRSA;


public class CertGen {

    public void generate() {
        KeyPairGeneratorRSA keyPairGeneratorRSA = new KeyPairGeneratorRSA();
        KeyPair key = keyPairGeneratorRSA.generateKeyPair();
    }
}
