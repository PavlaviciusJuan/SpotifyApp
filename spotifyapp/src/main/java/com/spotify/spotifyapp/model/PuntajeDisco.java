package com.spotify.spotifyapp.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class PuntajeDisco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "disco_id")
    private Disco disco;

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

    public Disco getDisco() {
        return disco;
    }

    public void setDisco(Disco disco) {
        this.disco = disco;
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
        PuntajeDisco that = (PuntajeDisco) o;
        return Double.compare(that.puntaje, puntaje) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(disco, that.disco) &&
                Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, disco, usuario, puntaje);
    }

    @Override
    public String toString() {
        return "PuntajeDisco{" +
                "id=" + id +
                ", disco=" + disco +
                ", usuario=" + usuario +
                ", puntaje=" + puntaje +
                '}';
    }

}

