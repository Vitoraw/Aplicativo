package com.example.vitor.aplicativo;

import android.graphics.Movie;

/**
 * Created by Vitor on 20/04/2015.
 */
public class Cambio {
    private String Cod;
    private String moeda;
    private String valor;
    public Cambio(){

    }
    public Cambio(String moeda,String valor){
        this.moeda=moeda;
        this.valor=valor;

    }

    public String getValor() {
        return valor;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    @Override
    public String toString() {
        return
                "valor='" + valor + '\'' +
                ", moeda='" + moeda + '\''
                ;
    }
}
