package com.lab.spring_apis_lab.controller;

import com.lab.spring_apis_lab.model.Libro;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private List<Libro> libros = new ArrayList<>();

    public LibroController() {
        libros.add(new Libro(1L, "Cien Anios de Soledad", "Gabriel Garcia Marquez", "Realismo magico", 120.00));
        libros.add(new Libro(2L, "El Principito", "Antoine de Saint-Exupery", "Fabula", 85.00));
        libros.add(new Libro(3L, "1984", "George Orwell", "Distopia", 95.00));
        libros.add(new Libro(4L, "Don Quijote de la Mancha", "Miguel de Cervantes", "Novela clasica", 150.00));
        libros.add(new Libro(5L, "Rayuela", "Julio Cortazar", "Novela experimental", 110.00));
    }

    @GetMapping
    public List<Libro> obtenerTodos() {
        return libros;
    }

    @GetMapping("/{id}")
    public Libro obtenerPorId(@PathVariable Long id) {
        for (Libro l : libros) {
            if (l.getId().equals(id)) {
                return l;
            }
        }
        return null;
    }

    @PostMapping
    public Libro crear(@RequestBody Libro nuevoLibro) {
        Long nuevoId = (long) (libros.size() + 1);
        nuevoLibro.setId(nuevoId);
        libros.add(nuevoLibro);
        return nuevoLibro;
    }

    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable Long id, @RequestBody Libro datosActualizados) {
        for (Libro l : libros) {
            if (l.getId().equals(id)) {
                l.setTitulo(datosActualizados.getTitulo());
                l.setAutor(datosActualizados.getAutor());
                l.setGenero(datosActualizados.getGenero());
                l.setPrecio(datosActualizados.getPrecio());
                return l;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Libro actualizarParcial(@PathVariable Long id, @RequestBody Libro camposActualizados) {
        for (Libro l : libros) {
            if (l.getId().equals(id)) {
                if (camposActualizados.getTitulo() != null) {
                    l.setTitulo(camposActualizados.getTitulo());
                }
                if (camposActualizados.getAutor() != null) {
                    l.setAutor(camposActualizados.getAutor());
                }
                if (camposActualizados.getGenero() != null) {
                    l.setGenero(camposActualizados.getGenero());
                }
                if (camposActualizados.getPrecio() != 0) {
                    l.setPrecio(camposActualizados.getPrecio());
                }
                return l;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        libros.removeIf(l -> l.getId().equals(id));
        return "Libro eliminado correctamente";
    }
}