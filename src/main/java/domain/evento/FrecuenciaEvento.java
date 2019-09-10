package domain.evento;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

/** https://stackoverflow.com/questions/35365571/get-the-next-localdatetime-for-a-given-day-of-week */

public enum FrecuenciaEvento {
    NO_SE_REPITE{
        @Override
        public void renovarEvento(Evento evento){
            return;
        }
    },
    DIARIO{
        @Override
        public void renovarEvento(Evento evento){
            LocalDateTime old = evento.getFecha();
            LocalDateTime siguienteDia = LocalDateTime.from(old).plusDays(1);
            evento.setFecha(siguienteDia);
        }
    },
    SEMANAL{
        @Override
        public void renovarEvento(Evento evento){
            LocalDateTime old = evento.getFecha();
            LocalDateTime siguienteSemana = LocalDateTime.from(old).plusWeeks(1);
            evento.setFecha(siguienteSemana);
        }
    },
    MENSUAL{
        @Override
        public void renovarEvento(Evento evento){
            LocalDateTime old = evento.getFecha();
            LocalDateTime siguienteMes= LocalDateTime.from(old).plusMonths(1);
            evento.setFecha(siguienteMes);
        }
    },
    ANUAL{
        @Override
        public void renovarEvento(Evento evento){
            LocalDateTime old = evento.getFecha();
            LocalDateTime siguienteAño = LocalDateTime.from(old).plusYears(1);
            evento.setFecha(siguienteAño);
        }
    };

    public abstract void renovarEvento(Evento evento);
}
