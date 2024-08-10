package ru.gb.timesheet.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.rest.ProjectService;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Контроллер для управления проектами.
 */
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    /**
     * Получение проекта по идентификатору.
     *
     * @param id идентификатор проекта
     * @return найденный проект, если существует
     */
    @GetMapping("/{id}")
    public ResponseEntity<Project> get(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Получение всех таймшитов проекта по идентификатору проекта.
     *
     * @param id идентификатор проекта
     * @return список таймшитов проекта
     */
    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getTimesheets(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getTimesheets(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Получение всех проектов.
     *
     * @return список всех проектов
     */
    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    /**
     * Создание нового проекта.
     *
     * @param project создаваемый проект
     * @return созданный проект
     */
    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(project));
    }

    /**
     * Удаление проекта по идентификатору.
     *
     * @param id идентификатор проекта
     * @return ответ без содержимого
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }




}
