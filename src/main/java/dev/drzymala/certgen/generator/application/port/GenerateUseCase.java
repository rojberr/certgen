package dev.drzymala.certgen.generator.application.port;

import java.util.Date;

public interface GenerateUseCase {

    void generate(
            int RsaKeySize,
            String commonName,
            Date validFrom,
            Date validTill
    );
}
