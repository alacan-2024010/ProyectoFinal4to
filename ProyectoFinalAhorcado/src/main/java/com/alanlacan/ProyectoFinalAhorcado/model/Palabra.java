package com.alanlacan.ProyectoFinalAhorcado.model;

import jakarta.persistence.*;

@Entity
@Table(name="Palabras")
public class Palabra {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer codigoPalabra;

    @Column(name="palabra")
    private String palabra;

    @Column(name="pista_uno")
    private String pistaUno;

    @Column(name="pista_dos")
    private String pistaDos;

    @Column(name="pista_tres")
    private String pistaTres;

    public Integer getCodigoPalabra() {
        return codigoPalabra;
    }

    public void setCodigoPalabra(Integer codigoPalabra) {
        this.codigoPalabra = codigoPalabra;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getPistaUno() {
        return pistaUno;
    }

    public void setPistaUno(String pistaUno) {
        this.pistaUno = pistaUno;
    }

    public String getPistaDos() {
        return pistaDos;
    }

    public void setPistaDos(String pistaDos) {
        this.pistaDos = pistaDos;
    }

    public String getPistaTres() {
        return pistaTres;
    }

    public void setPistaTres(String pistaTres) {
        this.pistaTres = pistaTres;
    }
}
