package ru.gb.timesheet.service.rest;

import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final TimesheetRepository timesheetRepository;

    /**
     * Сервис для управления проектами.
     */
    public ProjectService(ProjectRepository projectRepository, TimesheetRepository timesheetRepository) {
        this.projectRepository = projectRepository;
        this.timesheetRepository = timesheetRepository;
    }

    /**
     * Поиск проекта по идентификатору.
     *
     * @param id идентификатор проекта
     * @return найденный проект, если существует
     */
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    /**
     * Поиск всех проектов.
     *
     * @return список всех проектов
     */
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    /**
     * Создание нового проекта.
     *
     * @param project создаваемый проект
     * @return созданный проект
     */
    public Project create(Project project) {
        return projectRepository.create(project);
    }

    /**
     * Удаление проекта по идентификатору.
     *
     * @param id идентификатор проекта
     */
    public void delete(Long id) {
        projectRepository.delete(id);
    }

    /**
     * Получение всех таймшитов проекта по идентификатору проекта.
     *
     * @param id идентификатор проекта
     * @return список таймшитов проекта
     */
    public List<Timesheet> getTimesheets(Long id) {
        if (projectRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Project with id = " + id + " does not exists");
        }

        return timesheetRepository.findByProjectId(id);
    }
}
