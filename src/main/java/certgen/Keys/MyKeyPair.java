package certgen.Keys;

import lombok.Getter;

@Getter
public class MyKeyPair {

    private final MyKey publicMyKey;
    private final MyKey privateMyKey;

    public MyKeyPair(String name, byte[] publicKey, byte[] privateKey) {
        this.publicMyKey = new MyKey(name, publicKey);
        this.privateMyKey = new MyKey(name, privateKey);
    }

    @Override
    public String toString() {
        return publicMyKey.getName() + " " + privateMyKey.getName();
    }
}
