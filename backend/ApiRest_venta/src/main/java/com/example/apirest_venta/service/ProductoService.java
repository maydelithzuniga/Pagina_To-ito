package com.example.apirest_venta.service;

import com.example.apirest_venta.dto.ProductoDTO;
import com.example.apirest_venta.dto.ProductoRequestDTO;
import com.example.apirest_venta.entity.CategoriaProducto;

import java.util.List;

/**
 * Reglas de negocio del catalogo de productos.
 */
public interface ProductoService {

    List<ProductoDTO> listarDisponibles();

    List<ProductoDTO> listarTodos();

    List<ProductoDTO> listarDestacados();

    List<ProductoDTO> listarPorCategoria(CategoriaProducto categoria);

    ProductoDTO obtenerPorId(Long id);

    ProductoDTO crear(ProductoRequestDTO dto);

    ProductoDTO actualizar(Long id, ProductoRequestDTO dto);

    void eliminar(Long id);
}
