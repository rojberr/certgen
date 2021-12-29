package certgen.Keys;

public class KeyPair {

    private final PublicKey publicKey;
    private final PrivateKey privateKey;

    public KeyPair(byte[] publicKey, byte[] privateKey) {
        this.publicKey = new PublicKey(publicKey);
        this.privateKey = new PrivateKey(privateKey);
    }

    byte[] getPublic() {
        return publicKey.getPublicKey();
    }

    byte[] getPrivate() {
        return privateKey.getPrivateKey();
    }
}
