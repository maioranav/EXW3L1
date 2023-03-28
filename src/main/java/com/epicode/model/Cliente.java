package com.epicode.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Getter @Setter @ToString
public class Cliente {
    private int idcliente;
    private String nome;
    private String cognome;
    private LocalDate datadinascita;
    private String regioneresidenza;

    public Cliente(String nome, String cognome, LocalDate datadinascita, String regioneresidenza) {
        this.nome = nome;
        this.cognome = cognome;
        this.datadinascita = datadinascita;
        this.regioneresidenza = regioneresidenza;
    }

    public Cliente(int idcliente, String nome, String cognome, LocalDate datadinascita, String regioneresidenza) {
        this.idcliente = idcliente;
        this.nome = nome;
        this.cognome = cognome;
        this.datadinascita = datadinascita;
        this.regioneresidenza = regioneresidenza;
    }
}
