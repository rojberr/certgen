package certgen.Keys;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class MyKeyPairGeneratorRSA extends MyKeyPairGenerator {

    private final String algorithm = "RSA";
    private final int keySize;
    private KeyPair keyPair;

    public MyKeyPairGeneratorRSA(int keySize) {
        super("name");
        this.keySize = keySize;
    }

    @Override
    // I use KeyPairGenerator from java.security for brevity reasons
    // I will try to implement RSA myself later, to get to know it better (reverse eng ftw)
    public MyKeyPair generateMyKeyPair(String name) {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGenerator.initialize(keySize);
        this.keyPair = keyPairGenerator.genKeyPair();
        return new MyKeyPair(name, this.keyPair.getPublic().getEncoded(), this.keyPair.getPrivate().getEncoded());
    }

    @Override
    public String getAlgorithm() {
        return this.algorithm;
    }
}
