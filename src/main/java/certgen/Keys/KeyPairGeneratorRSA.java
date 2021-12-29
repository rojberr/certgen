package certgen.Keys;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class KeyPairGeneratorRSA implements KeyPairGeneratorInterface {

    private final String algorithm = "RSA";
    private final int keySize = 1024;
    private KeyPair keyPair;

    public KeyPairGeneratorRSA() {
    }

    @Override
    // I use KeyPairGenerator from java.security for brevity reasons
    // I will try to implement RSA myself later, to get to know it better (reverse eng ftw)
    public certgen.Keys.KeyPair generateKeyPair() {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGenerator.initialize(keySize);
        this.keyPair = keyPairGenerator.genKeyPair();
        return new certgen.Keys.KeyPair(this.keyPair.getPublic().getEncoded(), this.keyPair.getPrivate().getEncoded());
    }

    @Override
    public String getAlgorithm() {
        return this.algorithm;
    }
}
