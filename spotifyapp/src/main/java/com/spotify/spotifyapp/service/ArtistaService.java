package com.spotify.spotifyapp.service;

import com.spotify.spotifyapp.model.Artista;
import com.spotify.spotifyapp.model.Genero;
import com.spotify.spotifyapp.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ArtistaService {
    @Autowired
    private final ArtistaRepository artistaRepository;

    public ArtistaService(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public Artista createArtista(Artista artista) {
        return artistaRepository.save(artista);
    }

    public Artista getArtistaById(Long id) {
        return artistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artista no encontrado"));
    }

    public List<Artista> getArtistasByGenero(Genero genero) {
        return artistaRepository.findByGenerosContaining(genero);
    }

    public List<Artista> getArtistasByNacionalidad(String nacionalidad) {
        return artistaRepository.findByNacionalidad(nacionalidad);
    }

    public List<Artista> getArtistasByInstrumento(String instrumento) {
        return artistaRepository.findByInstrumentosContaining(instrumento);
    }

    public List<Artista> getArtistasByEdad(int edad) {
        LocalDate startDate = LocalDate.now().minusYears(edad + 1);
        LocalDate endDate = LocalDate.now().minusYears(edad);
        return artistaRepository.findByFechaNacimientoBetween(startDate, endDate);
    }

    public List<Artista> getArtistasVivos() {
        return artistaRepository.findByFechaFallecimientoIsNull();
    }

    public List<Artista> getArtistasFallecidos() {
        return artistaRepository.findByFechaFallecimientoIsNotNull();
    }

    public List<Artista> getAllArtistas() {
        return artistaRepository.findAll();
    }
}

