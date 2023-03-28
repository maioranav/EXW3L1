package com.epicode.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Prodotto {
    private int id;
    private String descrizione;
    private boolean inprod;
    private boolean incomm;
    private LocalDate dataattiv;
    private LocalDate datadisattiv;

    public Prodotto(String desc, boolean prod, boolean comm, LocalDate dataattiv) {
        this.descrizione = desc;
        this.inprod = prod;
        this.incomm = comm;
        this.dataattiv = dataattiv;
    }

    public Prodotto(String desc, boolean prod, boolean comm, LocalDate dataattiv, LocalDate datadisattiv) {
        this(desc, prod, comm, dataattiv);
        this.datadisattiv = datadisattiv;
    }

    public Prodotto(int id, String desc, boolean prod, boolean comm, LocalDate dataattiv) {
        this(desc, prod, comm, dataattiv);
        this.id = id;
    }

    public Prodotto(int id, String desc, boolean prod, boolean comm, LocalDate dataattiv, LocalDate datadisattiv) {
        this(desc, prod, comm, dataattiv, datadisattiv);
        this.id = id;
    }


}
