package dev.drzymala.certgen.generator.web;

import dev.drzymala.certgen.generator.application.port.GenerateUseCase;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping
public class GenerateController {

    private final GenerateUseCase generator;

    @GetMapping("/generateX509")
    @ResponseStatus(HttpStatus.OK)
    public HttpStatus generateX509(
            @RequestParam(value = "version", required = false) String version,
            @RequestParam(value = "serialNumber", required = false) BigDecimal serialNumber,
            @RequestParam(value = "signatureAlgorithm", required = false) String signatureAlgorithm,
            @RequestParam(value = "keySize", required = false) int keySize,
            @RequestParam(value = "signatureHashAlgorithm", required = false) String signatureHashAlgorithm,
            @RequestParam(value = "issuer", required = false) String issuer,
            @RequestParam(value = "validFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date validFrom,
            @RequestParam(value = "validTill", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date validTill,
            @RequestParam(value = "subject", required = false) String subject
    ) {
        generator.generate(version,
                serialNumber,
                signatureAlgorithm,
                keySize,
                signatureHashAlgorithm,
                issuer,
                validFrom,
                validTill,
                subject);
        return HttpStatus.OK;
    }
}
