package modelo.personajes;

public class PersonajeFactory {

    public static Personaje crearPersonaje(String tipo, String nombre, int salud, int poder, int oro,
                                           int valorExtra1, int valorExtra2) {
        return switch (tipo.toLowerCase()) {
            case "vampiro" -> new Vampiro(nombre, salud, poder, oro, valorExtra1, valorExtra2);
            case "licantropo" -> new Licantropo(nombre, salud, poder, oro, valorExtra1);
            case "cazador" -> new Cazador(nombre, salud, poder, oro, valorExtra1);
            default -> throw new IllegalArgumentException("Tipo de personaje no válido: " + tipo);
        };
    }

}

