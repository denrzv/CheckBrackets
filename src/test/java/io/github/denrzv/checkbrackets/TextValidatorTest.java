package io.github.denrzv.checkbrackets;

import io.github.denrzv.checkbrackets.model.TextInput;
import io.github.denrzv.checkbrackets.model.ValidationResult;
import io.github.denrzv.checkbrackets.service.RoundBracketsValidationStrategy;
import io.github.denrzv.checkbrackets.service.TextValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TextValidatorTest {

    private final TextValidator validator = new TextValidator(new RoundBracketsValidationStrategy());

    @Test
    @DisplayName("Валидация корректного текста")
    public void testValidTextWithBrackets() {
        ValidationResult result = validator.isValid(new TextInput("Это корректный (тест)"));
        assertTrue(result.isCorrect());
    }

    @Test
    @DisplayName("Валидация некорректного текста")
    public void testInvalidTextWithBrackets() {
        ValidationResult result = validator.isValid(new TextInput("Это некорректный (тест"));
        assertFalse(result.isCorrect());
    }

    @Test
    @DisplayName("Валидация некорректного текста")
    public void testIWhiteSpacesWithBrackets() {
        ValidationResult result = validator.isValid(new TextInput("(   )"));
        assertFalse(result.isCorrect());
    }

    @Test
    @DisplayName("Валидация некорректного текста с пробелами")
    public void testWhiteSpaces() {
        assertThrows(IllegalArgumentException.class, () -> validator.isValid(new TextInput("   ")));
    }

    @Test
    @DisplayName("Валидация пустого текста")
    public void testEmptyText() {
        assertThrows(IllegalArgumentException.class, () -> validator.isValid(new TextInput("")));
    }

    @Test
    @DisplayName("Валидация null")
    public void testNullTextWithBrackets() {
        assertThrows(IllegalArgumentException.class, () -> validator.isValid(new TextInput(null)));
    }
}
