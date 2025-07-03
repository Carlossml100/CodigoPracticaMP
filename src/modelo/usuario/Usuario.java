package modelo.usuario;

import java.util.regex.Pattern;

public abstract class Usuario {
    protected String nombre;
    protected String nick;
    protected String password;

    public Usuario(String nombre, String nick, String password) {
        this.nombre = nombre;
        this.nick = nick;
        setPassword(password);
    }

    public String getNombre() {
        return nombre;
    }

    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        if (password == null || password.length() < 8 || password.length() > 12) {
            throw new IllegalArgumentException("La contraseña debe tener entre 8 y 12 caracteres");
        }
        this.password = password;
    }

    // Puedes añadir métodos comunes aquí (por ejemplo, validación de password, etc.)
}
