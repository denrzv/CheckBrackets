package io.github.denrzv.checkbrackets.service;

import io.github.denrzv.checkbrackets.model.TextInput;
import io.github.denrzv.checkbrackets.model.ValidationResult;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Сервис - валидатор, который использует стратегию для проверки текста.
 */
@Service
@AllArgsConstructor
public class TextValidator {
    private final TextValidationStrategy strategy;
    private static final Logger logger = LoggerFactory.getLogger(TextValidator.class);


    public ValidationResult isValid(TextInput input) {

        /*
         * Проверяет на валидность TextInput и возвращает ValidationResult.
         *
         * @param input текстовый параметр для валидации
         * @return результат валидации
         * @throws IllegalArgumentException если входной параметр равен null или пустой
         */

        return Optional.ofNullable(input.text())
                .filter(t -> !t.trim().isEmpty())
                .map(this.strategy::validate)
                .orElseThrow(() -> {
                    logger.error("Ошибка в запросе! Некорректно составленный запрос.");
                    throw new IllegalArgumentException("Некорректный запрос, не найден текст для валидации!");
                });
    }
}