package com.lab.spring_apis_lab.controller;

import com.lab.spring_apis_lab.model.Pelicula;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {

    private List<Pelicula> peliculas = new ArrayList<>();

    public PeliculaController() {
        peliculas.add(new Pelicula(1L, "Inception", "Christopher Nolan", "Ciencia ficcion", 2010));
        peliculas.add(new Pelicula(2L, "El Padrino", "Francis Ford Coppola", "Drama", 1972));
        peliculas.add(new Pelicula(3L, "Pulp Fiction", "Quentin Tarantino", "Crimen", 1994));
        peliculas.add(new Pelicula(4L, "Coco", "Lee Unkrich", "Animacion", 2017));
        peliculas.add(new Pelicula(5L, "Parasite", "Bong Joon-ho", "Thriller", 2019));
    }

    @GetMapping
    public List<Pelicula> obtenerTodos() {
        return peliculas;
    }

    @GetMapping("/{id}")
    public Pelicula obtenerPorId(@PathVariable Long id) {
        for (Pelicula p : peliculas) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    @PostMapping
    public Pelicula crear(@RequestBody Pelicula nuevaPelicula) {
        Long nuevoId = (long) (peliculas.size() + 1);
        nuevaPelicula.setId(nuevoId);
        peliculas.add(nuevaPelicula);
        return nuevaPelicula;
    }

    @PutMapping("/{id}")
    public Pelicula actualizar(@PathVariable Long id, @RequestBody Pelicula datosActualizados) {
        for (Pelicula p : peliculas) {
            if (p.getId().equals(id)) {
                p.setTitulo(datosActualizados.getTitulo());
                p.setDirector(datosActualizados.getDirector());
                p.setGenero(datosActualizados.getGenero());
                p.setAnio(datosActualizados.getAnio());
                return p;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Pelicula actualizarParcial(@PathVariable Long id, @RequestBody Pelicula camposActualizados) {
        for (Pelicula p : peliculas) {
            if (p.getId().equals(id)) {
                if (camposActualizados.getTitulo() != null) {
                    p.setTitulo(camposActualizados.getTitulo());
                }
                if (camposActualizados.getDirector() != null) {
                    p.setDirector(camposActualizados.getDirector());
                }
                if (camposActualizados.getGenero() != null) {
                    p.setGenero(camposActualizados.getGenero());
                }
                if (camposActualizados.getAnio() != null) {
                    p.setAnio(camposActualizados.getAnio());
                }
                return p;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        peliculas.removeIf(p -> p.getId().equals(id));
        return "Pelicula eliminada correctamente";
    }
}