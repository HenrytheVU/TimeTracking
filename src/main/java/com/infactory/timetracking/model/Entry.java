package com.infactory.timetracking.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
public class Entry {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    public Duration getDuration() {
        return Duration.between(checkIn, Objects.requireNonNullElseGet(checkOut, LocalDateTime::now));
    }

}
