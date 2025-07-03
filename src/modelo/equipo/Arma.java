package modelo.equipo;

public class Arma implements Equipo {

    public enum Tipo {
        UNA_MANO,
        DOS_MANOS
    }

    private String nombre;
    private int modificadorAtaque; // 1-3
    private int modificadorDefensa; // 1-3 (puede ser 0 si no añade defensa)
    private Tipo tipo;

    public Arma(String nombre, int modificadorAtaque, int modificadorDefensa, Tipo tipo) {
        this.nombre = nombre;
        this.modificadorAtaque = Math.max(1, Math.min(3, modificadorAtaque));
        this.modificadorDefensa = Math.max(0, Math.min(3, modificadorDefensa));
        this.tipo = tipo;
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

    public Tipo getTipo() {
        return tipo;
    }

    public boolean esDosManos() {
        return tipo == Tipo.DOS_MANOS;
    }
}


