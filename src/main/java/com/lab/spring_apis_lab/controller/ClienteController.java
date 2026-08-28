package com.lab.spring_apis_lab.controller;

import com.lab.spring_apis_lab.model.Cliente;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private List<Cliente> clientes = new ArrayList<>();

    public ClienteController() {
        clientes.add(new Cliente(1L, "Jorge", "Aguilar", "jorge.aguilar@correo.com", "55123456"));
        clientes.add(new Cliente(2L, "Karla", "Solis", "karla.solis@correo.com", "55234567"));
        clientes.add(new Cliente(3L, "Marco", "Reyes", "marco.reyes@correo.com", "55345678"));
        clientes.add(new Cliente(4L, "Daniela", "Ortiz", "daniela.ortiz@correo.com", "55456789"));
        clientes.add(new Cliente(5L, "Pablo", "Cifuentes", "pablo.cifuentes@correo.com", "55567890"));
    }

    @GetMapping
    public List<Cliente> obtenerTodos() {
        return clientes;
    }

    @GetMapping("/{id}")
    public Cliente obtenerPorId(@PathVariable Long id) {
        for (Cliente c : clientes) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    @PostMapping
    public Cliente crear(@RequestBody Cliente nuevoCliente) {
        Long nuevoId = (long) (clientes.size() + 1);
        nuevoCliente.setId(nuevoId);
        clientes.add(nuevoCliente);
        return nuevoCliente;
    }

    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable Long id, @RequestBody Cliente datosActualizados) {
        for (Cliente c : clientes) {
            if (c.getId().equals(id)) {
                c.setNombre(datosActualizados.getNombre());
                c.setApellido(datosActualizados.getApellido());
                c.setCorreo(datosActualizados.getCorreo());
                c.setTelefono(datosActualizados.getTelefono());
                return c;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Cliente actualizarParcial(@PathVariable Long id, @RequestBody Cliente camposActualizados) {
        for (Cliente c : clientes) {
            if (c.getId().equals(id)) {
                if (camposActualizados.getNombre() != null) {
                    c.setNombre(camposActualizados.getNombre());
                }
                if (camposActualizados.getApellido() != null) {
                    c.setApellido(camposActualizados.getApellido());
                }
                if (camposActualizados.getCorreo() != null) {
                    c.setCorreo(camposActualizados.getCorreo());
                }
                if (camposActualizados.getTelefono() != null) {
                    c.setTelefono(camposActualizados.getTelefono());
                }
                return c;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        clientes.removeIf(c -> c.getId().equals(id));
        return "Cliente eliminado correctamente";
    }
}