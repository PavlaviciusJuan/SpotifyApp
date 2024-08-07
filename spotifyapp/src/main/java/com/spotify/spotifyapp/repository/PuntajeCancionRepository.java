package com.spotify.spotifyapp.repository;

import com.spotify.spotifyapp.model.PuntajeCancion;
import com.spotify.spotifyapp.model.PuntajeDisco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PuntajeCancionRepository extends JpaRepository<PuntajeCancion, Long> {
    List<PuntajeCancion> findByCancionId(Long discoId);
}

