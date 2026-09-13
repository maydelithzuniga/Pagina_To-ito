package com.example.apirest_venta.controller;

import com.example.apirest_venta.dto.ProductoDTO;
import com.example.apirest_venta.dto.ProductoRequestDTO;
import com.example.apirest_venta.entity.CategoriaProducto;
import com.example.apirest_venta.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Catalogo de productos de la landing page (poleras, polos y articulos en
 * resina). La lectura es publica; la creacion, edicion y eliminacion
 * requieren autenticacion de administrador (ver SecurityConfig).
 */
@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listar(
            @RequestParam(required = false) CategoriaProducto categoria) {
        if (categoria != null) {
            return ResponseEntity.ok(productoService.listarPorCategoria(categoria));
        }
        return ResponseEntity.ok(productoService.listarDisponibles());
    }

    @GetMapping("/destacados")
    public ResponseEntity<List<ProductoDTO>> listarDestacados() {
        return ResponseEntity.ok(productoService.listarDestacados());
    }

    /** Listado completo (incluye no disponibles) para el panel de administracion. */
    @GetMapping("/admin")
    public ResponseEntity<List<ProductoDTO>> listarTodos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@Valid @RequestBody ProductoRequestDTO dto) {
        ProductoDTO creado = productoService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizar(
            @PathVariable Long id, @Valid @RequestBody ProductoRequestDTO dto) {
        return ResponseEntity.ok(productoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
