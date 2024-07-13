package ru.gb.timesheet.controllers.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.timesheet.controllers.page.DTO.ProjectPageDto;
import ru.gb.timesheet.service.page.ProjectPageService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Контроллер для отображения страниц проектов
 */
@Controller
@RequestMapping("/home/projects")
@RequiredArgsConstructor
public class ProjectPageController {

    private final ProjectPageService service;


    /**
     * Отображение страницы всех проектов.
     *
     * @param model модель
     * @return путь к HTML-странице всех таймшитов
     */
    @GetMapping
    public String getAllProjects(Model model) {
        List<ProjectPageDto> projects = service.findAll();
        model.addAttribute("projects", projects);
        return "projects-page.html";
    }

    /**
     * Отображение страницы конкретного проекта по идентификатору.
     *
     * @param id    идентификатор проекта
     * @param model модель
     * @return путь к HTML-странице проекта
     */
    // GET /home/projects/{id}
    @GetMapping("/{id}")
    public String getProjectPage(@PathVariable Long id, Model model) {
        Optional<ProjectPageDto> projectOpt = service.findById(id);
        if (projectOpt.isEmpty()) {
            throw new NoSuchElementException();
        }

        model.addAttribute("project", projectOpt.get());
        return "project-page.html";
    }

}
