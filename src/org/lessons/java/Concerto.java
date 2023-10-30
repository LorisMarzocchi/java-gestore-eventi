package org.lessons.java;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concerto extends Evento{
    private BigDecimal prezzo;
    private LocalTime ora;

    public Concerto(String titolo, LocalDate data, int postiTotali, LocalTime ora, BigDecimal prezzo) {
        super(titolo, data, postiTotali);
        this.ora = ora;
        this.prezzo = prezzo;
    }

    public String getFormattedPrezzo() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00€");
        return decimalFormat.format(prezzo);
    }
    public String getFormattedOra(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        return ora.format(format);
    }
    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return "Concerto: " + "in data " +
                getFormattedData() + " " + "ora:" + getFormattedOra() + " - " + getTitolo() + " - " + "prezzo " + getFormattedPrezzo();
    }
}
