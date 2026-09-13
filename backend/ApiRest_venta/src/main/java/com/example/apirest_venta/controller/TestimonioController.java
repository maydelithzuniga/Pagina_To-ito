package com.example.apirest_venta.controller;

import com.example.apirest_venta.dto.TestimonioDTO;
import com.example.apirest_venta.dto.TestimonioRequestDTO;
import com.example.apirest_venta.service.TestimonioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Testimonios de compradores mostrados en la landing page. Cualquier
 * visitante puede leerlos o dejar el suyo; solo el administrador puede
 * moderarlos (eliminarlos) o ver los que aun no estan aprobados.
 */
@RestController
@RequestMapping("/api/testimonios")
@RequiredArgsConstructor
public class TestimonioController {

    private final TestimonioService testimonioService;

    @GetMapping
    public ResponseEntity<List<TestimonioDTO>> listar() {
        return ResponseEntity.ok(testimonioService.listarAprobados());
    }

    /** Listado completo (incluye no aprobados) para moderacion. */
    @GetMapping("/admin")
    public ResponseEntity<List<TestimonioDTO>> listarTodos() {
        return ResponseEntity.ok(testimonioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestimonioDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(testimonioService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<TestimonioDTO> crear(@Valid @RequestBody TestimonioRequestDTO dto) {
        TestimonioDTO creado = testimonioService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        testimonioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
