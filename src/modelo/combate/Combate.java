package modelo.combate;

import modelo.usuario.Jugador;

import java.time.LocalDateTime;

public class Combate {
    private Jugador ganador;
    private Jugador perdedor;
    private int oroTransferido; // Oro que pasó del perdedor al ganador
    private LocalDateTime fecha;

    public Combate(Jugador ganador, Jugador perdedor, int oroTransferido, LocalDateTime fecha) {
        this.ganador = ganador;
        this.perdedor = perdedor;
        this.oroTransferido = oroTransferido;
        this.fecha = fecha;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public Jugador getPerdedor() {
        return perdedor;
    }

    public int getOroTransferido() {
        return oroTransferido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}
