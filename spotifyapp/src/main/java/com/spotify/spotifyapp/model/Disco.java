package com.spotify.spotifyapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Disco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ElementCollection(targetClass = Genero.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "DiscoGenero")
    private List<Genero> generos;

    private LocalDate fechaLanzamiento;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "DiscoCancion",
            joinColumns = @JoinColumn(name = "disco_id"),
            inverseJoinColumns = @JoinColumn(name = "cancion_id")
    )
    private List<Cancion> canciones;

    @OneToMany(mappedBy = "disco")
    private List<PuntajeDisco> puntajes = new ArrayList<>();;


    public Disco() {
    }

    public Disco(String nombre, List<Genero> generos, LocalDate fechaLanzamiento, Artista artista, List<Cancion> canciones) {
        this.nombre = nombre;
        this.generos = generos;
        this.fechaLanzamiento = fechaLanzamiento;
        this.artista = artista;
        this.canciones = canciones;
    }



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

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    public List<PuntajeDisco> getPuntajes() {
        return puntajes;
    }

    public void setPuntajes(List<PuntajeDisco> puntajes) {
        this.puntajes = puntajes;
    }

    public double getPromedioPuntaje() {
        return puntajes.stream().mapToDouble(PuntajeDisco::getPuntaje).average().orElse(0.0);
    }

    @Override
    public String toString() {
        return "Disco{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaLanzamiento=" + fechaLanzamiento +
                ", artista=" + artista +
                ", generos=" + generos +
                ", canciones=" + canciones +
                ", puntaje promedio=" + this.getPromedioPuntaje() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Disco disco = (Disco) o;

        return id != null ? id.equals(disco.id) : disco.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}



