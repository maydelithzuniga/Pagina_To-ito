package com.example.apirest_venta.mapper;

import com.example.apirest_venta.dto.TestimonioDTO;
import com.example.apirest_venta.dto.TestimonioRequestDTO;
import com.example.apirest_venta.entity.Testimonio;

import java.time.LocalDate;

/**
 * Conversion entre la entidad {@link Testimonio} y sus DTOs.
 */
public final class TestimonioMapper {

    private TestimonioMapper() {
    }

    public static TestimonioDTO toDTO(Testimonio testimonio) {
        if (testimonio == null) {
            return null;
        }
        return TestimonioDTO.builder()
                .id(testimonio.getId())
                .nombreCliente(testimonio.getNombreCliente())
                .comentario(testimonio.getComentario())
                .calificacion(testimonio.getCalificacion())
                .productoAdquirido(testimonio.getProductoAdquirido())
                .fechaPublicacion(testimonio.getFechaPublicacion())
                .build();
    }

    public static Testimonio toEntity(TestimonioRequestDTO dto) {
        return Testimonio.builder()
                .nombreCliente(dto.getNombreCliente())
                .comentario(dto.getComentario())
                .calificacion(dto.getCalificacion())
                .productoAdquirido(dto.getProductoAdquirido())
                .fechaPublicacion(LocalDate.now())
                .aprobado(true)
                .build();
    }
}
