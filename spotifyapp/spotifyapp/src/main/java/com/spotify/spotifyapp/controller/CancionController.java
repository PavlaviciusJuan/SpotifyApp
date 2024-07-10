package com.spotify.spotifyapp.controller;

import com.spotify.spotifyapp.model.Cancion;
import com.spotify.spotifyapp.service.CancionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/canciones")
public class CancionController {
    @Autowired
    private final CancionService cancionService;

    public CancionController(CancionService cancionService) {
        this.cancionService = cancionService;
    }

    @PostMapping
    public Cancion createCancion(@RequestBody Cancion cancion) {
        return cancionService.createCancion(cancion);
    }

    @PutMapping("/{id}")
    public Cancion updateCancion(@PathVariable Long id, @RequestBody Cancion cancionDetails) {
        return cancionService.updateCancion(id, cancionDetails);
    }

    @GetMapping("/{id}")
    public Cancion getCancionById(@PathVariable Long id) {
        return cancionService.getCancionById(id);
    }

    @GetMapping("/search")
    public List<Cancion> searchCancion(@RequestParam(required = false) String nombre,
                                       @RequestParam(required = false) String letra) {
        if (nombre != null) {
            return cancionService.searchCancionByName(nombre);
        } else if (letra != null) {
            return cancionService.searchCancionByLetra(letra);
        }
        return List.of();
    }
}

