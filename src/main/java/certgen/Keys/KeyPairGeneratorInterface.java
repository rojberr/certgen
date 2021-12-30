package certgen.Keys;

import java.security.NoSuchAlgorithmException;

public interface KeyPairGeneratorInterface {

    KeyPair generateKeyPair() throws NoSuchAlgorithmException;

    String getAlgorithm();
}
