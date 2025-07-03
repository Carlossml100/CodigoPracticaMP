package modelo.habilidades;

public class Talento implements HabilidadEspecial {
    private String nombre;
    private int valorAtaque; // 1-3
    private int valorDefensa; // 1-3

    public Talento(String nombre, int valorAtaque, int valorDefensa) {
        this.nombre = nombre;
        this.valorAtaque = Math.max(1, Math.min(3, valorAtaque));
        this.valorDefensa = Math.max(1, Math.min(3, valorDefensa));
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
}


