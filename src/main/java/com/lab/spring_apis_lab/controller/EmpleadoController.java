package com.lab.spring_apis_lab.controller;

import com.lab.spring_apis_lab.model.Empleado;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private List<Empleado> empleados = new ArrayList<>();

    public EmpleadoController() {
        empleados.add(new Empleado(1L, "Roberto Gomez", "Gerente de Ventas", 12000.00, "Ventas"));
        empleados.add(new Empleado(2L, "Patricia Diaz", "Analista de Sistemas", 8500.00, "Tecnologia"));
        empleados.add(new Empleado(3L, "Fernando Castillo", "Contador", 7500.00, "Finanzas"));
        empleados.add(new Empleado(4L, "Laura Morales", "Recursos Humanos", 6800.00, "Recursos Humanos"));
        empleados.add(new Empleado(5L, "Diego Herrera", "Desarrollador", 9200.00, "Tecnologia"));
    }

    @GetMapping
    public List<Empleado> obtenerTodos() {
        return empleados;
    }

    @GetMapping("/{id}")
    public Empleado obtenerPorId(@PathVariable Long id) {
        for (Empleado e : empleados) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    @PostMapping
    public Empleado crear(@RequestBody Empleado nuevoEmpleado) {
        Long nuevoId = (long) (empleados.size() + 1);
        nuevoEmpleado.setId(nuevoId);
        empleados.add(nuevoEmpleado);
        return nuevoEmpleado;
    }

    @PutMapping("/{id}")
    public Empleado actualizar(@PathVariable Long id, @RequestBody Empleado datosActualizados) {
        for (Empleado e : empleados) {
            if (e.getId().equals(id)) {
                e.setNombre(datosActualizados.getNombre());
                e.setPuesto(datosActualizados.getPuesto());
                e.setSalario(datosActualizados.getSalario());
                e.setDepartamento(datosActualizados.getDepartamento());
                return e;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Empleado actualizarParcial(@PathVariable Long id, @RequestBody Empleado camposActualizados) {
        for (Empleado e : empleados) {
            if (e.getId().equals(id)) {
                if (camposActualizados.getNombre() != null) {
                    e.setNombre(camposActualizados.getNombre());
                }
                if (camposActualizados.getPuesto() != null) {
                    e.setPuesto(camposActualizados.getPuesto());
                }
                if (camposActualizados.getSalario() != 0) {
                    e.setSalario(camposActualizados.getSalario());
                }
                if (camposActualizados.getDepartamento() != null) {
                    e.setDepartamento(camposActualizados.getDepartamento());
                }
                return e;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        empleados.removeIf(e -> e.getId().equals(id));
        return "Empleado eliminado correctamente";
    }
}