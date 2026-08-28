package com.lab.spring_apis_lab.controller;

import com.lab.spring_apis_lab.model.Tarea;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private List<Tarea> tareas = new ArrayList<>();

    public TareaController() {
        tareas.add(new Tarea(1L, "Entregar Laboratorio V", "Terminar las 10 APIs REST", "Alta", false));
        tareas.add(new Tarea(2L, "Estudiar para el parcial", "Repasar POO y excepciones", "Alta", false));
        tareas.add(new Tarea(3L, "Actualizar el CV", "Agregar proyectos recientes", "Media", false));
        tareas.add(new Tarea(4L, "Configurar MySQL", "Instalar y probar conexion", "Media", true));
        tareas.add(new Tarea(5L, "Revisar correos del curso", "Ver anuncios pendientes", "Baja", true));
    }

    @GetMapping
    public List<Tarea> obtenerTodos() {
        return tareas;
    }

    @GetMapping("/{id}")
    public Tarea obtenerPorId(@PathVariable Long id) {
        for (Tarea t : tareas) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    @PostMapping
    public Tarea crear(@RequestBody Tarea nuevaTarea) {
        Long nuevoId = (long) (tareas.size() + 1);
        nuevaTarea.setId(nuevoId);
        tareas.add(nuevaTarea);
        return nuevaTarea;
    }

    @PutMapping("/{id}")
    public Tarea actualizar(@PathVariable Long id, @RequestBody Tarea datosActualizados) {
        for (Tarea t : tareas) {
            if (t.getId().equals(id)) {
                t.setTitulo(datosActualizados.getTitulo());
                t.setDescripcion(datosActualizados.getDescripcion());
                t.setPrioridad(datosActualizados.getPrioridad());
                t.setCompletada(datosActualizados.getCompletada());
                return t;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Tarea actualizarParcial(@PathVariable Long id, @RequestBody Tarea camposActualizados) {
        for (Tarea t : tareas) {
            if (t.getId().equals(id)) {
                if (camposActualizados.getTitulo() != null) {
                    t.setTitulo(camposActualizados.getTitulo());
                }
                if (camposActualizados.getDescripcion() != null) {
                    t.setDescripcion(camposActualizados.getDescripcion());
                }
                if (camposActualizados.getPrioridad() != null) {
                    t.setPrioridad(camposActualizados.getPrioridad());
                }
                if (camposActualizados.getCompletada() != null) {
                    t.setCompletada(camposActualizados.getCompletada());
                }
                return t;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        tareas.removeIf(t -> t.getId().equals(id));
        return "Tarea eliminada correctamente";
    }
}