package com.example.apirest_venta.dto;

import com.example.apirest_venta.entity.CategoriaProducto;
import com.example.apirest_venta.entity.TipoDiseno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Representacion de un producto que se expone hacia el cliente de la API.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private CategoriaProducto categoria;
    private TipoDiseno tipoDiseno;
    private String imagenUrl;
    private Boolean disponible;
    private Boolean destacado;
}
