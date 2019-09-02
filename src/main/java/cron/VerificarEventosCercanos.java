package cron;

import clima.AccuWeather;
import domain.usuario.Usuario;
import exceptions.TodaviaNoEstaCercaElEvento;
import java.util.List;
import java.util.TimerTask;

public class VerificarEventosCercanos extends TimerTask {
    @Override
    public void run() {
        List<Usuario> usuarios = Cron.getRepoInstance().getListaDeUsuarios();
        usuarios.stream()
                .map(usuario -> usuario.getEventos())
                .forEach(listaEventosDeUsuarios -> listaEventosDeUsuarios.stream().forEach(evento -> {
                    try{
                        AccuWeather meteorologo = new AccuWeather();
                        evento.generarSugerencias(meteorologo);
                        evento.notificar();
                    }catch (TodaviaNoEstaCercaElEvento e){
                        /* salteo y no notifico */
                    }
                }));

    }
}
