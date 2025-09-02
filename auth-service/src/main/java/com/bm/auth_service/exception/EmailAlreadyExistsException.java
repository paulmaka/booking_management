package com.bm.auth_service.exception;

/**
 * Класс ошибки при попытке создания нового пользователя с уже занятым адресом электронной почты.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
