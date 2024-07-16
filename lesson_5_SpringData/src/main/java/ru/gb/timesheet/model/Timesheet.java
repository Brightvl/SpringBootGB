package ru.gb.timesheet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

/**
 * Описание структуры json-ответа на REST-запросы.
 * Т.е. запросы, ответ на которые - JSON.
 */
@Data
@Entity
@Table(name = "timesheet")
public class Timesheet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;
  private Long projectId;
  private Integer minutes;
  private LocalDate createdAt;

}
