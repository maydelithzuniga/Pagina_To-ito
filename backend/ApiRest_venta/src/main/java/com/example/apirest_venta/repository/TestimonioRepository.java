package com.example.apirest_venta.repository;

import com.example.apirest_venta.entity.Testimonio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio JPA para los testimonios de compradores mostrados en la
 * landing page.
 */
public interface TestimonioRepository extends JpaRepository<Testimonio, Long> {

    List<Testimonio> findByAprobadoTrueOrderByFechaPublicacionDesc();
}
