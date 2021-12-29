package certgen.Keys;

import lombok.Getter;

@Getter
public class PrivateKey {

    private final byte[] privateKey;

    public PrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }
}
