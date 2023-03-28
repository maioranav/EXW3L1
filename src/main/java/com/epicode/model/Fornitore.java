package com.epicode.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Fornitore {
    private int idfornitore;
    private String denominazione;
    private String regioneresidenza;

    public Fornitore(String nome, String regione) {
        this.denominazione = nome;
        this.regioneresidenza = regione;
    }

    public Fornitore(int id, String nome, String regione){
        this.idfornitore = id;
        this.denominazione = nome;
        this.regioneresidenza = regione;
    }

}
