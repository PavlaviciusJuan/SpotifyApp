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

    public AppRunner(CancionService cancionService, ArtistaService artistaService, DiscoService discoService) {
        this.cancionService = cancionService;
        this.artistaService = artistaService;
        this.discoService = discoService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Crear algunas canciones y guardarlas
        Cancion cancion1 = new Cancion();
        cancion1.setNombre("Primer Movimiento");
        cancion1.setLetra("Letra de ejemplo...");
        cancion1.setGenero("CLASICA");
        cancion1 = cancionService.createCancion(cancion1);  // Guardar cancion1

        Cancion cancion2 = new Cancion();
        cancion2.setNombre("So What");
        cancion2.setLetra("Letra de ejemplo...");
        cancion2.setGenero("JAZZ");
        cancion2 = cancionService.createCancion(cancion2);  // Guardar cancion2

        // Crear un artista
        Artista artista1 = new Artista();
        artista1.setNombre("Ludwig van Beethoven");
        artista1.setNacionalidad("Alemana");
        artista1.setFechaNacimiento(LocalDate.of(1770, 12, 17));
        artista1.setFechaFallecimiento(LocalDate.of(1827, 3, 26));
        artista1.setGeneros(Arrays.asList("CLASICA"));
        artista1.setInstrumentos(Arrays.asList("Piano", "Violín"));
        artista1.setBiografia("Compositor y pianista alemán.");
        artistaService.createArtista(artista1);

        Artista artista2 = new Artista();
        artista2.setNombre("Miles Davis");
        artista2.setNacionalidad("Estadounidense");
        artista2.setFechaNacimiento(LocalDate.of(1926, 5, 26));
        artista2.setFechaFallecimiento(LocalDate.of(1991, 9, 28));
        artista2.setGeneros(Arrays.asList("JAZZ"));
        artista2.setInstrumentos(Arrays.asList("Trompeta"));
        artista2.setBiografia("Trompetista y compositor de jazz.");
        artistaService.createArtista(artista2);

        // Obtener las canciones desde el repositorio para asegurarse de que están gestionadas por el contexto de persistencia
        cancion1 = cancionService.getCancionById(cancion1.getId());
        cancion2 = cancionService.getCancionById(cancion2.getId());

        // Crear un disco
        Disco disco1 = new Disco();
        disco1.setNombre("Sinfonía No. 5");
        disco1.setFechaLanzamiento(LocalDate.of(1808, 12, 22));
        disco1.setArtista(artista1);
        disco1.setGeneros(Arrays.asList("CLASICA"));
        disco1.setCanciones(Arrays.asList(cancion1));  // Añadir cancion1 que ya está guardada
        discoService.createDisco(disco1);

        Disco disco2 = new Disco();
        disco2.setNombre("Kind of Blue");
        disco2.setFechaLanzamiento(LocalDate.of(1959, 8, 17));
        disco2.setArtista(artista2);
        disco2.setGeneros(Arrays.asList("JAZZ"));
        disco2.setCanciones(Arrays.asList(cancion2));  // Añadir cancion2 que ya está guardada
        discoService.createDisco(disco2);

        // Buscar y mostrar resultados
        System.out.println("Buscar canción por ID 1: " + cancionService.getCancionById(1L));
        System.out.println("Buscar canciones por nombre 'So What': " + cancionService.searchCancionByName("So What"));
        System.out.println("Buscar canciones por letra 'Letra de ejemplo': " + cancionService.searchCancionByLetra("Letra de ejemplo"));

        System.out.println("Buscar artista por ID 1: " + artistaService.getArtistaById(1L));
        System.out.println("Buscar artistas por género JAZZ: " + artistaService.getArtistasByGenero("JAZZ"));
        System.out.println("Buscar artistas por nacionalidad 'Estadounidense': " + artistaService.getArtistasByNacionalidad("Estadounidense"));
        System.out.println("Buscar artistas por instrumento 'Piano': " + artistaService.getArtistasByInstrumento("Piano"));
        System.out.println("Buscar artistas vivos: " + artistaService.getArtistasVivos());
        System.out.println("Buscar artistas fallecidos: " + artistaService.getArtistasFallecidos());

        System.out.println("Buscar disco por ID 1: " + discoService.getDiscoById(1L));
        System.out.println("Buscar discos por nombre 'Kind of Blue': " + discoService.searchDiscoByName("Kind of Blue"));
        System.out.println("Buscar discos por género CLASICA: " + discoService.searchDiscoByGenero("CLASICA"));
        System.out.println("Buscar discos por fecha '1959-08-17': " + discoService.searchDiscoByFecha(LocalDate.of(1959, 8, 17)));
    }
}



