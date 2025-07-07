package ec.edu.ups.modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String username;
    private String contrasenia;
    private Rol rol;

    private String nombre;
    private String apellido;

    private String pregunta1;
    private String pregunta2;
    private String pregunta3;
    private List<String> respuestasSeguridad;


    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String username, String contrasenia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.contrasenia = contrasenia;
        this.respuestasSeguridad = new ArrayList<>();
    }

    public Usuario(String nombre, String apellido, String username, String contrasenia, Rol rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

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

    public String getPregunta1() {
        return pregunta1;
    }

    public void setPregunta1(String pregunta1) {
        this.pregunta1 = pregunta1;
    }

    public String getPregunta2() {
        return pregunta2;
    }

    public void setPregunta2(String pregunta2) {
        this.pregunta2 = pregunta2;
    }

    public String getPregunta3() {
        return pregunta3;
    }

    public void setPregunta3(String pregunta3) {
        this.pregunta3 = pregunta3;
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