package com.example.apirest_venta.controller;

import com.example.apirest_venta.dto.WhatsAppContactoDTO;
import com.example.apirest_venta.service.ContactoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Expone el enlace de contacto usado por el boton de "call to action" de la
 * landing page para redirigir al comprador a una conversacion de WhatsApp.
 */
@RestController
@RequestMapping("/api/contacto")
@RequiredArgsConstructor
public class ContactoController {

    private final ContactoService contactoService;

    @GetMapping("/whatsapp")
    public ResponseEntity<WhatsAppContactoDTO> obtenerWhatsApp() {
        return ResponseEntity.ok(contactoService.obtenerContactoWhatsApp());
    }
}
