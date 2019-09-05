package cron;

import exceptions.RepositorioDeUsuariosNoEstaInstanciado;
import java.util.Timer;

public class Cron {

    private static RepositorioUsuarios repo = null;

    public static void main(String[] args){
        repo = RepositorioUsuarios.getInstance();

        Timer timer = new Timer();
        long oneDayInMiliseconds = 86400000; //1 dia

        VerificarEventosCercanos eventosCercanos = new VerificarEventosCercanos();
        timer.scheduleAtFixedRate(eventosCercanos, 0, oneDayInMiliseconds);

        VerificarAlertasMeteorologicas alertasMeteorologicas = new VerificarAlertasMeteorologicas();
        timer.scheduleAtFixedRate(alertasMeteorologicas, 0, oneDayInMiliseconds);
    }
}

