package com.bm.auth_service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


/**
 * Класс - глобальный перехватчик ошибок. Обрабатывает ошибки, пробрасываемые приложением.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Перехватывает ошибки валидации.
     * @param exception возникшая ошибка валидации.
     * @return ResponseEntity с кодом 400 и телом с описанием конкретной ошибки валидации.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        log.warn("Invalid: {}", exception.getMessage());
        exception.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Перехватывает ошибки попытки создания пользователя с уже существующей в БД электронной почтой.
     * @param exception возникшая ошибка создания пользователя с существующим адресом электронной почты.
     * @return ResponseEntity с кодом 400 и телом с описанием ошибки.
     */
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception) {
        Map<String, String> errors = new HashMap<>();

        log.warn("Email address already used {}", exception.getMessage());
        errors.put("message", "Email address already used");
        return ResponseEntity.badRequest().body(errors);
    }
}
