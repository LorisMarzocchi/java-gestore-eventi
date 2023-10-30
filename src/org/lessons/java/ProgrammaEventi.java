package org.lessons.java;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProgrammaEventi {
    private String titolo;
    private List<Evento> eventi;

    public List<Evento> getEventi() {
        return eventi;
    }

    public ProgrammaEventi(String titolo) {
        this.titolo = titolo;
        this.eventi = new ArrayList<>();  // Inizializza con una nuova ArrayList
    }

    public void aggiungiEvento(Evento evento) {
        eventi.add(evento);
    }

    public List<Evento> getEventiInData(LocalDate data) {
        List<Evento> eventiInData = new ArrayList<>();
        for (Evento e : eventi) {
            if (e.getData().isEqual(data)) {
                eventiInData.add(e);
            }
        }
        return eventiInData;
    }

    @Override
    public String toString() {
        return
                ", eventi: " + eventi;
    }

    public int numeroEventi() {
        return eventi.size();
    }

    public void svuotaProgramma() {
        eventi.clear();
    }
}
