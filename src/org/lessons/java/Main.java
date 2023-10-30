package org.lessons.java;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

                switch (scelta) {
                    case 1:
                        System.out.println("Inserisci il titolo del concerto:");
                        String titolo = scanner.nextLine();

                        System.out.println("Inserisci la data del concerto (gg/mm/yyyy):");
                        String dataString = scanner.nextLine();
                        LocalDate data = LocalDate.parse(dataString, format);

                        System.out.println("Inserisci l'orario del concerto (HH:mm):");
                        String orarioString = scanner.nextLine();
                        DateTimeFormatter orarioFormat = DateTimeFormatter.ofPattern("HH:mm");
                        LocalTime orario = LocalTime.parse(orarioString, orarioFormat);

                        System.out.println("Inserisci il numero di posti totali:");
                        int postiTotali = scanner.nextInt();

                        System.out.println("Inserisci il prezzo del concerto:");
                        BigDecimal prezzo = scanner.nextBigDecimal();

                        evento = new Concerto(titolo, data, postiTotali, orario, prezzo);
                        programmaEventi.aggiungiEvento(evento);
                        System.out.println("Concerto creato con successo!");
                        break;

                    case 2:
                        if (!programmaEventi.getEventi().isEmpty()) {
                            System.out.println("Quanti posti vuoi prenotare?");
                            int postiDaPrenotare = scanner.nextInt();
                            // Aggiungere logica per selezionare un evento specifico se necessario
                            evento.prenota(postiDaPrenotare);
                            System.out.println("Posti prenotati con successo!");
                        } else {
                            System.out.println("Nessun evento disponibile per la prenotazione.");
                        }
                        break;

                    case 3:
                        if (!programmaEventi.getEventi().isEmpty()) {
                            System.out.println("Quanti posti vuoi disdire?");
                            int postiDaDisdire = scanner.nextInt();
                            // Aggiungere logica per selezionare un evento specifico se necessario
                            evento.disdici(postiDaDisdire);
                            System.out.println("Posti disdetti con successo!");
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
                        String dataEventoString = scanner.nextLine();
                        LocalDate dataEvento = LocalDate.parse(dataEventoString, format);
                        System.out.println("Concerti in data " + dataEventoString + ":");
                        programmaEventi.getEventiInData(dataEvento);
                        break;

                    case 6:
                        continua = false;
                        System.out.println("Programma terminato.");
                        break;

                    default:
                        System.out.println("Scelta non valida. Riprova.");
                        break;
                }

                if (evento != null) {
                    System.out.println("dati evento dopo operazioni:");
                    System.out.println("Posti prenotati: " + evento.getPostiPrenotati());
                    System.out.println("Posti disponibili: " + evento.postiDisponibili());
                }
            }
        }catch (InputMismatchException e) {
            System.out.println("Devi inserire un numero. Riprova.");
            scanner.next();
        }
        catch (Exception e) {
            System.out.println("Si è verificato un errore: " + e.getMessage());
        }




        scanner.close();
    }
}
