package com.example.apirest_venta.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Testimonio dejado por un comprador para mostrarse en la landing page.
 */
@Entity
@Table(name = "testimonios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Testimonio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_cliente", nullable = false, length = 100)
    private String nombreCliente;

    @Column(nullable = false, length = 500)
    private String comentario;

    @Column(nullable = false)
    private Integer calificacion;

    @Column(name = "producto_adquirido", length = 120)
    private String productoAdquirido;

    @Column(name = "fecha_publicacion", nullable = false)
    @Builder.Default
    private LocalDate fechaPublicacion = LocalDate.now();

    /** Permite moderar testimonios antes de mostrarlos publicamente. */
    @Column(nullable = false)
    @Builder.Default
    private Boolean aprobado = true;
}
