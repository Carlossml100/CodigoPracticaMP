import gestor.GestorUsuarios;
import gestor.GestorPersonajes;
import gestor.GestorDesafios;
import gestor.GestorCombates;
import modelo.usuario.*;
import modelo.personajes.Personaje;

import java.util.Scanner;

public class Aplicacion {

    private GestorPersonajes gestorPersonajes = new GestorPersonajes();
    private GestorDesafios gestorDesafios = new GestorDesafios();
    private GestorCombates gestorCombates = new GestorCombates();
    private GestorUsuarios gestorUsuarios = new GestorUsuarios(gestorCombates);

    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Registrarse");
            System.out.println("2. Entrar");
            System.out.println("3. Salir");
            System.out.print("Elige opción: ");

            int opcion = leerOpcion();

            switch (opcion) {
                case 1 -> registrarUsuario();
                case 2 -> entrarSistema();
                case 3 -> {
                    System.out.println("Saliendo del sistema...");
                    ejecutando = false;
                }
                default -> System.out.println("Opción inválida, inténtalo de nuevo.");
            }
        }



        scanner.close();
    }

    private void registrarUsuario() {
        System.out.print("¿Eres jugador (1) u operador (2)? ");
        int tipo = leerOpcion();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nick: ");
        String nick = scanner.nextLine();
        System.out.print("Password (8-12 caracteres): ");
        String password = scanner.nextLine();

        try {
            if (tipo == 1) {
                gestorUsuarios.registrarJugador(nombre, nick, password);
                System.out.println("Jugador registrado correctamente.");
            } else if (tipo == 2) {
                gestorUsuarios.registrarOperador(nombre, nick, password);
                System.out.println("Operador registrado correctamente.");
            } else {
                System.out.println("Tipo inválido.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void entrarSistema() {
        System.out.print("Nick: ");
        String nick = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        Usuario usuario = gestorUsuarios.login(nick, password);

        if (usuario == null) {
            System.out.println("Credenciales incorrectas.");
            return;
        }

        if (usuario instanceof Jugador jugador) {
            menuJugador(jugador);
        } else if (usuario instanceof Operador operador) {
            menuOperador(operador);
        }
    }

    private void menuJugador(Jugador jugador) {
        boolean enMenu = true;

        while (enMenu) {
            System.out.println("\n=== MENÚ JUGADOR ===");
            System.out.println("1. Registrar personaje");
            System.out.println("2. Dar de baja personaje");
            System.out.println("3. Elegir armas/armaduras activas");
            System.out.println("4. Desafiar a otro jugador");
            System.out.println("5. Ver desafíos recibidos");
            System.out.println("6. Aceptar desafío");
            System.out.println("7. Rechazar desafío");
            System.out.println("8. Consultar oro");
            System.out.println("9. Consultar ranking");
            System.out.println("10. Darse de baja usuario"); // NUEVO
            System.out.println("11. Cerrar sesión");
            System.out.print("Elige opción: ");

            int opcion = leerOpcion();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Tipo de personaje (vampiro/licantropo/cazador): ");
                    String tipo = scanner.nextLine().toLowerCase();

                    System.out.print("Nombre del personaje: ");
                    String nombrePersonaje = scanner.nextLine();

                    System.out.print("Salud (0-5): ");
                    int salud = Integer.parseInt(scanner.nextLine());

                    System.out.print("Poder (1-5): ");
                    int poder = Integer.parseInt(scanner.nextLine());

                    System.out.print("Oro: ");
                    int oro = Integer.parseInt(scanner.nextLine());

                    // Pedimos los extras según tipo:
                    int extra1 = 0, extra2 = 0;
                    if (tipo.equals("vampiro")) {
                        System.out.print("Cantidad de sangre (0-10): ");
                        extra1 = Integer.parseInt(scanner.nextLine());
                        System.out.print("Edad: ");
                        extra2 = Integer.parseInt(scanner.nextLine());
                    } else if (tipo.equals("licantropo")) {
                        System.out.print("Rabia (0-3): ");
                        extra1 = Integer.parseInt(scanner.nextLine());
                    } else if (tipo.equals("cazador")) {
                        System.out.print("Voluntad (0-3): ");
                        extra1 = Integer.parseInt(scanner.nextLine());
                    }

                    try {
                        gestorPersonajes.registrarPersonaje(jugador, tipo, nombrePersonaje, salud, poder, oro, extra1, extra2);
                        System.out.println("Personaje registrado correctamente.");
                    } catch (Exception e) {
                        System.out.println("Error creando personaje: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.print("Nombre del personaje a eliminar: ");
                    String nombreEliminar = scanner.nextLine();

                    boolean eliminado = gestorPersonajes.eliminarPersonaje(jugador, nombreEliminar);
                    if (eliminado) {
                        System.out.println("Personaje eliminado correctamente.");
                    } else {
                        System.out.println("No se encontró el personaje.");
                    }
                }

                case 3 -> {
                    if (jugador.getPersonajes().isEmpty()) {
                        System.out.println("No tienes personajes registrados.");
                        break;
                    }
                    System.out.println("Tus personajes:");
                    for (int i = 0; i < jugador.getPersonajes().size(); i++) {
                        System.out.println((i + 1) + ". " + jugador.getPersonajes().get(i).getNombre());
                    }
                    System.out.print("Selecciona personaje (número): ");
                    int idx = Integer.parseInt(scanner.nextLine()) - 1;

                    if (idx < 0 || idx >= jugador.getPersonajes().size()) {
                        System.out.println("Selección inválida.");
                        break;
                    }

                    Personaje personaje = jugador.getPersonajes().get(idx);

                    boolean gestionando = true;
                    while (gestionando) {
                        System.out.println("\nGestión de equipo para " + personaje.getNombre());
                        System.out.println("1. Listar armas disponibles");
                        System.out.println("2. Activar arma");
                        System.out.println("3. Desactivar arma");
                        System.out.println("4. Listar armas activas");
                        System.out.println("5. Listar armaduras disponibles");
                        System.out.println("6. Activar armadura");
                        System.out.println("7. Ver armadura activa");
                        System.out.println("8. Volver al menú jugador");
                        System.out.print("Elige opción: ");

                        int op = leerOpcion();

                        switch (op) {
                            case 1 -> {
                                System.out.println("Armas disponibles:");
                                var armasDisp = gestorPersonajes.obtenerArmasDisponibles(personaje);
                                if (armasDisp.isEmpty()) System.out.println("No tienes armas disponibles.");
                                else armasDisp.forEach(a -> System.out.println("- " + a.getNombre()));
                            }
                            case 2 -> {
                                System.out.print("Nombre del arma a activar: ");
                                String nombreArma = scanner.nextLine();
                                var arma = gestorPersonajes.obtenerArmasDisponibles(personaje).stream()
                                        .filter(a -> a.getNombre().equalsIgnoreCase(nombreArma))
                                        .findFirst().orElse(null);
                                if (arma == null) {
                                    System.out.println("Arma no encontrada.");
                                } else if (gestorPersonajes.activarArma(personaje, arma)) {
                                    System.out.println("Arma activada.");
                                } else {
                                    System.out.println("No se pudo activar el arma.");
                                }
                            }
                            case 3 -> {
                                System.out.print("Nombre del arma a desactivar: ");
                                String nombreArma = scanner.nextLine();
                                var arma = gestorPersonajes.obtenerArmasActivas(personaje).stream()
                                        .filter(a -> a.getNombre().equalsIgnoreCase(nombreArma))
                                        .findFirst().orElse(null);
                                if (arma == null) {
                                    System.out.println("Arma activa no encontrada.");
                                } else if (gestorPersonajes.desactivarArma(personaje, arma)) {
                                    System.out.println("Arma desactivada.");
                                } else {
                                    System.out.println("No se pudo desactivar el arma.");
                                }
                            }
                            case 4 -> {
                                System.out.println("Armas activas:");
                                var armasAct = gestorPersonajes.obtenerArmasActivas(personaje);
                                if (armasAct.isEmpty()) System.out.println("No tienes armas activas.");
                                else armasAct.forEach(a -> System.out.println("- " + a.getNombre()));
                            }
                            case 5 -> {
                                System.out.println("Armaduras disponibles:");
                                var armaduras = gestorPersonajes.obtenerArmadurasDisponibles(personaje);
                                if (armaduras.isEmpty()) System.out.println("No tienes armaduras disponibles.");
                                else armaduras.forEach(a -> System.out.println("- " + a.getNombre()));
                            }
                            case 6 -> {
                                System.out.print("Nombre de la armadura a activar: ");
                                String nombreArmadura = scanner.nextLine();
                                var armadura = gestorPersonajes.obtenerArmadurasDisponibles(personaje).stream()
                                        .filter(a -> a.getNombre().equalsIgnoreCase(nombreArmadura))
                                        .findFirst().orElse(null);
                                if (armadura == null) {
                                    System.out.println("Armadura no encontrada.");
                                } else if (gestorPersonajes.activarArmadura(personaje, armadura)) {
                                    System.out.println("Armadura activada.");
                                } else {
                                    System.out.println("No se pudo activar la armadura.");
                                }
                            }
                            case 7 -> {
                                var armaduraActiva = gestorPersonajes.obtenerArmaduraActiva(personaje);
                                if (armaduraActiva == null) {
                                    System.out.println("No tienes armadura activa.");
                                } else {
                                    System.out.println("Armadura activa: " + armaduraActiva.getNombre());
                                }
                            }
                            case 8 -> gestionando = false;
                            default -> System.out.println("Opción inválida.");
                        }
                    }
                }

                case 4 -> {
                    System.out.print("Nick del jugador a desafiar: ");
                    String nickObjetivo = scanner.nextLine();
                    Usuario objetivo = gestorUsuarios.buscarUsuarioPorNick(nickObjetivo);
                    if (objetivo == null || !(objetivo instanceof Jugador)) {
                        System.out.println("Jugador no encontrado.");
                    } else {
                        try {
                            gestorDesafios.enviarDesafio(jugador, (Jugador) objetivo);
                            System.out.println("Desafío enviado a " + nickObjetivo);
                        } catch (Exception e) {
                            System.out.println("Error al enviar desafío: " + e.getMessage());
                        }
                    }
                }
                case 5 -> {
                    var desafiosRecibidos = gestorDesafios.getDesafiosRecibidos(jugador);
                    if (desafiosRecibidos.isEmpty()) {
                        System.out.println("No tienes desafíos pendientes.");
                    } else {
                        System.out.println("Desafíos recibidos:");
                        for (int i = 0; i < desafiosRecibidos.size(); i++) {
                            System.out.println((i + 1) + ". " + desafiosRecibidos.get(i));
                        }
                    }
                }
                case 6 -> {
                    var desafiosRecibidos = gestorDesafios.getDesafiosRecibidos(jugador);
                    if (desafiosRecibidos.isEmpty()) {
                        System.out.println("No tienes desafíos pendientes.");
                    } else {
                        System.out.println("Selecciona el desafío a aceptar:");
                        for (int i = 0; i < desafiosRecibidos.size(); i++) {
                            System.out.println((i + 1) + ". " + desafiosRecibidos.get(i));
                        }
                        int sel = Integer.parseInt(scanner.nextLine()) - 1;
                        if (sel < 0 || sel >= desafiosRecibidos.size()) {
                            System.out.println("Selección inválida.");
                        } else {
                            gestorDesafios.aceptarDesafio(desafiosRecibidos.get(sel));
                            System.out.println("Desafío aceptado.");
                            // Aquí podrías añadir lógica de combate, actualización oro, etc.
                        }
                    }
                }

                case 7 -> {
                    var desafiosRecibidos = gestorDesafios.getDesafiosRecibidos(jugador);
                    if (desafiosRecibidos.isEmpty()) {
                        System.out.println("No tienes desafíos pendientes.");
                    } else {
                        System.out.println("Selecciona el desafío a rechazar:");
                        for (int i = 0; i < desafiosRecibidos.size(); i++) {
                            System.out.println((i + 1) + ". " + desafiosRecibidos.get(i));
                        }
                        int sel = Integer.parseInt(scanner.nextLine()) - 1;
                        if (sel < 0 || sel >= desafiosRecibidos.size()) {
                            System.out.println("Selección inválida.");
                        } else {
                            gestorDesafios.rechazarDesafio(desafiosRecibidos.get(sel));
                            System.out.println("Desafío rechazado.");
                        }
                    }
                }
                case 8 -> {
                    int oroNeto = gestorCombates.calcularOroNeto(jugador);
                    System.out.println("Tu oro neto ganado/perdido en combates es: " + oroNeto);
                }

                case 9 -> {
                    System.out.println("=== Ranking Global ===");
                    var ranking = gestorUsuarios.obtenerRankingGlobal();
                    if (ranking.isEmpty()) {
                        System.out.println("No hay jugadores registrados.");
                    } else {
                        int posicion = 1;
                        for (Jugador j : ranking) {
                            int oroNeto = gestorCombates.calcularOroNeto(j);
                            System.out.printf("%d. %s - Oro neto: %d%n", posicion++, j.getNick(), oroNeto);
                        }
                    }
                }

                case 10 -> {
                    System.out.print("¿Seguro que quieres darte de baja? (s/n): ");
                    String resp = scanner.nextLine();
                    if (resp.equalsIgnoreCase("s")) {
                        boolean eliminado = gestorUsuarios.eliminarJugador(jugador.getNick());
                        if (eliminado) {
                            System.out.println("Usuario dado de baja correctamente.");
                            enMenu = false; // cerrar sesión
                        } else {
                            System.out.println("Error al dar de baja.");
                        }
                    }
                }
                case 11 -> {
                    enMenu = false;
                    System.out.println("Sesión cerrada.");
                }
                default -> System.out.println("Opción inválida o no implementada.");
            }
        }
    }

    private void menuOperador(Operador operador) {
        boolean enMenu = true;

        while (enMenu) {
            System.out.println("\n=== MENÚ OPERADOR ===");
            System.out.println("1. Editar personaje");
            System.out.println("2. Añadir armas, armaduras, fortalezas, debilidades y esbirros");
            System.out.println("3. Validar desafíos");
            System.out.println("4. Bloquear usuario");
            System.out.println("5. Desbloquear usuario");
            System.out.println("6. Cerrar sesión");
            System.out.print("Elige opción: ");

            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    System.out.println("Funcionalidad para editar personaje aún no implementada.");
                    break;
                case 2:
                    System.out.println("Funcionalidad para añadir equipamiento aún no implementada.");
                    break;
                case 3:
                    System.out.println("Funcionalidad para validar desafíos aún no implementada.");
                    break;
                case 4:
                    System.out.println("Funcionalidad para bloquear usuario aún no implementada.");
                    break;
                case 5:
                    System.out.println("Funcionalidad para desbloquear usuario aún no implementada.");
                    break;
                case 6:
                    enMenu = false;
                    System.out.println("Sesión cerrada.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        new Aplicacion().iniciar();
    }
}

