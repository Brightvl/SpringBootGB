package ru.gb.timesheet.controllers.page;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для приветственных сообщений.
 */
@RestController
public class HelloController {
    /**
     * Приветственная страница с параметром.
     *
     * @param username имя пользователя
     * @return приветственное сообщение
     */
    // GET http://localhost:8080/hello?username=Igor
    @GetMapping("/hello")
    public String helloPage(@RequestParam String username) {
        return "<h1>Hello, " + username + "!</h1>";
    }

    /**
     * Приветственная страница с путем.
     *
     * @param username имя пользователя
     * @return приветственное сообщение
     */
    // GET http://localhost:8080/hello/igor
    // GET http://localhost:8080/hello/alex
    @GetMapping("/hello/{username}")
    public String helloPagePathVariable(@PathVariable String username) {
        return "<h1>Hello, " + username + "!</h1>";
    }

}
