package io.github.denrzv.checkbrackets.service;

import io.github.denrzv.checkbrackets.model.ValidationResult;

/**
 * Универсальный интерфейс, который можно реализовать для разных стратегий проверки входного текста.
 */
public interface TextValidationStrategy {
    /**
      * @param text текст для валидации
      * @return результат проверки текста
     */

    ValidationResult validate(String text);
}
