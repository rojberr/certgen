package certgen.Keys;

import java.security.NoSuchAlgorithmException;

public interface KeyPairGeneratorInterface {

    public KeyPair generateKeyPair() throws NoSuchAlgorithmException;

    public String getAlgorithm();
}
