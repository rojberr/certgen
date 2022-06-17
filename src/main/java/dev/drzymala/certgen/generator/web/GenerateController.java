package dev.drzymala.certgen.generator.web;

import dev.drzymala.certgen.generator.application.port.GenerateUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/generate")
public class GenerateController {

    private final GenerateUseCase generator;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void findAntPath() {
    }
}
