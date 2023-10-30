package org.lessons.java;

import java.time.LocalDate;

public class Evento {

    private String nome;
    private LocalDate data;
    private int postiTotali = 0;
    private int postiPrenotati;

    public int getPostiTotali() {
        return postiTotali;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
