package modelo.equipo;

public class Armadura implements Equipo {
    private String nombre;
    private int modificadorAtaque; // 0-3, puede tener modificador ataque
    private int modificadorDefensa; // 1-3

    public Armadura(String nombre, int modificadorAtaque, int modificadorDefensa) {
        this.nombre = nombre;
        this.modificadorAtaque = Math.max(0, Math.min(3, modificadorAtaque));
        this.modificadorDefensa = Math.max(1, Math.min(3, modificadorDefensa));
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public int getModificadorAtaque() {
        return modificadorAtaque;
    }

    @Override
    public int getModificadorDefensa() {
        return modificadorDefensa;
    }
}


