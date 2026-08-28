package com.lab.spring_apis_lab.controller;

import com.lab.spring_apis_lab.model.Curso;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private List<Curso> cursos = new ArrayList<>();

    public CursoController() {
        cursos.add(new Curso(1L, "Programacion II", "Fundamentos de POO en Java", 4, "Presencial"));
        cursos.add(new Curso(2L, "Base de Datos I", "Modelado y consultas SQL", 3, "Presencial"));
        cursos.add(new Curso(3L, "Estructura de Datos", "Listas, pilas, colas y arboles", 4, "Virtual"));
        cursos.add(new Curso(4L, "Redes de Computadoras", "Fundamentos de redes y protocolos", 3, "Virtual"));
        cursos.add(new Curso(5L, "Ingenieria de Software", "Ciclo de vida y metodologias agiles", 4, "Hibrida"));
    }

    @GetMapping
    public List<Curso> obtenerTodos() {
        return cursos;
    }

    @GetMapping("/{id}")
    public Curso obtenerPorId(@PathVariable Long id) {
        for (Curso c : cursos) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    @PostMapping
    public Curso crear(@RequestBody Curso nuevoCurso) {
        Long nuevoId = (long) (cursos.size() + 1);
        nuevoCurso.setId(nuevoId);
        cursos.add(nuevoCurso);
        return nuevoCurso;
    }

    @PutMapping("/{id}")
    public Curso actualizar(@PathVariable Long id, @RequestBody Curso datosActualizados) {
        for (Curso c : cursos) {
            if (c.getId().equals(id)) {
                c.setNombre(datosActualizados.getNombre());
                c.setDescripcion(datosActualizados.getDescripcion());
                c.setCreditos(datosActualizados.getCreditos());
                c.setModalidad(datosActualizados.getModalidad());
                return c;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Curso actualizarParcial(@PathVariable Long id, @RequestBody Curso camposActualizados) {
        for (Curso c : cursos) {
            if (c.getId().equals(id)) {
                if (camposActualizados.getNombre() != null) {
                    c.setNombre(camposActualizados.getNombre());
                }
                if (camposActualizados.getDescripcion() != null) {
                    c.setDescripcion(camposActualizados.getDescripcion());
                }
                if (camposActualizados.getCreditos() != null) {
                    c.setCreditos(camposActualizados.getCreditos());
                }
                if (camposActualizados.getModalidad() != null) {
                    c.setModalidad(camposActualizados.getModalidad());
                }
                return c;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        cursos.removeIf(c -> c.getId().equals(id));
        return "Curso eliminado correctamente";
    }
}
