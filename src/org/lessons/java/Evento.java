package org.lessons.java;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Evento {

    private String titolo;
    private LocalDate data;
    private int postiTotali;
    private int postiPrenotati = 0;

    public Evento(String titolo, LocalDate data, int postiTotali) throws IllegalArgumentException{

        if (titolo.isEmpty()){
            throw new IllegalArgumentException("il titolo non puo essere una stringa vuota ");
        }
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("la data non puo essere gia passata ");
        }
        if (postiTotali < 0){
            throw new IllegalArgumentException("il numero di posti totali deve essere positivo");
        }

        this.titolo = titolo;
        this.data = data;
        this.postiTotali = postiTotali;
    }

    @Override
    public String toString() {
        return "Evento -> " +
                "data: " + getFormattedData() + " " +
                "titolo: " + titolo;
    }

    public int postiDisponibili() {
        return postiTotali - postiPrenotati;
    }
    public void prenota(int numeroPosti) {
//        if (data.isBefore(LocalDate.now())) {
//            throw new IllegalStateException("L'evento è già passato");
//        }
//        if (postiPrenotati <= 0) {
//            throw new IllegalArgumentException("Devi prenotare almeno un posto");
//        }
        if (postiPrenotati + numeroPosti > postiTotali) {
            throw new IllegalStateException("Non ci sono abbastanza posti disponibili");
        }
        postiPrenotati += numeroPosti;
    }

    public void disdici(int numeroPosti) {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalStateException("L'evento è già passato");
        }
        if (postiPrenotati == 0) {
            throw new IllegalArgumentException("Devi disdire almeno un posto");
        }
        if (postiPrenotati < numeroPosti) {
            throw new IllegalStateException("Non ci sono abbastanza posti prenotati da disdire");
        }
        postiPrenotati -= numeroPosti;
    }
    public int getPostiTotali() {
        return postiTotali;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getFormattedData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }
    public void setData(LocalDate data) {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data dell'evento non può essere passata");
        }
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }


}
