package com.spotify.spotifyapp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Disco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ElementCollection(fetch = FetchType.EAGER)
    //@Enumerated(EnumType.STRING)
    private List<String> generos;

    private LocalDate fechaLanzamiento;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "DISCO_CANCIONES",
            joinColumns = @JoinColumn(name = "disco_id"),
            inverseJoinColumns = @JoinColumn(name = "canciones_id")
    )
    private List<Cancion> canciones;
}


