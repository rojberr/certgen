package certgen.Keys;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
abstract public class MyKeyPairGenerator {

    @NonNull
    private final String genName;

    abstract MyKeyPair generateMyKeyPair(String name);

    abstract String getAlgorithm();
}
