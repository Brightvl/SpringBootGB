package ru.gb.timesheet.controllers.page.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

/**
 * Контроллер для обработки исключений на страницах.
 */
@Controller
@ControllerAdvice(basePackageClasses = PageExceptionController.class)
//@ControllerAdvice(basePackages = "ru.gb.timesheet.page")
public class PageExceptionController {
    /**
     * Отображение страницы ошибки.
     *
     * @return путь к HTML-странице ошибки
     */
    @GetMapping("/home/oops")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String oopsPage() {
        return "oops.html";
    }

    /**
     * Обработчик исключения NoSuchElementException.
     *
     * @param e исключение
     * @return путь к HTML-странице ошибки "Не найдено"
     */
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchElementException(NoSuchElementException e) {
        return "not-found.html";
    }

    /**
     * Обработчик всех прочих исключений.
     *
     * @param e исключение
     * @return перенаправление на страницу ошибки
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return "redirect:/home/oops";
    }

}
