package modelo.personajes;

public class Cazador extends Personaje {
    private int voluntad; // 0-3

    public Cazador(String nombre, int salud, int poder, int oro, int voluntad) {
        super(nombre, salud, poder, oro);
        this.voluntad = Math.min(3, Math.max(0, voluntad)); // limita entre 0 y 3
    }

    public int getVoluntad() {
        return voluntad;
    }

    public void setVoluntad(int voluntad) {
        this.voluntad = Math.min(3, Math.max(0, voluntad));
    }

    @Override
    public String getRaza() {
        return "Cazador";
    }
}


