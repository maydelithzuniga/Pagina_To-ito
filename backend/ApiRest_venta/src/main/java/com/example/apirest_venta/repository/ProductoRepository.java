package com.example.apirest_venta.repository;

import com.example.apirest_venta.entity.CategoriaProducto;
import com.example.apirest_venta.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio JPA para el catalogo de productos (poleras, polos y articulos
 * en resina). Al extender {@link JpaRepository} se obtienen las operaciones
 * CRUD basicas, y se agregan metodos de consulta derivados para las
 * necesidades de la landing page.
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByDisponibleTrue();

    List<Producto> findByCategoriaAndDisponibleTrue(CategoriaProducto categoria);

    List<Producto> findByDestacadoTrueAndDisponibleTrue();
}
