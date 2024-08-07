package com.spotify.spotifyapp.controller;

import com.spotify.spotifyapp.model.*;
import com.spotify.spotifyapp.service.CancionService;
import com.spotify.spotifyapp.service.DiscoService;
import com.spotify.spotifyapp.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final CancionService cancionService;
    private final DiscoService discoService;

    public UsuarioController(UsuarioService usuarioService, CancionService cancionService, DiscoService discoService) {
        this.usuarioService = usuarioService;
        this.cancionService = cancionService;
        this.discoService = discoService;
    }

    @GetMapping("/usuarios/{usuarioId}/canciones-sugeridas")
    public ResponseEntity<List<Cancion>> getCancionesSugeridas(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(usuarioService.getCancionesSugeridasParaUsuario(usuarioId));
    }


    @PostMapping("/usuarios/{usuarioId}/puntuar-cancion")
    public ResponseEntity<PuntajeCancion> puntuarCancion(@PathVariable Long usuarioId, @RequestBody PuntajeCancion puntajeCancion) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        puntajeCancion.setUsuario(usuario);
        Cancion cancion = cancionService.getCancionById(puntajeCancion.getCancion().getId());
        puntajeCancion.setCancion(cancion);
        return ResponseEntity.ok(cancionService.puntuarCancion(puntajeCancion));
    }



    @PostMapping("/usuarios/{usuarioId}/puntuar-disco/{discoId}")
    public ResponseEntity<PuntajeDisco> puntuarDisco(
            @PathVariable Long usuarioId,
            @PathVariable Long discoId,
            @RequestBody double puntaje) {

        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        Disco disco = discoService.getDiscoById(discoId);

        PuntajeDisco puntajeDisco = new PuntajeDisco();
        puntajeDisco.setUsuario(usuario);
        puntajeDisco.setDisco(disco);
        puntajeDisco.setPuntaje(puntaje);

        return ResponseEntity.ok(discoService.puntuarDisco(puntajeDisco));
    }


    @GetMapping("/discos/{discoId}/promedio-puntaje")
    public ResponseEntity<Double> getPromedioPuntajeDisco(@PathVariable Long discoId) {
        return ResponseEntity.ok(discoService.getPromedioPuntajeDisco(discoId));
    }



}