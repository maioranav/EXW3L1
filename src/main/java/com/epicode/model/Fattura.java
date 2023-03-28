package com.epicode.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Fattura {
    private int numfattura;
    private String tipologia;
    private double importo;
    private double iva;
    private int idcliente;
    private LocalDate datafattura;
    private int idfornitore;

    public Fattura(String type, double importo, double iva, LocalDate datafattura) {
        this.tipologia = type;
        this.importo = importo;
        this.iva = iva;
        this.datafattura = datafattura;
    }

    public Fattura(String type, double importo, double iva, LocalDate datafattura, int idcliente, int idfornitore) {
        this(type, importo, iva, datafattura);
        if (idcliente > 0) {
            this.idcliente = idcliente;
        }
        if (idfornitore > 0) {
            this.idfornitore = idfornitore;
        }
    }

    public Fattura(int id, String type, double importo, double iva, LocalDate datafattura, int idcliente, int idfornitore) {
        this(type, importo, iva, datafattura, idcliente, idfornitore);
        this.numfattura = id;
    }

}
