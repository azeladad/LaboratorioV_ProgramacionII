package com.lab.spring_apis_lab.controller;

import com.lab.spring_apis_lab.model.Producto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private List<Producto> productos = new ArrayList<>();

    public ProductoController() {
        productos.add(new Producto(1L, "Laptop", 4500.00, "Electronica"));
        productos.add(new Producto(2L, "Mouse", 85.50, "Electronica"));
        productos.add(new Producto(3L, "Escritorio", 950.00, "Muebles"));
        productos.add(new Producto(4L, "Silla", 620.00, "Muebles"));
        productos.add(new Producto(5L, "Cuaderno", 15.00, "Papeleria"));
    }

    // GET /api/productos - obtener todos
    @GetMapping
    public List<Producto> obtenerTodos() {
        return productos;
    }

    // GET /api/productos/{id} - obtener uno por id
    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable Long id) {
        for (Producto p : productos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    // POST /api/productos - crear uno nuevo
    @PostMapping
    public Producto crear(@RequestBody Producto nuevoProducto) {
        Long nuevoId = (long) (productos.size() + 1);
        nuevoProducto.setId(nuevoId);
        productos.add(nuevoProducto);
        return nuevoProducto;
    }

    // PUT /api/productos/{id} - actualizar completo
    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto datosActualizados) {
        for (Producto p : productos) {
            if (p.getId().equals(id)) {
                p.setNombre(datosActualizados.getNombre());
                p.setPrecio(datosActualizados.getPrecio());
                p.setCategoria(datosActualizados.getCategoria());
                return p;
            }
        }
        return null;
    }

    // PATCH /api/productos/{id} - actualizar parcial
    @PatchMapping("/{id}")
    public Producto actualizarParcial(@PathVariable Long id, @RequestBody Producto camposActualizados) {
        for (Producto p : productos) {
            if (p.getId().equals(id)) {
                if (camposActualizados.getNombre() != null) {
                    p.setNombre(camposActualizados.getNombre());
                }
                if (camposActualizados.getPrecio() != 0) {
                    p.setPrecio(camposActualizados.getPrecio());
                }
                if (camposActualizados.getCategoria() != null) {
                    p.setCategoria(camposActualizados.getCategoria());
                }
                return p;
            }
        }
        return null;
    }

    // DELETE /api/productos/{id} - eliminar
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        productos.removeIf(p -> p.getId().equals(id));
        return "Producto eliminado correctamente";
    }
}