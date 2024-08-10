package ru.gb.timesheet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.TimesheetService;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер для управления табелями учета рабочего времени (Timesheet). Обрабатывает HTTP-запросы для выполнения CRUD
 * операций.
 */
@RestController
@RequestMapping("/timesheets")
public class TimesheetController {

    private final TimesheetService service;

    public TimesheetController(TimesheetService service) {
        this.service = service;
    }

    /**
     * Получить табель учета рабочего времени по идентификатору.
     *
     * @param id идентификатор табеля.
     * @return ResponseEntity с табелем учета рабочего времени и статусом OK, или статусом NOT FOUND, если табель не
     * найден.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Timesheet> get(@PathVariable Long id) {
        Optional<Timesheet> ts = service.getById(id);

        if (ts.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(ts.get());
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Получить все табели учета рабочего времени.
     *
     * @return ResponseEntity со списком всех табелей и статусом OK.
     */
    @GetMapping
    public ResponseEntity<List<Timesheet>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    /**
     * Создать новый табель учета рабочего времени.
     *
     * @param timesheet данные нового табеля в формате JSON.
     * @return ResponseEntity с созданным табелем и статусом CREATED.
     */
    @PostMapping
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet) {
        timesheet = service.create(timesheet);
        return ResponseEntity.status(HttpStatus.CREATED).body(timesheet);
    }

    /**
     * Удалить табель учета рабочего времени по идентификатору.
     *
     * @param id идентификатор табеля.
     * @return ResponseEntity со статусом NO CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
