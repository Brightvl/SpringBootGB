package ru.gb.timesheet.service;

import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
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
    private final ProjectRepository projectRepository;

    public TimesheetService(TimesheetRepository repository,ProjectRepository projectRepository) {
        this.repository = repository;
        this.projectRepository = projectRepository;
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
        Optional<Project> project = projectRepository.getById(timesheet.getProjectId());
        // Проверка существует ли проект
        if (project.isPresent()) {
            // Установка текущей даты на стороне сервера
            timesheet.setCreatedAt(LocalDate.now());
            return repository.create(timesheet);
        } else {
            throw new IllegalArgumentException("Project with id " + timesheet.getProjectId() + " does not exist");
        }
    }

    /**
     * Удалить табель по идентификатору.
     *
     * @param id идентификатор табеля.
     */
    public void delete(Long id) {
        repository.delete(id);
    }


    public List<Timesheet> getByProjectId(Long projectId) {
        return repository.getByProjectId(projectId);
    }

}
