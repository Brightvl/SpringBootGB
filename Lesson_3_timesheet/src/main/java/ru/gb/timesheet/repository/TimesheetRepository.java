package ru.gb.timesheet.repository;

import org.springframework.stereotype.Repository;
import ru.gb.timesheet.model.Timesheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Репозиторий для управления табелями учета рабочего времени.
 */
@Repository // @Component для классов, работающих с данными
public class TimesheetRepository {

    /**
     * Порядковый номер id
     */
    private static Long sequence = 1L;
    /**
     * Список табелей
     */
    private final List<Timesheet> timesheets = new ArrayList<>();

    /**
     * Найти табель по идентификатору.
     *
     * @param id идентификатор табеля.
     * @return Optional с табелем или пустой Optional, если табель не найден.
     */
    public Optional<Timesheet> getById(Long id) {
        // select * from timesheets where id = $id
        return timesheets.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst();
    }

    /**
     * Получить все табели.
     *
     * @return список всех табелей.
     */
    public List<Timesheet> getAll() {
        return List.copyOf(timesheets);
    }

    /**
     * Создать новый табель.
     *
     * @param timesheet данные нового табеля.
     * @return созданный табель.
     */
    public Timesheet create(Timesheet timesheet) {
        timesheet.setId(sequence++);
        timesheets.add(timesheet);
        return timesheet;
    }

    /**
     * Удалить табель по идентификатору.
     *
     * @param id идентификатор табеля.
     */
    public void delete(Long id) {
        timesheets.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .ifPresent(timesheets::remove); // если нет - иногда посылают 404 Not Found
    }

}
