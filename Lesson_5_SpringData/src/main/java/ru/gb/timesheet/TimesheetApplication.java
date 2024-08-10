package ru.gb.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TimesheetApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(TimesheetApplication.class, args);
        DataInitializationService dataInitService = ctx.getBean(DataInitializationService.class);
        dataInitService.initData();
    }
}
