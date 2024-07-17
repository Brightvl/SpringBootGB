package ru.gb.timesheet.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Employee;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.rest.EmployeeService;
import ru.gb.timesheet.service.rest.TimesheetService;

import java.util.List;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final TimesheetService timesheetService;

    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable Long id) {
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.save(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getTimesheetsByEmployee(@PathVariable Long id) {
        return employeeService.findById(id)
                .map(employee -> ResponseEntity.ok(timesheetService.getAllTimesheetsByEmployeeId(employee.getId())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
