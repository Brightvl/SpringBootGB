package ru.gb.timesheet;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.timesheet.model.Employee;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.EmployeeRepository;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DataInitializationService {

    private final ProjectRepository projectRepo;
    private final TimesheetRepository timesheetRepo;
    private final EmployeeRepository employeeRepo;

    public DataInitializationService(ProjectRepository projectRepo, TimesheetRepository timesheetRepo, EmployeeRepository employeeRepo) {
        this.projectRepo = projectRepo;
        this.timesheetRepo = timesheetRepo;
        this.employeeRepo = employeeRepo;
    }

    @Transactional
    public void initData() {
        for (int i = 1; i <= 5; i++) {
            Project project = new Project();
            project.setName("Project #" + i);
            projectRepo.save(project);
        }

        LocalDate createdAt = LocalDate.now();
        for (int i = 1; i <= 10; i++) {
            createdAt = createdAt.plusDays(1);

            Timesheet timesheet = new Timesheet();
            timesheet.setProjectId(ThreadLocalRandom.current().nextLong(1, 6));
            timesheet.setCreatedAt(createdAt);
            timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));

            timesheetRepo.save(timesheet);
        }

        for (int i = 1; i <= 10; i++) {
            Employee employee = new Employee();
            employee.setFirstName("Employee #" + i);
            employee.setLastName("Lastname #" + i);

            if (i % 2 == 0) {
                employee.getProjects().add(projectRepo.findById(1L).orElse(null));
            } else {
                employee.getProjects().add(projectRepo.findById(2L).orElse(null));
            }

            employeeRepo.save(employee);
        }
    }
}
