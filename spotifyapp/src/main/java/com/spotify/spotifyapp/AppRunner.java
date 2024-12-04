package com.spotify.spotifyapp;

import com.spotify.spotifyapp.model.*;
import com.spotify.spotifyapp.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.Arrays;

@Component
public class AppRunner implements CommandLineRunner {

    private final CancionService cancionService;
    private final ArtistaService artistaService;
    private final DiscoService discoService;
    private final UsuarioService usuarioService;

    public AppRunner(CancionService cancionService, ArtistaService artistaService, DiscoService discoService, UsuarioService usuarioService) {
        this.cancionService = cancionService;
        this.artistaService = artistaService;
        this.discoService = discoService;
        this.usuarioService = usuarioService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setEmail("juan@gmail.com");
        usuario.setPassword("1234");
        usuario = usuarioService.createUsuario(usuario);

        Usuario usuario2 = new Usuario();
        usuario2.setNombre("Miguel");
        usuario2.setEmail("miguel@gmail.com");
        usuario2.setPassword("1234");
        usuario2 = usuarioService.createUsuario(usuario2);

        // Canciones
        Cancion cancion1 = new Cancion();
        cancion1.setNombre("Primer Movimiento");
        cancion1.setLetra("Letra de ejemplo...");
        cancion1.setGenero(Genero.CLASICA);
        cancion1 = cancionService.createCancion(cancion1);  // Guardar cancion1

        Cancion cancion2 = new Cancion();
        cancion2.setNombre("So What");
        cancion2.setLetra("Letra de ejemplo...");
        cancion2.setGenero(Genero.JAZZ);
        cancion2 = cancionService.createCancion(cancion2);  // Guardar cancion2

        // artista
        Artista artista1 = new Artista();
        artista1.setNombre("Ludwig van Beethoven");
        artista1.setNacionalidad("Alemana");
        artista1.setFechaNacimiento(LocalDate.of(1770, 12, 17));
        artista1.setFechaFallecimiento(LocalDate.of(1827, 3, 26));
        artista1.setGeneros(Arrays.asList(Genero.CLASICA));
        artista1.setInstrumentos(Arrays.asList("Piano", "Violín"));
        artista1.setBiografia("Compositor y pianista alemán.");
        artistaService.createArtista(artista1);

        Artista artista2 = new Artista();
        artista2.setNombre("Miles Davis");
        artista2.setNacionalidad("Estadounidense");
        artista2.setFechaNacimiento(LocalDate.of(1926, 5, 26));
        artista2.setFechaFallecimiento(LocalDate.of(1991, 9, 28));
        artista2.setGeneros(Arrays.asList(Genero.JAZZ));
        artista2.setInstrumentos(Arrays.asList("Trompeta"));
        artista2.setBiografia("Trompetista y compositor de jazz.");
        artistaService.createArtista(artista2);

        //
        cancion1 = cancionService.getCancionById(cancion1.getId());
        cancion2 = cancionService.getCancionById(cancion2.getId());

        // disco
        Disco disco1 = new Disco();
        disco1.setNombre("Sinfonía No. 5");
        disco1.setFechaLanzamiento(LocalDate.of(1808, 12, 22));
        disco1.setArtista(artista1);
        disco1.setGeneros(Arrays.asList(Genero.CLASICA));
        disco1.setCanciones(Arrays.asList(cancion1));  // Añadir cancion1 que ya está guardada
        discoService.createDisco(disco1);

        Disco disco2 = new Disco();
        disco2.setNombre("Kind of Blue");
        disco2.setFechaLanzamiento(LocalDate.of(1959, 8, 17));
        disco2.setArtista(artista2);
        disco2.setGeneros(Arrays.asList(Genero.JAZZ));
        disco2.setCanciones(Arrays.asList(cancion2));  // Añadir cancion2 que ya está guardada
        discoService.createDisco(disco2);

        //puntaje disco
        PuntajeDisco puntajeDisco = new PuntajeDisco();
        puntajeDisco.setUsuario(usuario);
        puntajeDisco.setDisco(disco1);
        puntajeDisco.setPuntaje(4.5);
        discoService.puntuarDisco(puntajeDisco);
        usuario.getPuntajesDiscos().add(puntajeDisco);

        PuntajeDisco puntajeDisco2 = new PuntajeDisco();
        puntajeDisco2.setUsuario(usuario2);
        puntajeDisco2.setDisco(disco1);
        puntajeDisco2.setPuntaje(3);

        discoService.puntuarDisco(puntajeDisco2);
        usuario2.getPuntajesDiscos().add(puntajeDisco2);

        //puntaje cancion

        PuntajeCancion puntajeCancion = new PuntajeCancion();
        puntajeCancion.setUsuario(usuario);
        puntajeCancion.setCancion(cancion1);
        puntajeCancion.setPuntaje(4.5);
        cancionService.puntuarCancion(puntajeCancion);
        usuario.getPuntajesCanciones().add(puntajeCancion);








    }
}



