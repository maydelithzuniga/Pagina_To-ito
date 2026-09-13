package com.example.apirest_venta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Informacion usada por el call to action de la landing page para redirigir
 * al comprador a una conversacion de WhatsApp con un mensaje predefinido.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WhatsAppContactoDTO {

    private String numero;
    private String mensaje;
    private String enlace;
}
