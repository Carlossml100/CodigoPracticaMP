package modelo.esbirros;

import java.util.ArrayList;
import java.util.List;

public class Demonio extends Esbirro {
    private String pactoDescripcion;
    private List<Esbirro> esbirros = new ArrayList<>();

    public Demonio(String nombre, int salud, String pactoDescripcion) {
        super(nombre, salud);
        this.pactoDescripcion = pactoDescripcion;
    }

    public String getPactoDescripcion() {
        return pactoDescripcion;
    }

    public void setPactoDescripcion(String pactoDescripcion) {
        this.pactoDescripcion = pactoDescripcion;
    }

    public List<Esbirro> getEsbirros() {
        return esbirros;
    }

    public void anadirEsbirro(Esbirro esbirro) {
        esbirros.add(esbirro);
    }

    public boolean eliminarEsbirro(String nombre) {
        return esbirros.removeIf(e -> e.getNombre().equalsIgnoreCase(nombre));
    }

    @Override
    public String getTipo() {
        return "Demonio";
    }
}

