package com.spotify.spotifyapp.service;

import com.spotify.spotifyapp.model.Cancion;
import com.spotify.spotifyapp.model.Disco;
import com.spotify.spotifyapp.model.Genero;
import com.spotify.spotifyapp.model.Usuario;
import com.spotify.spotifyapp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CancionService cancionService;

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public UsuarioService(UsuarioRepository usuarioRepository, CancionService cancionService) {
        this.usuarioRepository = usuarioRepository;
        this.cancionService = cancionService;
    }

    @Transactional(readOnly = true)
    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Cancion> getCancionesSugeridasParaUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findByIdWithPuntajesCanciones(usuarioId);
        List<Genero> generosQueLeGustan = usuario.getPuntajesCanciones().stream()
                .filter(puntaje -> puntaje.getPuntaje() >= 3.5)
                .map(puntaje -> puntaje.getCancion().getGenero())
                .distinct()
                .collect(Collectors.toList());

        return cancionService.findAll().stream()
                .filter(cancion -> generosQueLeGustan.contains(cancion.getGenero()))
                .collect(Collectors.toList());
    }
}

