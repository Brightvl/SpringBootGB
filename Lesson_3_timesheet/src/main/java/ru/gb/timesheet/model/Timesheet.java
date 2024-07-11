package ru.gb.timesheet.model;

import lombok.Data;

import java.time.LocalDate;

/*
Модель представляет собой данные и их логику.
Это классы, которые соответствуют таблицам базы данных и содержат данные, которые приложение будет обрабатывать.
 */

/**
 * Модель данных для табеля учета рабочего времени.
 */
@Data
public class Timesheet {
    /**
     * Идентификатор табеля.
     */
    private Long id;
    /**
     * id проекта.
     */
    private Long projectId;
    /**
     * Количество минут, затраченных на проект.
     */
    private int minutes;
    /**
     * Дата создания табеля.
     */
    private LocalDate createdAt;

}
