package com.spotify.spotifyapp.repository;

import com.spotify.spotifyapp.model.Cancion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long> {
    List<Cancion> findByNombreContainingIgnoreCase(String nombre);

    //List<Cancion> findByLetraContainingIgnoreCase(String letra);
    @Query("SELECT c FROM Cancion c WHERE LOWER(c.letra) LIKE LOWER(CONCAT('%', :letra, '%'))")
    List<Cancion> findByLetraContainingIgnoreCase(@Param("letra") String letra);
}

