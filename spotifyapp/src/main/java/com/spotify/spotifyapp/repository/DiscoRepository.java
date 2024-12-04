package com.spotify.spotifyapp.repository;

import com.spotify.spotifyapp.model.Disco;

import com.spotify.spotifyapp.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiscoRepository extends JpaRepository<Disco, Long> {
    List<Disco> findByNombreContainingIgnoreCase(String nombre);

    List<Disco> findByGenerosContaining(Genero genero);

    List<Disco> findByFechaLanzamiento(LocalDate fechaLanzamiento);
}

