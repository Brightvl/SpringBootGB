package artemtsmyg.ru.DZ_sem6.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Note - класс, определяющий характеристики сущности, используемой для управления заметками в ежедневнике (БД).
 */
@Data
@Entity
@Table(name = "notes")
public class Note {
    public enum Status {TASK, REMINDER, INFORMATION}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column//(nullable = false)
    private Status status;
    @Column//(nullable = false)
    private String title;
    @Column//(nullable = false)
    private String description;
    @Column
    private LocalDateTime local_date_time = LocalDateTime.now();
}