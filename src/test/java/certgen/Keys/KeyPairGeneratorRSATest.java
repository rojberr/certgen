package certgen.Keys;

import org.junit.jupiter.api.Test;

class KeyPairGeneratorRSATest {

    @Test
    void generateKeyPair() {

        KeyPairGeneratorRSA keyPairGeneratorRSA = new KeyPairGeneratorRSA();
        keyPairGeneratorRSA.generateKeyPair();
    }
}