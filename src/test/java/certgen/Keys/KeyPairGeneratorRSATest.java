package certgen.Keys;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyPairGeneratorRSATest {

    @Test
    void generateKeyPair() {

        KeyPairGeneratorRSA keyPairGeneratorRSA = new KeyPairGeneratorRSA();
        keyPairGeneratorRSA.generateKeyPair();
    }
}