package com.example.apirest_venta.dto;

import com.example.apirest_venta.entity.CategoriaProducto;
import com.example.apirest_venta.entity.TipoDiseno;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Datos recibidos para crear o actualizar un producto (solo administrador).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequestDTO {

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 120, message = "El nombre no debe superar los 120 caracteres")
    private String nombre;

    @Size(max = 500, message = "La descripcion no debe superar los 500 caracteres")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private BigDecimal precio;

    @NotNull(message = "La categoria es obligatoria")
    private CategoriaProducto categoria;

    private TipoDiseno tipoDiseno;

    private String imagenUrl;

    private Boolean disponible;

    private Boolean destacado;
}
