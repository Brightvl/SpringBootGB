package ru.gb.timesheet.service.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.controllers.page.DTO.ProjectPageDto;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.service.rest.ProjectService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectPageService {

    private final ProjectService projectService;

    /**
     * Получение всех проектов и преобразование в ДТО
     *
     * @return список всех проектов
     */
    public List<ProjectPageDto> findAll() {
        return projectService.findAll().stream()
                .map(this::convert).toList();

    }

    /**
     * Поиск страницы проекта по идентификатору.
     *
     * @param id идентификатор проекта
     * @return найденная страница проекта, если существует
     */
    public Optional<ProjectPageDto> findById(Long id) {
        return projectService.findById(id)
                .map(this::convert);
    }


    /**
     * Преобразование проекта в DTO для отображения на странице.
     *
     * @param project проект
     * @return DTO страницы таймшита
     */
    private ProjectPageDto convert(Project project) {

        ProjectPageDto projectPageDto = new ProjectPageDto();
        projectPageDto.setId(project.getId());
        projectPageDto.setName(project.getName());

        return projectPageDto;
    }
}
