package gestor;

import modelo.personajes.Personaje;
import modelo.personajes.PersonajeFactory;
import modelo.usuario.Jugador;
import modelo.equipo.Arma;
import modelo.equipo.Armadura;

import java.util.List;



public class GestorPersonajes {

    public void registrarPersonaje(Jugador jugador, String tipo, String nombrePersonaje, int salud, int poder, int oro,
                                   int extra1, int extra2) {
        Personaje personaje = PersonajeFactory.crearPersonaje(tipo, nombrePersonaje, salud, poder, oro, extra1, extra2);
        jugador.anadirPersonaje(personaje);
    }

    public boolean eliminarPersonaje(Jugador jugador, String nombrePersonaje) {
        return jugador.eliminarPersonaje(nombrePersonaje);
    }

    public List<Arma> obtenerArmasDisponibles(Personaje personaje) {
        return personaje.getArmasDisponibles();
    }

    // Activar arma para un personaje (devuelve true si pudo activar)
    public boolean activarArma(Personaje personaje, Arma arma) {
        return personaje.activarArma(arma);
    }

    // Desactivar arma activa
    public boolean desactivarArma(Personaje personaje, Arma arma) {
        return personaje.desactivarArma(arma);
    }

    // Listar armas activas
    public List<Arma> obtenerArmasActivas(Personaje personaje) {
        return personaje.getArmasActivas();
    }

    // Listar armaduras disponibles
    public List<Armadura> obtenerArmadurasDisponibles(Personaje personaje) {
        return personaje.getArmadurasDisponibles();
    }

    // Activar armadura (devuelve true si pudo activar)
    public boolean activarArmadura(Personaje personaje, Armadura armadura) {
        return personaje.activarArmadura(armadura);
    }

    // Obtener armadura activa
    public Armadura obtenerArmaduraActiva(Personaje personaje) {
        return personaje.getArmaduraActiva();
    }
}


