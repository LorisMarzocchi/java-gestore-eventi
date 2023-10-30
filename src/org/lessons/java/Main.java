package org.lessons.java;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProgrammaEventi programmaEventi = new ProgrammaEventi("Programma Eventi");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            Evento evento = null;
            boolean continua = true;
            while (continua) {
                System.out.println("Cosa vuoi fare?");
                System.out.println("1: Creare un nuovo concerto");
                System.out.println("2: Prenotare posti per un concerto");
                System.out.println("3: Disdire posti per un concerto");
                System.out.println("4: Visualizzare tutti i concerti");
                System.out.println("5: Visualizzare tutti i concerti in una specifica data");
                System.out.println("6: Uscire");

                int scelta = scanner.nextInt();
                scanner.nextLine();

                boolean datiModificati = false;

                switch (scelta) {
                    case 1:
                        String titolo = null;
                        while (titolo == null || titolo.trim().isEmpty()) {
                            System.out.println("Inserisci il titolo del concerto:");
                            titolo = scanner.nextLine();
                            if (titolo.trim().isEmpty()) {
                                System.out.println("Il titolo non può essere vuoto. Riprova.");
                            }
                        }


                        LocalDate data = null;
                        while (data == null) {
                            try {
                                System.out.println("Inserisci la data del concerto (gg/mm/yyyy):");
                                String dataString = scanner.nextLine();
                                data = LocalDate.parse(dataString, format);
                                if (data.isBefore(LocalDate.now())) {
                                    System.out.println("La data non può essere passata. Inserisci una data futura.");
                                    data = null;
                                }
                            } catch (DateTimeParseException e) {
                                System.out.println("Formato data non valido. Inserisci la data nel formato gg/mm/yyyy.");
                            }
                        }

                        LocalTime orario = null;
                        while (orario == null) {
                            try {
                                System.out.println("Inserisci l'orario del concerto (HH:mm):");
                                String orarioString = scanner.nextLine();
                                DateTimeFormatter orarioFormat = DateTimeFormatter.ofPattern("HH:mm");
                                orario = LocalTime.parse(orarioString, orarioFormat);
                            } catch (DateTimeParseException e) {
                                System.out.println("Formato orario non valido. Inserisci l'orario nel formato HH:mm.");
                            }
                        }

                        int postiTotali = 0;
                        while (postiTotali <= 0) {
                            try {
                                System.out.println("Inserisci il numero di posti totali:");
                                postiTotali = scanner.nextInt();
                                if (postiTotali <= 0) {
                                    System.out.println("Il numero di posti totali deve essere positivo. Riprova.");
                                }
                            } catch (InputMismatchException e) {
                                    System.out.println("Devi inserire un numero. Riprova.");
                                    scanner.next();
                            }
                        }

                        BigDecimal prezzo = BigDecimal.valueOf(0);
                        while (prezzo.compareTo(BigDecimal.ZERO) <= 0) {
                            try {
                                System.out.println("Inserisci il prezzo del concerto:");
                                prezzo = scanner.nextBigDecimal();
                                if (prezzo.compareTo(BigDecimal.ZERO) <= 0) {
                                    System.out.println("Il prezzo deve essere positivo. Riprova.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Devi inserire un numero valido. Riprova.");
                                scanner.next();  // Pulisce l'input errato
                            }
                        }



                        evento = new Concerto(titolo, data, postiTotali, orario, prezzo);
                        programmaEventi.aggiungiEvento(evento);
                        System.out.println("Concerto creato con successo!");
                        break;

                    case 2:
                        if (!programmaEventi.getEventi().isEmpty()) {
                            System.out.println("Quanti posti vuoi prenotare?");
                            int postiDaPrenotare = scanner.nextInt();

                            evento.prenota(postiDaPrenotare);
                            System.out.println("Posti prenotati con successo!");
                            datiModificati = true;
                        } else {
                            System.out.println("Nessun evento disponibile per la prenotazione.");
                        }
                        break;

                    case 3:
                        if (!programmaEventi.getEventi().isEmpty()) {
                            System.out.println("Quanti posti vuoi disdire?");
                            int postiDaDisdire = scanner.nextInt();

                            evento.disdici(postiDaDisdire);
                            System.out.println("Posti disdetti con successo!");
                            datiModificati = true;
                        } else {
                            System.out.println("Nessun evento disponibile per disdire i posti.");
                        }
                        break;

                    case 4:
                        System.out.println("Tutti i concerti:");
                        List<Evento> tuttiEventi = programmaEventi.getEventi();
                        if (tuttiEventi.isEmpty()){
                            System.out.println("non ci sono eventi");
                        }else{
                            for (Evento listaEventi : tuttiEventi) {
                                System.out.println(listaEventi);
                            }
                        }
                        break;

                    case 5:
                        System.out.println("Inserisci la data dei concerti da visualizzare (gg/mm/yyyy):");
//                        scanner.nextLine();
                        String dataEventoString = scanner.nextLine();

                        LocalDate dataEvento = LocalDate.parse(dataEventoString, format);
                        List<Evento> eventiInData = programmaEventi.getEventiInData(dataEvento);
                        if (eventiInData.isEmpty()) {
                            System.out.println("Non ci sono eventi in data " + dataEventoString);
                        } else {
                            System.out.println("Concerti in data " + dataEventoString + ":");
                            for (Evento e : eventiInData) {
                                System.out.println(e);
                            }
                        }
                        break;

                    case 6:
                        continua = false;
                        System.out.println("Programma terminato.");
                        break;

                    default:
                        System.out.println("Scelta non valida. Riprova.");
                        break;
                }

                if (evento != null && datiModificati) {
                    System.out.println("dati evento dopo operazioni: Evento " + evento.getTitolo());
                    System.out.println("Posti prenotati: " + evento.getPostiPrenotati());
                    System.out.println("Posti disponibili: " + evento.postiDisponibili());
                }
            }

        }
        catch (Exception e) {
            System.out.println("Si è verificato un errore: " + e.getMessage());
        }




        scanner.close();
    }
}
