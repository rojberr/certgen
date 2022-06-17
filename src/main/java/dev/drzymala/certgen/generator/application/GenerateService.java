package dev.drzymala.certgen.generator.application;

import dev.drzymala.certgen.generator.application.port.GenerateUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenerateService implements GenerateUseCase {

    @Override
    public void generate() {
        return;
    }
}
