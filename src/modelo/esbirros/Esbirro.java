package modelo.esbirros;

public abstract class Esbirro {
    protected String nombre;
    protected int salud; // entre 1 y 3

    public Esbirro(String nombre, int salud) {
        this.nombre = nombre;
        this.salud = Math.max(1, Math.min(3, salud));
    }

    public String getNombre() {
        return nombre;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = Math.max(1, Math.min(3, salud));
    }

    public abstract String getTipo();
}

