package dev.drzymala.certgen.generator.web;

import dev.drzymala.certgen.generator.application.port.GenerateUseCase;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping
public class GenerateController {

    private final GenerateUseCase generator;

    @GetMapping("/generateX509")
    @ResponseStatus(HttpStatus.OK)
    public HttpStatus generateX509(
            @RequestParam(value = "rsaKeySize", required = false) int rsaKeySize,
            @RequestParam(value = "commonName", required = false) String commonName,
            @RequestParam(value = "validFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date validFrom,
            @RequestParam(value = "validTill", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date validTill
    ) {
        generator.generate(rsaKeySize, commonName, validFrom, validTill);
        return HttpStatus.OK;
    }
}
