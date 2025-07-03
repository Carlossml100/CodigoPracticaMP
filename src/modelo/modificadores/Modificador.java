package modelo.modificadores;

public class Modificador {
    private String nombre;
    private int valor; // entre 1 y 5

    public Modificador(String nombre, int valor) {
        this.nombre = nombre;
        setValor(valor);
    }

    public String getNombre() {
        return nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        if (valor < 1) {
            this.valor = 1;
        } else if (valor > 5) {
            this.valor = 5;
        } else {
            this.valor = valor;
        }
    }

    @Override
    public String toString() {
        return nombre + " (valor: " + valor + ")";
    }
}


