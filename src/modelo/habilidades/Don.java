package modelo.habilidades;

public class Don implements HabilidadEspecial {
    private String nombre;
    private int valorAtaque; // 1-3
    private int valorDefensa; // 1-3
    private int rabiaMinima; // para poder usarse

    public Don(String nombre, int valorAtaque, int valorDefensa, int rabiaMinima) {
        this.nombre = nombre;
        this.valorAtaque = Math.max(1, Math.min(3, valorAtaque));
        this.valorDefensa = Math.max(1, Math.min(3, valorDefensa));
        this.rabiaMinima = Math.max(0, Math.min(3, rabiaMinima));
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

    public int getRabiaMinima() {
        return rabiaMinima;
    }
}


