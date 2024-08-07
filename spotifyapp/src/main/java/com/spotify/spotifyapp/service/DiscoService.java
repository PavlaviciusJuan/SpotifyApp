package com.spotify.spotifyapp.service;

import com.spotify.spotifyapp.model.Disco;
import com.spotify.spotifyapp.model.Genero;
import com.spotify.spotifyapp.model.PuntajeDisco;
import com.spotify.spotifyapp.repository.DiscoRepository;
import com.spotify.spotifyapp.repository.PuntajeDiscoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class DiscoService {
    @Autowired
    private final DiscoRepository discoRepository;
    private final PuntajeDiscoRepository puntajeDiscoRepository;

    public DiscoService(DiscoRepository discoRepository, PuntajeDiscoRepository puntajeDiscoRepository) {
        this.discoRepository = discoRepository;
        this.puntajeDiscoRepository = puntajeDiscoRepository;
    }

    public Disco createDisco(Disco disco) {
        return discoRepository.save(disco);
    }

    public Disco getDiscoById(Long id) {
        return discoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disco no encontrado"));
    }

    public List<Disco> searchDiscoByName(String nombre) {
        return discoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Disco> searchDiscoByGenero(Genero genero) {
        return discoRepository.findByGenerosContaining(genero);
    }

    public List<Disco> searchDiscoByFecha(LocalDate fecha) {
        return discoRepository.findByFechaLanzamiento(fecha);
    }

    public List<Disco> getAllDiscos() {
        return discoRepository.findAll();
    }

    @Transactional
    public PuntajeDisco puntuarDisco(PuntajeDisco puntajeDisco) {
        return puntajeDiscoRepository.save(puntajeDisco);
    }

    @Transactional(readOnly = true)
    public double getPromedioPuntajeDisco(Long discoId) {
        List<PuntajeDisco> puntajes = puntajeDiscoRepository.findByDiscoId(discoId);
        return puntajes.stream().mapToDouble(PuntajeDisco::getPuntaje).average().orElse(0.0);
    }
}

