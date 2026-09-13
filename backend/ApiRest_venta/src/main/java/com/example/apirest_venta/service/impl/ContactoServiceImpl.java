package com.example.apirest_venta.service.impl;

import com.example.apirest_venta.dto.WhatsAppContactoDTO;
import com.example.apirest_venta.service.ContactoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class ContactoServiceImpl implements ContactoService {

    private final String numero;
    private final String mensaje;

    public ContactoServiceImpl(
            @Value("${app.contacto.whatsapp.numero}") String numero,
            @Value("${app.contacto.whatsapp.mensaje}") String mensaje) {
        this.numero = numero;
        this.mensaje = mensaje;
    }

    @Override
    public WhatsAppContactoDTO obtenerContactoWhatsApp() {
        String mensajeCodificado = URLEncoder.encode(mensaje, StandardCharsets.UTF_8);
        String enlace = "https://wa.me/" + numero + "?text=" + mensajeCodificado;
        return WhatsAppContactoDTO.builder()
                .numero(numero)
                .mensaje(mensaje)
                .enlace(enlace)
                .build();
    }
}
