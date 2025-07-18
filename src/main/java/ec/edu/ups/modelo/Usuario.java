package ec.edu.ups.modelo;

import ec.edu.ups.excepciones.ContrasenaInvalidaException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * La clase {@code Usuario} representa un usuario dentro del sistema.
 * Contiene información básica como nombre, apellido, cédula (username),
 * contraseña, rol y respuestas de seguridad.
 *
 * Esta clase también incluye validaciones para la cédula (como username)
 * y para la contraseña según requisitos de seguridad.
 *
 * Implementa {@link Serializable} para permitir persistencia.
 *
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String contrasenia;
    private Rol rol;

    private String nombre;
    private String apellido;


    private List<String> respuestasSeguridad;

    /**
     * Constructor vacío requerido para serialización y frameworks.
     */
    public Usuario() {
    }


    /**
     * Constructor básico sin rol.
     *
     * @param nombre Nombre del usuario.
     * @param apellido Apellido del usuario.
     * @param username Cédula del usuario.
     * @param contrasenia Contraseña del usuario.
     */
    public Usuario(String nombre, String apellido, String username, String contrasenia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.contrasenia = contrasenia;
        this.respuestasSeguridad = new ArrayList<>();
    }
    /**
     * Constructor completo.
     *
     * @param nombre Nombre del usuario.
     * @param apellido Apellido del usuario.
     * @param username Cédula del usuario.
     * @param contrasenia Contraseña del usuario.
     * @param rol Rol del usuario (ADMINISTRADOR o USUARIO).
     */
    public Usuario(String nombre, String apellido, String username, String contrasenia, Rol rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }
    /**
     * Verifica si el usuario tiene al menos 3 respuestas registradas.
     *
     * @param respuestas Lista de respuestas registradas.
     * @return {@code true} si tiene al menos 3, {@code false} en caso contrario.
     */
    public boolean tieneMinimoDeRespuestas(List<Respuesta> respuestas) {
        int contador = 0;
        for (Respuesta r : respuestas) {
            if (r.getUsuario().getUsername().equals(this.username)) {
                contador++;
            }
        }
        return contador >= 3;
    }
    /**
     * Valida que el username tenga formato de cédula ecuatoriana.
     *
     * @throws Exception si la cédula es inválida.
     */
    public void validarCedula() throws Exception {
        if (username == null || username.isEmpty()) {
            throw new Exception("La cédula no puede estar vacía.");
        }
        if (username.length() != 10) {
            throw new Exception("La cédula debe tener exactamente 10 dígitos.");
        }
        try {
            Long.parseLong(username);
        } catch (NumberFormatException e) {
            throw new Exception("La cédula solo debe contener números.");
        }
    }
    /**
     * Valida que la contraseña cumpla requisitos de seguridad:
     * al menos 6 caracteres, una minúscula, una mayúscula y un símbolo.
     *
     * @throws ContrasenaInvalidaException si no cumple con los requisitos.
     */
    public void validarContrasena() throws ContrasenaInvalidaException {
        if (contrasenia == null || contrasenia.isEmpty()) {
            throw new ContrasenaInvalidaException("La contraseña no puede estar vacía.");
        }
        if (contrasenia.length() < 6 ||
                !contrasenia.matches(".*[a-z].*") ||
                !contrasenia.matches(".*[A-Z].*") ||
                !contrasenia.matches(".*[@_\\-].*")) {
            throw new ContrasenaInvalidaException("La contraseña debe tener al menos 6 caracteres, una mayúscula, una minúscula y un símbolo (@ _ -).");
        }
    }
    // Getters y setters documentados opcionalmente también:
    /**
     * @return Nombre de usuario (cédula).
     */
    public List<String> getRespuestasSeguridad() {
        return respuestasSeguridad;
    }

    public void setRespuestasSeguridad(List<String> respuestasSeguridad) {
        this.respuestasSeguridad = respuestasSeguridad;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }



    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", rol=" + rol +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}