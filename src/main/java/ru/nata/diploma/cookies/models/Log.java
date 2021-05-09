package ru.nata.diploma.cookies.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "LOG")
public class Log {
    @Id
    private String id;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private String status;
    private String comment;
    private String operation;

}
