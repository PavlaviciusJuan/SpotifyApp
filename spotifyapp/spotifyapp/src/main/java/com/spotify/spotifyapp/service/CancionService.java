package com.spotify.spotifyapp.service;

import com.spotify.spotifyapp.model.Cancion;
import com.spotify.spotifyapp.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CancionService {
    @Autowired
    private final CancionRepository cancionRepository;

    public CancionService(CancionRepository cancionRepository) {
        this.cancionRepository = cancionRepository;
    }

    @Transactional
    public Cancion createCancion(Cancion cancion) {
        return cancionRepository.save(cancion);
    }

    @Transactional(readOnly = true)
    public Cancion updateCancion(Long id, Cancion cancionDetails) {
        Cancion cancion = cancionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Canción no encontrada"));

        cancion.setNombre(cancionDetails.getNombre());
        cancion.setLetra(cancionDetails.getLetra());
        cancion.setGenero(cancionDetails.getGenero());

        return cancionRepository.save(cancion);
    }

    @Transactional(readOnly = true)
    public Cancion getCancionById(Long id) {
        return cancionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Canción no encontrada"));
    }

    @Transactional(readOnly = true)
    public List<Cancion> searchCancionByName(String nombre) {
        return cancionRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Transactional(readOnly = true)
    public List<Cancion> searchCancionByLetra(String letra) {
        return cancionRepository.findByLetraContainingIgnoreCase(letra);
    }
}

