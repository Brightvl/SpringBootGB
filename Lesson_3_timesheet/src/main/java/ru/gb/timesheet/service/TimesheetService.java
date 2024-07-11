package ru.gb.timesheet.service;

import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.util.List;
import java.util.Optional;
/*
Сервисный слой содержит бизнес-логику приложения.
Он отвечает за обработку данных, полученных из репозиториев, и выполнение операций с ними.
 */

/**
 * Сервис для управления табелями учета рабочего времени.
 */
@Service // то же самое, что и Component
public class TimesheetService {

    private final TimesheetRepository repository;

    public TimesheetService(TimesheetRepository repository) {
        this.repository = repository;
    }

    /**
     * Найти табель по идентификатору.
     *
     * @param id идентификатор табеля.
     * @return Optional с табелем или пустой Optional, если табель не найден.
     */
    public Optional<Timesheet> getById(Long id) {
        return repository.getById(id);
    }

    /**
     * Получить все табели.
     *
     * @return список всех табелей.
     */
    public List<Timesheet> getAll() {
        return repository.getAll();
    }

    /**
     * Создать новый табель.
     *
     * @param timesheet данные нового табеля.
     * @return созданный табель.
     */
    public Timesheet create(Timesheet timesheet) {
        return repository.create(timesheet);
    }

    /**
     * Удалить табель по идентификатору.
     *
     * @param id идентификатор табеля.
     */
    public void delete(Long id) {
        repository.delete(id);
    }

}
