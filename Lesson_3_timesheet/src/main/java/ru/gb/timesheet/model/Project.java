package ru.gb.timesheet.model;

import lombok.Data;

/**
 * Модель данных для проекта.
 */
@Data
public class Project {
    /**
     * Идентификатор проекта.
     */
    private Long id;
    /**
     * Название проекта.
     */
    private String name;
}
