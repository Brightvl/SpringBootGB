package ru.gb.timesheet.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.rest.TimesheetService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Контроллер для управления таймшитами.
 */
@RestController
@RequestMapping("/timesheets")
public class TimesheetController {

    // GET - получить - не содержит тела
    // POST - create
    // PUT - изменение
    // PATCH - изменение
    // DELETE - удаление

    // @GetMapping("/timesheets/{id}") // получить конкретную запись по идентификатору
    // @DeleteMapping("/timesheets/{id}") // удалить конкретную запись по идентификатору
    // @PutMapping("/timesheets/{id}") // обновить конкретную запись по идентификатору

    private final TimesheetService service;

    public TimesheetController(TimesheetService service) {
        this.service = service;
    }

    /**
     * Получение таймшита по идентификатору.
     *
     * @param id идентификатор таймшита
     * @return найденный таймшит, если существует
     */
    @GetMapping("/{id}") // получить все
    public ResponseEntity<Timesheet> get(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // /timesheets
    // /timesheets?createdAtBefore=2024-07-09
    // /timesheets?createdAtAfter=2024-07-15
    // /timesheets?createdAtAfter=2024-07-15&createdAtBefore=2024-06-05

    /**
     * Получение всех таймшитов с возможностью фильтрации по дате создания.
     *
     * @param createdAtBefore дата, до которой были созданы таймшиты
     * @param createdAtAfter  дата, после которой были созданы таймшиты
     * @return список отфильтрованных таймшитов
     */
    @GetMapping
    public ResponseEntity<List<Timesheet>> getAll(
            @RequestParam(required = false) LocalDate createdAtBefore,
            @RequestParam(required = false) LocalDate createdAtAfter
    ) {
        return ResponseEntity.ok(service.findAll(createdAtBefore, createdAtAfter));
    }

    // client -> [spring-server -> ... -> TimesheetController
    //                          -> exceptionHandler(e)
    // client <- [spring-server <- ...

    /**
     * Создание нового таймшита.
     *
     * @param timesheet создаваемый таймшит
     * @return созданный таймшит
     */
    @PostMapping // создание нового ресурса
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet) {
        final Timesheet created = service.create(timesheet);

        // 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Удаление таймшита по идентификатору.
     *
     * @param id идентификатор таймшита
     * @return ответ без содержимого
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        // 204 No Content
        return ResponseEntity.noContent().build();
    }

    /**
     * Обработчик исключений NoSuchElementException.
     *
     * @param e исключение
     * @return ответ с ошибкой "не найдено"
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.notFound().build();
    }

    /**
     * выдает заметки по конкретному проекту
     * @param projectId id проекта
     * @return ответ без содержимого
     */
    @GetMapping("/projects/{projectId}")
    public ResponseEntity<List<Timesheet>> getTimesheetsByProjectId(@PathVariable Long projectId) {
        List<Timesheet> timesheets = service.getByProjectId(projectId);
        return ResponseEntity.ok(timesheets);
    }

}
