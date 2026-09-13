package com.example.apirest_venta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Representacion de un testimonio que se expone hacia el cliente de la API.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestimonioDTO {

    private Long id;
    private String nombreCliente;
    private String comentario;
    private Integer calificacion;
    private String productoAdquirido;
    private LocalDate fechaPublicacion;
}
