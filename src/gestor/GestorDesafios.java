package gestor;

import modelo.usuario.Jugador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestorDesafios {

    private List<Desafio> desafios = new ArrayList<>();

    // Lanzar un desafío
    public void enviarDesafio(Jugador emisor, Jugador receptor) {
        if (emisor.equals(receptor)) {
            throw new IllegalArgumentException("No puedes desafiarte a ti mismo.");
        }
        if (estaBloqueado(receptor)) {
            throw new IllegalStateException("El jugador receptor está bloqueado para recibir desafíos.");
        }
        desafios.add(new Desafio(emisor, receptor, LocalDateTime.now()));
    }

    // Obtener desafíos recibidos no resueltos
    public List<Desafio> getDesafiosRecibidos(Jugador jugador) {
        List<Desafio> recibidos = new ArrayList<>();
        for (Desafio d : desafios) {
            if (d.getReceptor().equals(jugador) && !d.isResuelto()) {
                recibidos.add(d);
            }
        }
        return recibidos;
    }

    // Aceptar desafío (marcar resuelto y procesar resultado)
    public void aceptarDesafio(Desafio desafio) {
        desafio.setResuelto(true);
        // Aquí se puede añadir la lógica de combate, actualización de oro, etc.
    }

    // Rechazar desafío (eliminar de la lista)
    public void rechazarDesafio(Desafio desafio) {
        desafios.remove(desafio);
    }

    // Ejemplo de bloqueo simple (a implementar según reglas)
    private boolean estaBloqueado(Jugador jugador) {
        // Por ahora siempre false, implementar regla según últimas 24h y resultados
        return false;
    }

    public static class Desafio {
        private Jugador emisor;
        private Jugador receptor;
        private LocalDateTime fechaEnvio;
        private boolean resuelto;

        public Desafio(Jugador emisor, Jugador receptor, LocalDateTime fechaEnvio) {
            this.emisor = emisor;
            this.receptor = receptor;
            this.fechaEnvio = fechaEnvio;
            this.resuelto = false;
        }

        public Jugador getEmisor() {
            return emisor;
        }

        public Jugador getReceptor() {
            return receptor;
        }

        public LocalDateTime getFechaEnvio() {
            return fechaEnvio;
        }

        public boolean isResuelto() {
            return resuelto;
        }

        public void setResuelto(boolean resuelto) {
            this.resuelto = resuelto;
        }

        @Override
        public String toString() {
            return "Desafío de " + emisor.getNick() + " a " + receptor.getNick() + " enviado el " + fechaEnvio;
        }
    }
}

