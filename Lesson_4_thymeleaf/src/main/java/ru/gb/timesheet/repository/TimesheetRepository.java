package ru.gb.timesheet.repository;

import org.springframework.stereotype.Repository;
import ru.gb.timesheet.model.Timesheet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Репозиторий для управления таймшитами.
 */
@Repository
public class TimesheetRepository {

    private static Long sequence = 1L;
    private final List<Timesheet> timesheets = new ArrayList<>();

    /**
     * Поиск таймшита по идентификатору.
     *
     * @param id идентификатор таймшита
     * @return найденный таймшит, если существует
     */
    public Optional<Timesheet> findById(Long id) {
        return timesheets.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst();
    }

    /**
     * Поиск всех таймшитов с возможностью фильтрации по дате создания.
     *
     * @param createdAtBefore дата, до которой были созданы таймшиты
     * @param createdAtAfter  дата, после которой были созданы таймшиты
     * @return список отфильтрованных таймшитов
     */
    public List<Timesheet> findAll(LocalDate createdAtBefore, LocalDate createdAtAfter) {
        Predicate<Timesheet> filter = it -> true;

        if (Objects.nonNull(createdAtBefore)) {
            filter = filter.and(it -> it.getCreatedAt().isBefore(createdAtBefore));
        }

        if (Objects.nonNull(createdAtAfter)) {
            filter = filter.and(it -> it.getCreatedAt().isAfter(createdAtAfter));
        }

        return timesheets.stream()
                .filter(filter)
                .toList();
    }

    /**
     * Создание нового таймшита.
     *
     * @param timesheet создаваемый таймшит
     * @return созданный таймшит
     */
    public Timesheet create(Timesheet timesheet) {
        timesheet.setId(sequence++);
        timesheets.add(timesheet);
        return timesheet;
    }

    /**
     * Удаление таймшита по идентификатору.
     *
     * @param id идентификатор таймшита
     */
    public void delete(Long id) {
        timesheets.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .ifPresent(timesheets::remove);
    }

    /**
     * Поиск таймшитов по идентификатору проекта.
     *
     * @param projectId идентификатор проекта
     * @return список таймшитов проекта
     */
    public List<Timesheet> findByProjectId(Long projectId) {
        return timesheets.stream()
                .filter(it -> Objects.equals(it.getProjectId(), projectId))
                .toList();
    }

    /**
     * Возвращает заметку по id проекта
     * @param projectId
     * @return
     */
    public List<Timesheet> getByProjectId(Long projectId) {
        return timesheets.stream()
                .filter(it -> Objects.equals(it.getProjectId(), projectId))
                .collect(Collectors.toList());
    }
}
