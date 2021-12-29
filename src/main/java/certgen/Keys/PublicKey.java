package certgen.Keys;

import lombok.Getter;

@Getter
public class PublicKey {

    private final byte[] publicKey;

    public PublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }
}
