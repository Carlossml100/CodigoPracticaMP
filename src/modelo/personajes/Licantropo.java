package modelo.personajes;

public class Licantropo extends Personaje {
    private int rabia; // 0-3

    public Licantropo(String nombre, int salud, int poder, int oro, int rabia) {
        super(nombre, salud, poder, oro);
        this.rabia = Math.min(3, Math.max(0, rabia)); // limita entre 0 y 3
    }

    public int getRabia() {
        return rabia;
    }

    public void setRabia(int rabia) {
        this.rabia = Math.min(3, Math.max(0, rabia));
    }

    @Override
    public String getRaza() {
        return "Licántropo";
    }
}


