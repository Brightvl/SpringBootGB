package ru.gb.timesheet.model;

import lombok.Data;

/**
 * Класс, представляющий проект.
 */
@Data
public class Project {
    /**
     * Идентификатор проекта
     */
    private Long id;
    /**
     * Название проекта
     */
    private String name;

}
