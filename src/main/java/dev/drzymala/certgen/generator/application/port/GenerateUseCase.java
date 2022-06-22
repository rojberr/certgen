package dev.drzymala.certgen.generator.application.port;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Date;

public interface GenerateUseCase {

    void generate(
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
