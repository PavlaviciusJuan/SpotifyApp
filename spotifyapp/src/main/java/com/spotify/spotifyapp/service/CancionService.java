package com.spotify.spotifyapp.service;

import com.spotify.spotifyapp.model.Cancion;
import com.spotify.spotifyapp.model.PuntajeCancion;
import com.spotify.spotifyapp.repository.PuntajeCancionRepository;
import com.spotify.spotifyapp.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CancionService {
    @Autowired
    private final CancionRepository cancionRepository;
    private final PuntajeCancionRepository puntajeCancionRepository;


    public CancionService(CancionRepository cancionRepository, PuntajeCancionRepository puntajeCancionRepository) {
        this.cancionRepository = cancionRepository;
        this.puntajeCancionRepository = puntajeCancionRepository;
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

    @Transactional(readOnly = true)
    public List<Cancion> findAll() {
        return cancionRepository.findAll();
    }

    @Transactional
    public PuntajeCancion puntuarCancion(PuntajeCancion puntajeCancion) {
        return puntajeCancionRepository.save(puntajeCancion);
    }

    @Transactional(readOnly = true)
    public double getPromedioPuntajeCancion(Long cancionId) {
        List<PuntajeCancion> puntajes = puntajeCancionRepository.findByCancionId(cancionId);
        return puntajes.stream().mapToDouble(PuntajeCancion::getPuntaje).average().orElse(0.0);
    }
}

