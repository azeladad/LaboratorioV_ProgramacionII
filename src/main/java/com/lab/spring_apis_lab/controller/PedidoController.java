package com.lab.spring_apis_lab.controller;

import com.lab.spring_apis_lab.model.Pedido;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private List<Pedido> pedidos = new ArrayList<>();

    public PedidoController() {
        pedidos.add(new Pedido(1L, "Jorge Aguilar", "Laptop", 1, 4500.00, "PENDIENTE"));
        pedidos.add(new Pedido(2L, "Karla Solis", "Mouse", 2, 171.00, "ENVIADO"));
        pedidos.add(new Pedido(3L, "Marco Reyes", "Teclado", 1, 150.00, "ENTREGADO"));
        pedidos.add(new Pedido(4L, "Daniela Ortiz", "Silla", 3, 1860.00, "PENDIENTE"));
        pedidos.add(new Pedido(5L, "Pablo Cifuentes", "Cuaderno", 5, 75.00, "ENVIADO"));
    }

    @GetMapping
    public List<Pedido> obtenerTodos() {
        return pedidos;
    }

    @GetMapping("/{id}")
    public Pedido obtenerPorId(@PathVariable Long id) {
        for (Pedido p : pedidos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    @PostMapping
    public Pedido crear(@RequestBody Pedido nuevoPedido) {
        Long nuevoId = (long) (pedidos.size() + 1);
        nuevoPedido.setId(nuevoId);
        pedidos.add(nuevoPedido);
        return nuevoPedido;
    }

    @PutMapping("/{id}")
    public Pedido actualizar(@PathVariable Long id, @RequestBody Pedido datosActualizados) {
        for (Pedido p : pedidos) {
            if (p.getId().equals(id)) {
                p.setCliente(datosActualizados.getCliente());
                p.setProducto(datosActualizados.getProducto());
                p.setCantidad(datosActualizados.getCantidad());
                p.setTotal(datosActualizados.getTotal());
                p.setEstado(datosActualizados.getEstado());
                return p;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Pedido actualizarParcial(@PathVariable Long id, @RequestBody Pedido camposActualizados) {
        for (Pedido p : pedidos) {
            if (p.getId().equals(id)) {
                if (camposActualizados.getCliente() != null) {
                    p.setCliente(camposActualizados.getCliente());
                }
                if (camposActualizados.getProducto() != null) {
                    p.setProducto(camposActualizados.getProducto());
                }
                if (camposActualizados.getCantidad() != null) {
                    p.setCantidad(camposActualizados.getCantidad());
                }
                if (camposActualizados.getTotal() != null) {
                    p.setTotal(camposActualizados.getTotal());
                }
                if (camposActualizados.getEstado() != null) {
                    p.setEstado(camposActualizados.getEstado());
                }
                return p;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        pedidos.removeIf(p -> p.getId().equals(id));
        return "Pedido eliminado correctamente";
    }
}