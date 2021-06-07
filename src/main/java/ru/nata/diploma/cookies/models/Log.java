package ru.nata.diploma.cookies.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "LOG")
public class Log {
    @Id
    @GeneratedValue
    private Long id;
    private String processId;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private String status;
    @Size(max = 1500)
    private String comment;
    private String operation;
}
