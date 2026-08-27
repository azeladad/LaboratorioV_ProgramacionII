package com.lab.spring_apis_lab.controller;

import com.lab.spring_apis_lab.model.Estudiante;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private List<Estudiante> estudiantes = new ArrayList<>();

    public EstudianteController() {
        estudiantes.add(new Estudiante(1L, "Angel", "Zelada", "Ingenieria en Sistemas", 21));
        estudiantes.add(new Estudiante(2L, "Maria", "Lopez", "Ingenieria Industrial", 22));
        estudiantes.add(new Estudiante(3L, "Carlos", "Perez", "Ingenieria Civil", 20));
        estudiantes.add(new Estudiante(4L, "Ana", "Garcia", "Ingenieria en Sistemas", 23));
        estudiantes.add(new Estudiante(5L, "Luis", "Ramirez", "Arquitectura", 24));
    }

    @GetMapping
    public List<Estudiante> obtenerTodos() {
        return estudiantes;
    }

    @GetMapping("/{id}")
    public Estudiante obtenerPorId(@PathVariable Long id) {
        for (Estudiante e : estudiantes) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    @PostMapping
    public Estudiante crear(@RequestBody Estudiante nuevoEstudiante) {
        Long nuevoId = (long) (estudiantes.size() + 1);
        nuevoEstudiante.setId(nuevoId);
        estudiantes.add(nuevoEstudiante);
        return nuevoEstudiante;
    }

    @PutMapping("/{id}")
    public Estudiante actualizar(@PathVariable Long id, @RequestBody Estudiante datosActualizados) {
        for (Estudiante e : estudiantes) {
            if (e.getId().equals(id)) {
                e.setNombre(datosActualizados.getNombre());
                e.setApellido(datosActualizados.getApellido());
                e.setCarrera(datosActualizados.getCarrera());
                e.setEdad(datosActualizados.getEdad());
                return e;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Estudiante actualizarParcial(@PathVariable Long id, @RequestBody Estudiante camposActualizados) {
        for (Estudiante e : estudiantes) {
            if (e.getId().equals(id)) {
                if (camposActualizados.getNombre() != null) {
                    e.setNombre(camposActualizados.getNombre());
                }
                if (camposActualizados.getApellido() != null) {
                    e.setApellido(camposActualizados.getApellido());
                }
                if (camposActualizados.getCarrera() != null) {
                    e.setCarrera(camposActualizados.getCarrera());
                }
                if (camposActualizados.getEdad() != 0) {
                    e.setEdad(camposActualizados.getEdad());
                }
                return e;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        estudiantes.removeIf(e -> e.getId().equals(id));
        return "Estudiante eliminado correctamente";
    }
}