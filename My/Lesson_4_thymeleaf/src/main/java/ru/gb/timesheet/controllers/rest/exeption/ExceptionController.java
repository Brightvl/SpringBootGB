package ru.gb.timesheet.controllers.rest.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

/**
 * Контроллер для обработки исключений в REST API.
 */
@RestControllerAdvice(basePackageClasses = ExceptionController.class)
public class ExceptionController {
    /**
     * Обработчик всех исключений.
     *
     * @param e исключение
     * @return ответ с ошибкой сервера
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setReason(e.getMessage());
        return ResponseEntity.internalServerError().body(exceptionResponse);
    }

    /**
     * Обработчик исключений IllegalArgumentException.
     *
     * @param e исключение
     * @return ответ с ошибкой клиента
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().build();
    }

    /**
     * Обработчик исключений NoSuchElementException.
     *
     * @param e исключение
     * @return ответ с ошибкой "не найдено"
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
