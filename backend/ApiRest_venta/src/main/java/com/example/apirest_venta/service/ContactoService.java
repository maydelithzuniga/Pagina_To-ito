package com.example.apirest_venta.service;

import com.example.apirest_venta.dto.WhatsAppContactoDTO;

/**
 * Provee la informacion de contacto usada por el call to action de la
 * landing page (redireccion a WhatsApp).
 */
public interface ContactoService {

    WhatsAppContactoDTO obtenerContactoWhatsApp();
}
