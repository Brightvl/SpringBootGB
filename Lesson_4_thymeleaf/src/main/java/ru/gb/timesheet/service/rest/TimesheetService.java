package ru.gb.timesheet.service.rest;

import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

/**
 * Сервис для управления таймшитами.
 */
@Service
public class TimesheetService {

    private final TimesheetRepository timesheetRepository;
    private final ProjectRepository projectRepository;

    public TimesheetService(TimesheetRepository repository, ProjectRepository projectRepository) {
        this.timesheetRepository = repository;
        this.projectRepository = projectRepository;
    }

    /**
     * Поиск таймшита по идентификатору.
     *
     * @param id идентификатор таймшита
     * @return найденный таймшит, если существует
     */
    public Optional<Timesheet> findById(Long id) {
        return timesheetRepository.findById(id);
    }

    /**
     * Поиск всех таймшитов.
     *
     * @return список всех таймшитов
     */
    public List<Timesheet> findAll() {
        return findAll(null, null);
    }

    /**
     * Поиск всех таймшитов с возможностью фильтрации по дате создания.
     *
     * @param createdAtBefore дата, до которой были созданы таймшиты
     * @param createdAtAfter  дата, после которой были созданы таймшиты
     * @return список отфильтрованных таймшитов
     */
    public List<Timesheet> findAll(LocalDate createdAtBefore, LocalDate createdAtAfter) {
        return timesheetRepository.findAll(createdAtBefore, createdAtAfter);
    }

    /**
     * Создание нового таймшита.
     *
     * @param timesheet создаваемый таймшит
     * @return созданный таймшит
     */
    public Timesheet create(Timesheet timesheet) {
        if (Objects.isNull(timesheet.getProjectId())) {
            throw new IllegalArgumentException("projectId must not be null");
        }

        if (projectRepository.findById(timesheet.getProjectId()).isEmpty()) {
            throw new NoSuchElementException("Project with id " + timesheet.getProjectId() + " does not exists");
        }

        timesheet.setCreatedAt(LocalDate.now());
        return timesheetRepository.create(timesheet);
    }

    /**
     * Удаление таймшита по идентификатору.
     *
     * @param id идентификатор таймшита
     */
    public void delete(Long id) {
        timesheetRepository.delete(id);
    }

    /**
     * Возвращает заметку по id проекта
     * @param projectId
     * @return
     */
    public List<Timesheet> getByProjectId(Long projectId) {
        return timesheetRepository.getByProjectId(projectId);
    }

}
