package ru.gb.timesheet.controllers.page.DTO;

import lombok.Data;

/**
 * Класс (DTO Data Transfer Object), который описывает параметры для шаблоново HTML-страниц.
 * Т.е. он нужен для передачи параметров внутрь thymeleaf в тех контроллерах, которые сразу отдают HTML-страницы.
 */
@Data
public class ProjectPageDto {
    /**
     * Идентификатор проекта
     */
    private Long id;
    /**
     * Название проекта
     */
    private String name;

}
