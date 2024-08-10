package ru.gb.timesheet.controllers.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.service.rest.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @Operation(
            summary = "Получить проект",
            description = "Возвращает проект по его идентификатору",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = Project.class))),
                    @ApiResponse(description = "Проект не найден", responseCode = "404", content = @Content(schema = @Schema(implementation = Void.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Project> get(@PathVariable @Parameter(description = "Идентификатор проекта") Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Получить все проекты",
            description = "Возвращает список всех проектов",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = Project.class)))
            }
    )
    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(
            summary = "Создать проект",
            description = "Создает новый проект",
            responses = {
                    @ApiResponse(description = "Проект успешно создан", responseCode = "201", content = @Content(schema = @Schema(implementation = Project.class))),
                    @ApiResponse(description = "Ошибка запроса", responseCode = "400", content = @Content(schema = @Schema(implementation = Void.class)))
            }
    )
    @PostMapping
    public ResponseEntity<Project> create(@RequestBody @Parameter(description = "Данные нового проекта") Project project) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(project));
    }

    @Operation(
            summary = "Удалить проект",
            description = "Удаляет проект по его идентификатору",
            responses = {
                    @ApiResponse(description = "Проект успешно удален", responseCode = "204", content = @Content(schema = @Schema(implementation = Void.class))),
                    @ApiResponse(description = "Проект не найден", responseCode = "404", content = @Content(schema = @Schema(implementation = Void.class)))
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Идентификатор проекта") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
