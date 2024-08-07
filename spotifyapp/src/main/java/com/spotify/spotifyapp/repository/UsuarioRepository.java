package com.spotify.spotifyapp.repository;

import com.spotify.spotifyapp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    /*
    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.puntajesCanciones LEFT JOIN FETCH u.puntajesDiscos WHERE u.id = :id")
    Usuario findByIdWithPuntajes(@Param("id") Long id);
    */
    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.puntajesCanciones WHERE u.id = :id")
    Usuario findByIdWithPuntajesCanciones(@Param("id") Long id);

    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.puntajesDiscos WHERE u.id = :id")
    Usuario findByIdWithPuntajesDiscos(@Param("id") Long id);
}

