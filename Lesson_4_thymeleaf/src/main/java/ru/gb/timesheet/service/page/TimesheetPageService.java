package ru.gb.timesheet.service.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.controllers.page.DTO.TimesheetPageDto;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.rest.ProjectService;
import ru.gb.timesheet.service.rest.TimesheetService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для управления страницами таймшитов.
 */
@Service
@RequiredArgsConstructor
public class TimesheetPageService {

    private final TimesheetService timesheetService;
    private final ProjectService projectService;

    /**
     * Получение всех страниц таймшитов и преобразование в ДТО
     *
     * @return список всех страниц таймшитов
     */
    public List<TimesheetPageDto> findAll() {
        return timesheetService.findAll().stream()
                .map(this::convert)
                .toList();
    }

    /**
     * Поиск страницы таймшита по идентификатору.
     *
     * @param id идентификатор таймшита
     * @return найденная страница таймшита, если существует
     */
    public Optional<TimesheetPageDto> findById(Long id) {
        return timesheetService.findById(id) // Optional<Timesheet>
                .map(this::convert);
    }

    /**
     * Возвращает заметку по id проекта
     * @param projectId
     * @return
     */
    public List<TimesheetPageDto> getByProjectId(Long projectId) {
        return timesheetService.getByProjectId(projectId)
                .stream()
                .map(this::convert)
                .toList();
    }

    /**
     * Преобразование таймшита в DTO для отображения на странице.
     *
     * @param timesheet таймшит
     * @return DTO страницы таймшита
     */
    private TimesheetPageDto convert(Timesheet timesheet) {
        Project project = projectService.findById(timesheet.getProjectId())
                .orElseThrow();

        TimesheetPageDto timesheetPageParameters = new TimesheetPageDto();
        timesheetPageParameters.setProjectName(project.getName());
        timesheetPageParameters.setId(String.valueOf(timesheet.getId()));
        // 150 -> 2h30m
        timesheetPageParameters.setMinutes(String.valueOf(timesheet.getMinutes()));
        timesheetPageParameters.setCreatedAt(timesheet.getCreatedAt().format(DateTimeFormatter.ISO_DATE));

        return timesheetPageParameters;
    }

}
