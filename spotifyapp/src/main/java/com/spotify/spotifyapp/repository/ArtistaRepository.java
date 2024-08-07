package com.spotify.spotifyapp.repository;

import com.spotify.spotifyapp.model.Artista;
import com.spotify.spotifyapp.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    List<Artista> findByGenerosContaining(Genero genero);

    List<Artista> findByNacionalidad(String nacionalidad);

    List<Artista> findByInstrumentosContaining(String instrumento);

    List<Artista> findByFechaNacimientoBetween(LocalDate startDate, LocalDate endDate);

    List<Artista> findByFechaFallecimientoIsNull();

    List<Artista> findByFechaFallecimientoIsNotNull();
}

