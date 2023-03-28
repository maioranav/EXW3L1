package com.epicode.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Studente {
    private int id;
    private String name;
    private String lastname;
    private String gender;
    private LocalDate birthdate;
    private int avg;
    private int min_vote;
    private int max_vote;

    public Studente(String name, String lastname, String gender, LocalDate birthdate) {
        this.name = name;
        this.lastname = lastname;
        this.gender = gender;
        this.birthdate = birthdate;
    }

    public Studente(String name, String lastname, String gender, LocalDate birthdate, int avg, int min_vote, int max_vote) {
        this(name, lastname, gender, birthdate);
        this.avg = avg;
        this.min_vote = min_vote;
        this.max_vote = max_vote;
    }

    public Studente(int id, String name, String lastname, String gender, LocalDate birthdate, int avg, int min_vote, int max_vote) {
        this(name, lastname, gender, birthdate, avg, min_vote, max_vote);
        this.id = id;
    }


}
