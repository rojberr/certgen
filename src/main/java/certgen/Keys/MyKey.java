package certgen.Keys;

import lombok.Getter;

@Getter
public class MyKey {

    long id;
    String name;
    private final byte[] key;
    private static long count = 0;

    public MyKey(String name, byte[] key) {

        this.name = name;
        this.key = key;
        this.id = count;
        incrementTheCount();
    }

    private static void incrementTheCount() {

        count++;
    }
}
