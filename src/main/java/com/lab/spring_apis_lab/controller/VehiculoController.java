package com.lab.spring_apis_lab.controller;

import com.lab.spring_apis_lab.model.Vehiculo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private List<Vehiculo> vehiculos = new ArrayList<>();

    public VehiculoController() {
        vehiculos.add(new Vehiculo(1L, "Toyota", "Corolla", 2022, 145000.00));
        vehiculos.add(new Vehiculo(2L, "Honda", "Civic", 2021, 138000.00));
        vehiculos.add(new Vehiculo(3L, "Mazda", "CX-5", 2023, 210000.00));
        vehiculos.add(new Vehiculo(4L, "Nissan", "Sentra", 2020, 115000.00));
        vehiculos.add(new Vehiculo(5L, "Hyundai", "Tucson", 2022, 195000.00));
    }

    @GetMapping
    public List<Vehiculo> obtenerTodos() {
        return vehiculos;
    }

    @GetMapping("/{id}")
    public Vehiculo obtenerPorId(@PathVariable Long id) {
        for (Vehiculo v : vehiculos) {
            if (v.getId().equals(id)) {
                return v;
            }
        }
        return null;
    }

    @PostMapping
    public Vehiculo crear(@RequestBody Vehiculo nuevoVehiculo) {
        Long nuevoId = (long) (vehiculos.size() + 1);
        nuevoVehiculo.setId(nuevoId);
        vehiculos.add(nuevoVehiculo);
        return nuevoVehiculo;
    }

    @PutMapping("/{id}")
    public Vehiculo actualizar(@PathVariable Long id, @RequestBody Vehiculo datosActualizados) {
        for (Vehiculo v : vehiculos) {
            if (v.getId().equals(id)) {
                v.setMarca(datosActualizados.getMarca());
                v.setModelo(datosActualizados.getModelo());
                v.setAnio(datosActualizados.getAnio());
                v.setPrecio(datosActualizados.getPrecio());
                return v;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Vehiculo actualizarParcial(@PathVariable Long id, @RequestBody Vehiculo camposActualizados) {
        for (Vehiculo v : vehiculos) {
            if (v.getId().equals(id)) {
                if (camposActualizados.getMarca() != null) {
                    v.setMarca(camposActualizados.getMarca());
                }
                if (camposActualizados.getModelo() != null) {
                    v.setModelo(camposActualizados.getModelo());
                }
                if (camposActualizados.getAnio() != null) {
                    v.setAnio(camposActualizados.getAnio());
                }
                if (camposActualizados.getPrecio() != 0) {
                    v.setPrecio(camposActualizados.getPrecio());
                }
                return v;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        vehiculos.removeIf(v -> v.getId().equals(id));
        return "Vehiculo eliminado correctamente";
    }
}
