package com.spotify.spotifyapp.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class PuntajeCancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cancion_id")
    private Cancion cancion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private double puntaje;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PuntajeCancion that = (PuntajeCancion) o;
        return Double.compare(that.puntaje, puntaje) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(cancion, that.cancion) &&
                Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cancion, usuario, puntaje);
    }

    @Override
    public String toString() {
        return "PuntajeCancion{" +
                "id=" + id +
                ", cancion=" + cancion +
                ", usuario=" + usuario +
                ", puntaje=" + puntaje +
                '}';
    }

}
