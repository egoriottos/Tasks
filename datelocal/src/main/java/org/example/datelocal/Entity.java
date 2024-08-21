package org.example.datelocal;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Entity {
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy:MM:dd",
            locale = "ru_RU"
    )
    private LocalDateTime dateTime;
}
