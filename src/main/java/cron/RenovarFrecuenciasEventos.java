package cron;

import domain.usuario.Usuario;

import java.util.List;
import java.util.TimerTask;

public class RenovarFrecuenciasEventos extends TimerTask {

    @Override
    public void run() {
        List<Usuario> usuarios = RepositorioUsuarios.getInstance().getListaDeUsuarios();
        usuarios.stream()
                .map(usuario -> usuario.getEventos())
                .forEach(listaEventosDeUsuarios -> listaEventosDeUsuarios.stream()
                        .filter(evento -> evento.eshoy())
                        .forEach(evento -> evento.renovarFrecuencia()));
    }
}
