package com.spotify.spotifyapp.controller;

import com.spotify.spotifyapp.model.Artista;
import com.spotify.spotifyapp.service.ArtistaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artistas")
public class ArtistaController {
    @Autowired
    private final ArtistaService artistaService;

    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @PostMapping
    public Artista createArtista(@RequestBody Artista artista) {
        return artistaService.createArtista(artista);
    }

    @GetMapping("/{id}")
    public Artista getArtistaById(@PathVariable Long id) {
        return artistaService.getArtistaById(id);
    }

    @GetMapping("/search")
    public List<Artista> searchArtista(@RequestParam(required = false) String genero,
                                       @RequestParam(required = false) String nacionalidad,
                                       @RequestParam(required = false) String instrumento,
                                       @RequestParam(required = false) Integer edad,
                                       @RequestParam(required = false) Boolean vivos,
                                       @RequestParam(required = false) Boolean fallecidos) {
        if (genero != null) {
            return artistaService.getArtistasByGenero(genero);
        } else if (nacionalidad != null) {
            return artistaService.getArtistasByNacionalidad(nacionalidad);
        } else if (instrumento != null) {
            return artistaService.getArtistasByInstrumento(instrumento);
        } else if (edad != null) {
            return artistaService.getArtistasByEdad(edad);
        } else if (vivos != null && vivos) {
            return artistaService.getArtistasVivos();
        } else if (fallecidos != null && fallecidos) {
            return artistaService.getArtistasFallecidos();
        }
        return artistaService.getAllArtistas();
    }
}

