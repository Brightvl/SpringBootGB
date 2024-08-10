package ru.gb.timesheet.controllers.rest.exeption;

import lombok.Data;

/**
 * Класс для передачи информации об ошибке.
 */
@Data
public class ExceptionResponse {
    /**
     * Причина ошибки
     */

    private String reason;

}
