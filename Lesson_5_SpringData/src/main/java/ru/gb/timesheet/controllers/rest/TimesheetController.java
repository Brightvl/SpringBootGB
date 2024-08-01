package ru.gb.timesheet.controllers.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.rest.TimesheetService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/timesheets")
public class TimesheetController {

    private final TimesheetService service;

    public TimesheetController(TimesheetService service) {
        this.service = service;
    }

    @Operation(
            summary = "Получить запись о рабочем времени",
            description = "Возвращает запись о рабочем времени по её идентификатору",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = Timesheet.class))),
                    @ApiResponse(description = "Запись не найдена", responseCode = "404", content = @Content(schema = @Schema(implementation = Void.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Timesheet> get(@PathVariable @Parameter(description = "Идентификатор записи") Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Получить все записи о рабочем времени",
            description = "Возвращает список всех записей о рабочем времени, возможно с фильтрацией по дате создания",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = Timesheet.class))),
                    @ApiResponse(description = "Ошибка запроса", responseCode = "400", content = @Content(schema = @Schema(implementation = Void.class)))
            }
    )
    @GetMapping
    public ResponseEntity<List<Timesheet>> getAll(
            @RequestParam(required = false) @Parameter(description = "Фильтр по дате создания до") LocalDate createdAtBefore,
            @RequestParam(required = false) @Parameter(description = "Фильтр по дате создания после") LocalDate createdAtAfter
    ) {
        return ResponseEntity.ok(service.findAll(createdAtBefore, createdAtAfter));
    }

    @Operation(
            summary = "Создать запись о рабочем времени",
            description = "Создает новую запись о рабочем времени",
            responses = {
                    @ApiResponse(description = "Запись успешно создана", responseCode = "201", content = @Content(schema = @Schema(implementation = Timesheet.class))),
                    @ApiResponse(description = "Ошибка запроса", responseCode = "400", content = @Content(schema = @Schema(implementation = Void.class)))
            }
    )
    @PostMapping
    public ResponseEntity<Timesheet> create(@RequestBody @Parameter(description = "Данные новой записи") Timesheet timesheet) {
        final Timesheet created = service.create(timesheet);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(
            summary = "Удалить запись о рабочем времени",
            description = "Удаляет запись о рабочем времени по её идентификатору",
            responses = {
                    @ApiResponse(description = "Запись успешно удалена", responseCode = "204", content = @Content(schema = @Schema(implementation = Void.class))),
                    @ApiResponse(description = "Запись не найдена", responseCode = "404", content = @Content(schema = @Schema(implementation = Void.class)))
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Идентификатор записи") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.notFound().build();
    }
}
