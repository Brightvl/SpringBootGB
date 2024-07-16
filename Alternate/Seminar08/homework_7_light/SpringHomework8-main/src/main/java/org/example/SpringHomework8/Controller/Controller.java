package org.example.SpringHomework8.Controller;

import lombok.AllArgsConstructor;
import org.example.SpringHomework8.Service.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.example.SpringHomework8.Model.Book;

@org.springframework.stereotype.Controller
@AllArgsConstructor
public class Controller {
    //в контролер внедряем зависимость - сервис, а также добавляем
    //статические переменные выручки магазина и суммы покупки пользователя
    private final Service service;
    private static double revenue = 0.0;
    private static double purchase = 0.0;

    //по умолчанию отдаем страницу витрины магазина
    @GetMapping("/")
    public String getViewAsGuest(Model model) {
        model.addAttribute("books", service.getAllBooks());
        return "showroom";
    }

    //отдаем страницу авторизации
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //отдаем страницу пользователя
    @GetMapping("/user-profile")
    public String getViewAsCommonUser(Model model) {
        model.addAttribute("books", service.getAllBooks());
        model.addAttribute("purchase", purchase);
        return "user-profile";
    }

    //отдаем страницу администратора
    @GetMapping("/admin-profile")
    public String getViewAsAdmin(Model model) {
        model.addAttribute("books", service.getAllBooks());
        model.addAttribute("revenue", revenue);
        return "admin-profile";
    }

    //отдаем обновленную страницу администратора после добавления новой книги
    @PostMapping("/admin-profile")
    public String addBook(Book book, Model model) {
        service.createBook(book);
        model.addAttribute("books", service.getAllBooks());
        return "redirect:/admin-profile";
    }

    //отдаем обновленную страницу администратора после продажи книги
    @GetMapping("book-sell/{name}")
    public String sellBook(@PathVariable("name") String name) {
        revenue += Math.round(service.getBookByName(name).getPrice() * 100.0) / 100.0;
        service.sellBook(name);
        return "redirect:/admin-profile";
    }

    //отдаем обновленную страницу пользователя после покупки книги
    @GetMapping("book-purchase/{name}")
    public String buyBook(@PathVariable("name") String name) {
        purchase += Math.round(service.getBookByName(name).getPrice() * 100.0) / 100.0;
        revenue += Math.round(service.getBookByName(name).getPrice() * 100.0) / 100.0;
        service.sellBook(name);
        return "redirect:/user-profile";
    }
}
