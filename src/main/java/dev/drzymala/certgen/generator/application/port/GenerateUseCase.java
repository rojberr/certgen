package dev.drzymala.certgen.generator.application.port;

import java.math.BigDecimal;
import java.util.Date;

public interface GenerateUseCase {

    String generate(
            String version,
            BigDecimal serialNumber,
            String signatureAlgorithm,
            int keySize,
            String signatureHashAlgorithm,
            String issuer,
            Date validFrom,
            Date validTill,
            String subject
    );
}
