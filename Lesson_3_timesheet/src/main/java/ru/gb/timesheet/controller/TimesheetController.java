package ru.gb.timesheet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.TimesheetService;

import java.util.List;
import java.util.Optional;

/*
Контроллеры обрабатывают HTTP-запросы от клиентов (например, браузеров) и возвращают ответы.
Они взаимодействуют с сервисами для выполнения операций над данными.

Почему это важно:
Контроллеры отвечают за прием запросов и отправку ответов, что делает их интерфейсом между клиентом и сервером.
Это упрощает тестирование и разработку, так как логика обработки запросов и бизнес-логика разделены.
 */

/**
 * Контроллер для управления табелями учета рабочего времени (Timesheet). Обрабатывает HTTP-запросы для выполнения CRUD
 * операций.
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

    // /timesheets/{id}

    /**
     * Получить табель учета рабочего времени по идентификатору.
     *
     * @param id идентификатор табеля.
     * @return ResponseEntity с табелем учета рабочего времени и статусом OK, или статусом NOT FOUND, если табель не
     * найден.
     */
    @GetMapping("/{id}") // получить все
    public ResponseEntity<Timesheet> get(@PathVariable Long id) {
        Optional<Timesheet> ts = service.getById(id);

        if (ts.isPresent()) {
//      return ResponseEntity.ok().body(ts.get());
            return ResponseEntity.status(HttpStatus.OK).body(ts.get());
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Получить все табели учета рабочего времени.
     *
     * @return ResponseEntity со списком всех табелей и статусом OK.
     */
    @GetMapping // получить все
    public ResponseEntity<List<Timesheet>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    /**
     * Создать новый табель учета рабочего времени.
     *
     * @param timesheet данные нового табеля в формате JSON.
     * @return ResponseEntity с созданным табелем и статусом CREATED.
     */
    @PostMapping // создание нового ресурса
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet) {
        timesheet = service.create(timesheet);

        // 201 Created
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

        // 204 No Content
        return ResponseEntity.noContent().build();
    }

}
