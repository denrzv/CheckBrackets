package io.github.denrzv.checkbrackets.service;

import io.github.denrzv.checkbrackets.model.ValidationResult;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;
/**
 * Реализация стратегии для проверки наличия круглых скобок в тексте
 */
@Component
public class RoundBracketsValidationStrategy implements TextValidationStrategy {

    private static final char OPEN_BRACKET = '(';
    private static final char CLOSE_BRACKET = ')';

    @Override
    public ValidationResult validate(String text) {
        Deque<Character> stack = new ArrayDeque<>();
        boolean insideBrackets = false; // Флаг для нахождения внутри скобок
        boolean hasInsideText = false; // Флаг - признак наличия текста внутри скобок
        boolean hasOnlyWhitespaceInside = true; // Флаг для случая пробелов вместо текста внутри скобок

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == OPEN_BRACKET) {
                stack.push(ch);
                insideBrackets = true;
                hasInsideText = false; // Сброс флага, когда открывается следующая пара скобок
                hasOnlyWhitespaceInside = true;
            } else if (ch == CLOSE_BRACKET) {
                if (stack.isEmpty() || !hasInsideText || hasOnlyWhitespaceInside) {
                    return new ValidationResult(false);
                }
                stack.pop();
                insideBrackets = false;
            } else if (insideBrackets) {
                hasInsideText = true;
                if (!Character.isWhitespace(ch)) {
                    hasOnlyWhitespaceInside = false;  // Обновляем флаг, если найден отличный от пробела символ
                }
            }
        }

        return stack.isEmpty() ? new ValidationResult(true) : new ValidationResult(false);
    }
}
