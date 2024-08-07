package com.spotify.spotifyapp.repository;

import com.spotify.spotifyapp.model.PuntajeDisco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PuntajeDiscoRepository extends JpaRepository<PuntajeDisco, Long> {
    List<PuntajeDisco> findByDiscoId(Long discoId);
}

