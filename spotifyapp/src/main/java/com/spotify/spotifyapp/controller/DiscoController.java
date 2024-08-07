package com.spotify.spotifyapp.controller;

import com.spotify.spotifyapp.model.Disco;
import com.spotify.spotifyapp.model.Genero;
import com.spotify.spotifyapp.service.DiscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/discos")
public class DiscoController {

    @Autowired
    private final DiscoService discoService;

    public DiscoController(DiscoService discoService) {
        this.discoService = discoService;
    }

    @PostMapping
    public ResponseEntity<Disco> createDisco(@RequestBody Disco disco) {
        Disco createdDisco = discoService.createDisco(disco);
        return new ResponseEntity<>(createdDisco, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disco> getDiscoById(@PathVariable Long id) {
        Disco disco = discoService.getDiscoById(id);
        if (disco != null) {
            return new ResponseEntity<>(disco, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Disco>> searchDisco(@RequestParam(required = false) String nombre,
                                                   @RequestParam(required = false) Genero genero,
                                                   @RequestParam(required = false) LocalDate fecha) {
        List<Disco> discos;
        if (nombre != null) {
            discos = discoService.searchDiscoByName(nombre);
        } else if (genero != null) {
            discos = discoService.searchDiscoByGenero(genero);
        } else if (fecha != null) {
            discos = discoService.searchDiscoByFecha(fecha);
        } else {
            discos = discoService.getAllDiscos();
        }
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }
}


