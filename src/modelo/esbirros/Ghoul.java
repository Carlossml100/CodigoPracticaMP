package modelo.esbirros;

public class Ghoul extends Esbirro {
    private int dependencia; // entre 1 y 5

    public Ghoul(String nombre, int salud, int dependencia) {
        super(nombre, salud);
        this.dependencia = Math.max(1, Math.min(5, dependencia));
    }

    public int getDependencia() {
        return dependencia;
    }

    public void setDependencia(int dependencia) {
        this.dependencia = Math.max(1, Math.min(5, dependencia));
    }

    @Override
    public String getTipo() {
        return "Ghoul";
    }
}
