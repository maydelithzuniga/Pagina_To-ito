package com.example.apirest_venta.service.impl;

import com.example.apirest_venta.dto.ProductoDTO;
import com.example.apirest_venta.dto.ProductoRequestDTO;
import com.example.apirest_venta.entity.CategoriaProducto;
import com.example.apirest_venta.entity.Producto;
import com.example.apirest_venta.exception.RecursoNoEncontradoException;
import com.example.apirest_venta.mapper.ProductoMapper;
import com.example.apirest_venta.repository.ProductoRepository;
import com.example.apirest_venta.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> listarDisponibles() {
        return productoRepository.findByDisponibleTrue().stream()
                .map(ProductoMapper::toDTO)
                .toList();
    }

    @Override
    public List<ProductoDTO> listarTodos() {
        return productoRepository.findAll().stream()
                .map(ProductoMapper::toDTO)
                .toList();
    }

    @Override
    public List<ProductoDTO> listarDestacados() {
        return productoRepository.findByDestacadoTrueAndDisponibleTrue().stream()
                .map(ProductoMapper::toDTO)
                .toList();
    }

    @Override
    public List<ProductoDTO> listarPorCategoria(CategoriaProducto categoria) {
        return productoRepository.findByCategoriaAndDisponibleTrue(categoria).stream()
                .map(ProductoMapper::toDTO)
                .toList();
    }

    @Override
    public ProductoDTO obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .map(ProductoMapper::toDTO)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public ProductoDTO crear(ProductoRequestDTO dto) {
        Producto producto = ProductoMapper.toEntity(dto);
        return ProductoMapper.toDTO(productoRepository.save(producto));
    }

    @Override
    @Transactional
    public ProductoDTO actualizar(Long id, ProductoRequestDTO dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado con id: " + id));
        ProductoMapper.actualizarEntidad(producto, dto);
        return ProductoMapper.toDTO(productoRepository.save(producto));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Producto no encontrado con id: " + id);
        }
        productoRepository.deleteById(id);
    }
}
