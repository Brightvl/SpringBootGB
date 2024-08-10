package ru.gb.timesheet.model;

import lombok.Data;

import java.time.LocalDate;

/**
 * Описание структуры json-ответа на REST-запросы.
 * Т.е. запросы, ответ на которые - JSON.
 */
@Data
public class Timesheet {
    /**
     * Идентификатор таймшита
     */

    private Long id;
    /**
     * Идентификатор проекта, к которому относится таймшит
     */

    private Long projectId;
    /**
     * Количество минут, затраченных на проект
     */

    private int minutes;
    /**
     * Дата создания таймшита
     */

    private LocalDate createdAt;

}
