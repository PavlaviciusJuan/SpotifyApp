package com.spotify.spotifyapp.service;

import com.spotify.spotifyapp.model.Disco;
import com.spotify.spotifyapp.repository.DiscoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DiscoService {
    @Autowired
    private final DiscoRepository discoRepository;

    public DiscoService(DiscoRepository discoRepository) {
        this.discoRepository = discoRepository;
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

    public List<Disco> searchDiscoByGenero(String genero) {
        return discoRepository.findByGenerosContaining(genero);
    }

    public List<Disco> searchDiscoByFecha(LocalDate fecha) {
        return discoRepository.findByFechaLanzamiento(fecha);
    }

    public List<Disco> getAllDiscos() {
        return discoRepository.findAll();
    }
}

