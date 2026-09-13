package com.example.apirest_venta.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Datos recibidos cuando un comprador deja su testimonio.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestimonioRequestDTO {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(max = 100, message = "El nombre no debe superar los 100 caracteres")
    private String nombreCliente;

    @NotBlank(message = "El comentario es obligatorio")
    @Size(max = 500, message = "El comentario no debe superar los 500 caracteres")
    private String comentario;

    @NotNull(message = "La calificacion es obligatoria")
    @Min(value = 1, message = "La calificacion minima es 1")
    @Max(value = 5, message = "La calificacion maxima es 5")
    private Integer calificacion;

    @Size(max = 120, message = "El producto adquirido no debe superar los 120 caracteres")
    private String productoAdquirido;
}
