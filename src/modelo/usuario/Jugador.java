package modelo.usuario;

import modelo.personajes.Personaje;

import java.util.ArrayList;
import java.util.List;

public class Jugador extends Usuario {
    private static int contadorRegistros = 0;

    private String numeroRegistro; // Formato LNNLL, generado automáticamente
    private List<Personaje> personajes;

    public Jugador(String nombre, String nick, String password) {
        super(nombre, nick, password);
        this.numeroRegistro = generarNumeroRegistro();
        this.personajes = new ArrayList<>();
    }

    private String generarNumeroRegistro() {
        contadorRegistros++;
        // Genera el formato LNNLL, ejemplo: A01BC
        // Aquí simplificamos: L = letra fija, N = números con ceros a la izquierda
        char primeraLetra = (char) ('A' + (contadorRegistros % 26));
        char segundaLetra = (char) ('A' + ((contadorRegistros / 26) % 26));
        int numero = contadorRegistros % 100;
        return "" + primeraLetra + String.format("%02d", numero) + segundaLetra + (char) ('A' + (contadorRegistros % 26));
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void anadirPersonaje(Personaje personaje) {
        personajes.add(personaje);
    }

    public boolean eliminarPersonaje(String nombrePersonaje) {
        return personajes.removeIf(p -> p.getNombre().equalsIgnoreCase(nombrePersonaje));
    }
}

