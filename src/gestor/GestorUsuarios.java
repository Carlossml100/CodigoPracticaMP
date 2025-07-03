package gestor;

import modelo.usuario.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class GestorUsuarios {
    private List<Usuario> usuarios;
    private GestorCombates gestorCombates;

    public GestorUsuarios(GestorCombates gestorCombates) {
        this.usuarios = new ArrayList<>();
        this.gestorCombates = gestorCombates;
    }

    public List<Jugador> obtenerRankingGlobal() {
        return usuarios.stream()
                .filter(u -> u instanceof Jugador)
                .map(u -> (Jugador) u)
                .sorted(Comparator.comparingInt(gestorCombates::calcularOroNeto).reversed())
                .collect(Collectors.toList());
    }

    public GestorUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    public void registrarJugador(String nombre, String nick, String password) {
        if (buscarUsuarioPorNick(nick) != null) {
            throw new IllegalArgumentException("El nick ya está en uso.");
        }
        Jugador jugador = new Jugador(nombre, nick, password);
        usuarios.add(jugador);
    }

    public void registrarOperador(String nombre, String nick, String password) {
        if (buscarUsuarioPorNick(nick) != null) {
            throw new IllegalArgumentException("El nick ya está en uso.");
        }
        Operador operador = new Operador(nombre, nick, password);
        usuarios.add(operador);
    }

    public Usuario login(String nick, String password) {
        for (Usuario u : usuarios) {
            if (u.getNick().equalsIgnoreCase(nick) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public Usuario buscarUsuarioPorNick(String nick) {
        for (Usuario u : usuarios) {
            if (u.getNick().equalsIgnoreCase(nick)) {
                return u;
            }
        }
        return null;
    }

    public boolean eliminarUsuario(String nick) {
        return usuarios.removeIf(u -> u.getNick().equalsIgnoreCase(nick));
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public boolean eliminarJugador(String nick) {
        Usuario u = buscarUsuarioPorNick(nick);
        if (u instanceof Jugador) {
            return usuarios.remove(u);
        }
        return false;
    }

    public Usuario autenticarUsuario(String nick, String password) {
        for (Usuario u : usuarios) {
            if (u.getNick().equalsIgnoreCase(nick) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }




}

