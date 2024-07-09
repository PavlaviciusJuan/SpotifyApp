package com.spotify.spotifyapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String letra;

    //@Enumerated(EnumType.STRING)
    private String genero;
}

