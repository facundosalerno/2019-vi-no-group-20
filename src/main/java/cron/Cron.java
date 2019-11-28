package cron;

import java.util.Timer;

public class Cron {

    public static void main(String[] args){

        Timer timer = new Timer();
        long oneDayInMiliseconds = 86400000; //1 dia

        VerificarEventosCercanos eventosCercanos = new VerificarEventosCercanos();
        timer.scheduleAtFixedRate(eventosCercanos, 0, oneDayInMiliseconds);

        VerificarAlertasMeteorologicas alertasMeteorologicas = new VerificarAlertasMeteorologicas();
        timer.scheduleAtFixedRate(alertasMeteorologicas, 0, oneDayInMiliseconds);

        RenovarFrecuenciasEventos eventosRecurrentes = new RenovarFrecuenciasEventos();
        timer.scheduleAtFixedRate(eventosRecurrentes, 0, oneDayInMiliseconds);
    }
}

