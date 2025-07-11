package modelo.esbirros;

public class Humano extends Esbirro {

    public enum Lealtad {
        ALTA, NORMAL, BAJA
    }

    private Lealtad lealtad;

    public Humano(String nombre, int salud, Lealtad lealtad) {
        super(nombre, salud);
        this.lealtad = lealtad;
    }

    public Lealtad getLealtad() {
        return lealtad;
    }

    public void setLealtad(Lealtad lealtad) {
        this.lealtad = lealtad;
    }

    @Override
    public String getTipo() {
        return "Humano";
    }
}

