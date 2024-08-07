package com.spotify.spotifyapp.controller;

import com.spotify.spotifyapp.model.Artista;
import com.spotify.spotifyapp.model.Genero;
import com.spotify.spotifyapp.service.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Artista> createArtista(@RequestBody Artista artista) {
        Artista createdArtista = artistaService.createArtista(artista);
        return new ResponseEntity<>(createdArtista, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artista> getArtistaById(@PathVariable Long id) {
        Artista artista = artistaService.getArtistaById(id);
        if (artista != null) {
            return new ResponseEntity<>(artista, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Artista>> searchArtista(@RequestParam(required = false) Genero genero,
                                                       @RequestParam(required = false) String nacionalidad,
                                                       @RequestParam(required = false) String instrumento,
                                                       @RequestParam(required = false) Integer edad,
                                                       @RequestParam(required = false) Boolean vivos,
                                                       @RequestParam(required = false) Boolean fallecidos) {
        List<Artista> artistas;
        if (genero != null) {
            artistas = artistaService.getArtistasByGenero(genero);
        } else if (nacionalidad != null) {
            artistas = artistaService.getArtistasByNacionalidad(nacionalidad);
        } else if (instrumento != null) {
            artistas = artistaService.getArtistasByInstrumento(instrumento);
        } else if (edad != null) {
            artistas = artistaService.getArtistasByEdad(edad);
        } else if (vivos != null && vivos) {
            artistas = artistaService.getArtistasVivos();
        } else if (fallecidos != null && fallecidos) {
            artistas = artistaService.getArtistasFallecidos();
        } else {
            artistas = artistaService.getAllArtistas();
        }
        return new ResponseEntity<>(artistas, HttpStatus.OK);
    }
}


