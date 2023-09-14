package io.github.denrzv.checkbrackets.controller;

import io.github.denrzv.checkbrackets.model.TextInput;
import io.github.denrzv.checkbrackets.model.ValidationResult;
import io.github.denrzv.checkbrackets.service.TextValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Контроллер для REST API
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ValidationController {
    private final TextValidator textValidator;
    private static final Logger logger = LoggerFactory.getLogger(ValidationController.class);

    @Operation(summary = "Проверка текста на корректность расстановки круглых скобок")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Результат проверки"),
            @ApiResponse(responseCode = "400", description = "При пустом тексте")
    })
    @PostMapping("/checkBrackets")
    public ValidationResult checkBrackets(@RequestBody TextInput textInput) {
        logger.info("Запрос на валидацию текста: " + textInput);
        return textValidator.isValid(textInput);
    }
}