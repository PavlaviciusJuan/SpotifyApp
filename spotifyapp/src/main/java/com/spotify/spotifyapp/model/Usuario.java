package com.spotify.spotifyapp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String email;

    private String password;

    @OneToMany(mappedBy = "usuario")
    private List<PuntajeCancion> puntajesCanciones;

    @OneToMany(mappedBy = "usuario")
    private List<PuntajeDisco> puntajesDiscos;

    public Usuario() {
        this.puntajesCanciones = new ArrayList<PuntajeCancion>();
        this.puntajesDiscos = new ArrayList<PuntajeDisco>();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<PuntajeCancion> getPuntajesCanciones() {
        return puntajesCanciones;
    }

    public void setPuntajesCanciones(List<PuntajeCancion> puntajesCanciones) {
        this.puntajesCanciones = puntajesCanciones;
    }

    public List<PuntajeDisco> getPuntajesDiscos() {
        return puntajesDiscos;
    }

    public void setPuntajesDiscos(List<PuntajeDisco> puntajesDiscos) {
        this.puntajesDiscos = puntajesDiscos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", puntajesCanciones=" + puntajesCanciones +
                ", puntajesDiscos=" + puntajesDiscos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return id != null ? id.equals(usuario.id) : usuario.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
