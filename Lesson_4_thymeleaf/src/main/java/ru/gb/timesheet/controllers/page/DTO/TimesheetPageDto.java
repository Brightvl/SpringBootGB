package ru.gb.timesheet.controllers.page.DTO;

import lombok.Data;

/**
 * Класс (DTO Data Transfer Object), который описывает параметры для шаблоново HTML-страниц.
 * Т.е. он нужен для передачи параметров внутрь thymeleaf в тех контроллерах, которые сразу отдают HTML-страницы.
 */
@Data
public class TimesheetPageDto {
    /**
     * Название проекта
     */

    private String projectName;
    /**
     * Идентификатор таймшита
     */

    private String id;
    /**
     * Количество минут, затраченных на проект
     */

    private String minutes;
    /**
     * Дата создания таймшита
     */

    private String createdAt;

    /**
     * Идентификатор проекта
     */
    private Long projectId;

}
