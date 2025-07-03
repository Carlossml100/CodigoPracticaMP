package modelo.personajes;

public class Vampiro extends Personaje {
    private int sangre; // 0-10
    private int edad;

    public Vampiro(String nombre, int salud, int poder, int oro, int sangre, int edad) {
        super(nombre, salud, poder, oro);
        this.sangre = Math.min(10, Math.max(0, sangre)); // limita entre 0 y 10
        this.edad = edad;
    }

    public int getSangre() {
        return sangre;
    }

    public void setSangre(int sangre) {
        this.sangre = Math.min(10, Math.max(0, sangre));
    }

    @Override
    public String getRaza() {
        return "Vampiro";
    }
}


