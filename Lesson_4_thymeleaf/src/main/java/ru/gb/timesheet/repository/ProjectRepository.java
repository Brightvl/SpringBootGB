package ru.gb.timesheet.repository;

import org.springframework.stereotype.Repository;
import ru.gb.timesheet.model.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Репозиторий для управления проектами.
 */
@Repository
public class ProjectRepository {

    private static Long sequence = 1L;
    private final List<Project> projects = new ArrayList<>();

    /**
     * Поиск проекта по идентификатору.
     *
     * @param id идентификатор проекта
     * @return найденный проект, если существует
     */
    public Optional<Project> findById(Long id) {
        return projects.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst();
    }

    /**
     * Поиск всех проектов.
     *
     * @return список всех проектов
     */
    public List<Project> findAll() {
        return List.copyOf(projects);
    }

    /**
     * Создание нового проекта.
     *
     * @param project создаваемый проект
     * @return созданный проект
     */
    public Project create(Project project) {
        project.setId(sequence++);
        projects.add(project);
        return project;
    }

    /**
     * Удаление проекта по идентификатору.
     *
     * @param id идентификатор проекта
     */
    public void delete(Long id) {
        projects.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .ifPresent(projects::remove);
    }

}
