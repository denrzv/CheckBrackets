package io.github.denrzv.checkbrackets.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Управление исключениями приложения
 */
@RestControllerAdvice
public class IllegalArgumentExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleIllegalArgument() {
        return "Некорректное тело запроса, текст не может быть пустым! " +
                "Пример корректного запроса: {\"text\" : \"текст для валидации\"}";
    }
}
