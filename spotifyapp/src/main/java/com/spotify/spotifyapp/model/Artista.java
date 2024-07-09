package com.spotify.spotifyapp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String nacionalidad;

    private LocalDate fechaNacimiento;

    private LocalDate fechaFallecimiento;

    @ElementCollection(fetch = FetchType.EAGER)
    //@Enumerated(EnumType.STRING)
    private List<String> generos;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> instrumentos;

    @Lob
    private String biografia;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Disco> discos;
}


