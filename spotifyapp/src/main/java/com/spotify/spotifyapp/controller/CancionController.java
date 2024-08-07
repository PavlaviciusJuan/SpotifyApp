package com.spotify.spotifyapp.controller;

import com.spotify.spotifyapp.model.Cancion;
import com.spotify.spotifyapp.service.CancionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Cancion> createCancion(@RequestBody Cancion cancion) {
        Cancion createdCancion = cancionService.createCancion(cancion);
        return new ResponseEntity<>(createdCancion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cancion> updateCancion(@PathVariable Long id, @RequestBody Cancion cancionDetails) {
        Cancion updatedCancion = cancionService.updateCancion(id, cancionDetails);
        if (updatedCancion != null) {
            return new ResponseEntity<>(updatedCancion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cancion> getCancionById(@PathVariable Long id) {
        Cancion cancion = cancionService.getCancionById(id);
        if (cancion != null) {
            return new ResponseEntity<>(cancion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Cancion>> searchCancion(@RequestParam(required = false) String nombre,
                                                       @RequestParam(required = false) String letra) {
        List<Cancion> canciones;
        if (nombre != null) {
            canciones = cancionService.searchCancionByName(nombre);
        } else if (letra != null) {
            canciones = cancionService.searchCancionByLetra(letra);
        } else {
            canciones = List.of();
        }
        return new ResponseEntity<>(canciones, HttpStatus.OK);
    }
}


