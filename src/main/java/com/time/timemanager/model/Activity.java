package com.time.timemanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private Long userId;
    private String idNumber;
    private LocalDate date;
    private LocalTime timeStarted;
    private LocalTime timeEnded;


}
