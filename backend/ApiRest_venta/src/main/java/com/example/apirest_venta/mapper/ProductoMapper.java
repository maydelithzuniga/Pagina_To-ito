package com.example.apirest_venta.mapper;

import com.example.apirest_venta.dto.ProductoDTO;
import com.example.apirest_venta.dto.ProductoRequestDTO;
import com.example.apirest_venta.entity.Producto;

/**
 * Conversion entre la entidad {@link Producto} y sus DTOs.
 */
public final class ProductoMapper {

    private ProductoMapper() {
    }

    public static ProductoDTO toDTO(Producto producto) {
        if (producto == null) {
            return null;
        }
        return ProductoDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .categoria(producto.getCategoria())
                .tipoDiseno(producto.getTipoDiseno())
                .imagenUrl(producto.getImagenUrl())
                .disponible(producto.getDisponible())
                .destacado(producto.getDestacado())
                .build();
    }

    public static Producto toEntity(ProductoRequestDTO dto) {
        return Producto.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .categoria(dto.getCategoria())
                .tipoDiseno(dto.getTipoDiseno())
                .imagenUrl(dto.getImagenUrl())
                .disponible(dto.getDisponible() != null ? dto.getDisponible() : Boolean.TRUE)
                .destacado(dto.getDestacado() != null ? dto.getDestacado() : Boolean.FALSE)
                .build();
    }

    public static void actualizarEntidad(Producto producto, ProductoRequestDTO dto) {
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setCategoria(dto.getCategoria());
        producto.setTipoDiseno(dto.getTipoDiseno());
        producto.setImagenUrl(dto.getImagenUrl());
        if (dto.getDisponible() != null) {
            producto.setDisponible(dto.getDisponible());
        }
        if (dto.getDestacado() != null) {
            producto.setDestacado(dto.getDestacado());
        }
    }
}
