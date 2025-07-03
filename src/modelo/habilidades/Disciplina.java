package modelo.habilidades;

public class Disciplina implements HabilidadEspecial {
    private String nombre;
    private int valorAtaque; // 1-3
    private int valorDefensa; // 1-3
    private int costeSangre; // 1-3

    public Disciplina(String nombre, int valorAtaque, int valorDefensa, int costeSangre) {
        this.nombre = nombre;
        this.valorAtaque = Math.max(1, Math.min(3, valorAtaque));
        this.valorDefensa = Math.max(1, Math.min(3, valorDefensa));
        this.costeSangre = Math.max(1, Math.min(3, costeSangre));
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public int getValorAtaque() {
        return valorAtaque;
    }

    @Override
    public int getValorDefensa() {
        return valorDefensa;
    }

    public int getCosteSangre() {
        return costeSangre;
    }
}

