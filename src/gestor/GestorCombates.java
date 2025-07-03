package gestor;

import modelo.combate.Combate;
import modelo.usuario.Jugador;

import java.util.ArrayList;
import java.util.List;

public class GestorCombates {
    private List<Combate> historial = new ArrayList<>();

    public void registrarCombate(Combate combate) {
        historial.add(combate);
    }

    // Obtener oro neto ganado (positivo) o perdido (negativo) por un jugador
    public int calcularOroNeto(Jugador jugador) {
        int oroNeto = 0;
        for (Combate c : historial) {
            if (c.getGanador().equals(jugador)) {
                oroNeto += c.getOroTransferido();
            } else if (c.getPerdedor().equals(jugador)) {
                oroNeto -= c.getOroTransferido();
            }
        }
        return oroNeto;
    }
}

