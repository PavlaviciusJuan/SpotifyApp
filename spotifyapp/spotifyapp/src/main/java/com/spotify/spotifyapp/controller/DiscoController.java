package com.spotify.spotifyapp.controller;

import com.spotify.spotifyapp.model.Disco;
import com.spotify.spotifyapp.service.DiscoService;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Disco createDisco(@RequestBody Disco disco) {
        return discoService.createDisco(disco);
    }

    @GetMapping("/{id}")
    public Disco getDiscoById(@PathVariable Long id) {
        return discoService.getDiscoById(id);
    }

    @GetMapping("/search")
    public List<Disco> searchDisco(@RequestParam(required = false) String nombre,
                                   @RequestParam(required = false) String genero,
                                   @RequestParam(required = false) LocalDate fecha) {
        if (nombre != null) {
            return discoService.searchDiscoByName(nombre);
        } else if (genero != null) {
            return discoService.searchDiscoByGenero(genero);
        } else if (fecha != null) {
            return discoService.searchDiscoByFecha(fecha);
        }
        return discoService.getAllDiscos();
    }
}

