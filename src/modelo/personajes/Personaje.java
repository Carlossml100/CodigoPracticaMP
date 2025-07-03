package modelo.personajes;

import modelo.equipo.Arma;
import modelo.equipo.Armadura;
import modelo.esbirros.*;
import modelo.habilidades.HabilidadEspecial;
import modelo.modificadores.Modificador;

import java.util.ArrayList;
import java.util.List;

public abstract class Personaje {
    protected String nombre;
    protected int salud; // 0-5
    protected int poder; // 1-5
    protected int oro;

    protected HabilidadEspecial habilidadEspecial;

    protected List<Arma> armasDisponibles = new ArrayList<>();
    protected List<Arma> armasActivas = new ArrayList<>(); // máximo 2 armas activas o 1 arma de dos manos
    protected List<Armadura> armadurasDisponibles = new ArrayList<>();
    protected Armadura armaduraActiva;

    protected List<Esbirro> esbirros = new ArrayList<>();
    protected List<Modificador> fortalezas = new ArrayList<>();
    protected List<Modificador> debilidades = new ArrayList<>();


    public Personaje(String nombre, int salud, int poder, int oro) {
        this.nombre = nombre;
        this.salud = Math.min(5, Math.max(0, salud));
        this.poder = Math.min(5, Math.max(1, poder));
        this.oro = Math.max(0, oro);
    }

    public String getNombre() {
        return nombre;
    }



    // Añadir arma a inventario
    public void anadirArma(Arma arma) {
        armasDisponibles.add(arma);
    }

    // Activar un arma con reglas:
    // - Si el arma es de dos manos, solo puede haber esa arma activa
    // - Si es de una mano, máximo 2 armas activas de una mano
    public boolean activarArma(Arma arma) {
        if (!armasDisponibles.contains(arma)) return false;

        if (arma.esDosManos()) {
            if (!armasActivas.isEmpty()) {
                // Desactivar todas las armas activas antes de activar esta
                armasActivas.clear();
            }
            armasActivas.add(arma);
            return true;
        } else {
            // Es arma de una mano
            // Si ya hay un arma de dos manos activa, no puede activar esta
            for (Arma a : armasActivas) {
                if (a.esDosManos()) return false;
            }
            if (armasActivas.size() < 2) {
                armasActivas.add(arma);
                return true;
            } else {
                return false; // no puede activar más de dos armas de una mano
            }
        }
    }

    // Desactivar un arma activa
    public boolean desactivarArma(Arma arma) {
        return armasActivas.remove(arma);
    }

    public List<Arma> getArmasActivas() {
        return armasActivas;
    }

    public List<Arma> getArmasDisponibles() {
        return armasDisponibles;
    }

    // Añadir armadura a inventario
    public void anadirArmadura(Armadura armadura) {
        armadurasDisponibles.add(armadura);
    }

    // Activar armadura (solo una activa)
    public boolean activarArmadura(Armadura armadura) {
        if (armadurasDisponibles.contains(armadura)) {
            armaduraActiva = armadura;
            return true;
        }
        return false;
    }

    public Armadura getArmaduraActiva() {
        return armaduraActiva;
    }

    public List<Armadura> getArmadurasDisponibles() {
        return armadurasDisponibles;
    }

    // Otros métodos (salud, poder, oro, habilidades, etc.) mantén los que ya tenías

    public abstract String getRaza();

    // Debilidades
    public void anadirDebilidad(Modificador debilidad) {
        debilidades.add(debilidad);
    }

    public List<Modificador> getDebilidades() {
        return debilidades;
    }

    public boolean eliminarDebilidad(String nombre) {
        return debilidades.removeIf(d -> d.getNombre().equalsIgnoreCase(nombre));
    }

    // Fortalezas
    public void anadirFortaleza(Modificador fortaleza) {
        fortalezas.add(fortaleza);
    }

    public List<Modificador> getFortalezas() {
        return fortalezas;
    }

    public boolean eliminarFortaleza(String nombre) {
        return fortalezas.removeIf(f -> f.getNombre().equalsIgnoreCase(nombre));
    }

    public List<Esbirro> getEsbirros() {
        return esbirros;
    }

    public boolean anadirEsbirro(Esbirro esbirro) {
        // Los vampiros no pueden tener esbirros humanos
        if (this.getRaza().equalsIgnoreCase("Vampiro") && esbirro instanceof Humano) {
            System.out.println("Un vampiro no puede tener esbirros humanos.");
            return false;
        }
        esbirros.add(esbirro);
        return true;
    }

    public boolean eliminarEsbirro(String nombre) {
        return esbirros.removeIf(e -> e.getNombre().equalsIgnoreCase(nombre));
    }




}





