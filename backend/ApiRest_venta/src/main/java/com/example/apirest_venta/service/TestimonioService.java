package com.example.apirest_venta.service;

import com.example.apirest_venta.dto.TestimonioDTO;
import com.example.apirest_venta.dto.TestimonioRequestDTO;

import java.util.List;

/**
 * Reglas de negocio de los testimonios de compradores.
 */
public interface TestimonioService {

    List<TestimonioDTO> listarAprobados();

    List<TestimonioDTO> listarTodos();

    TestimonioDTO obtenerPorId(Long id);

    TestimonioDTO crear(TestimonioRequestDTO dto);

    void eliminar(Long id);
}
