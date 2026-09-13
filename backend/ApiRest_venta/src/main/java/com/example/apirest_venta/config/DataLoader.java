package com.example.apirest_venta.config;

import com.example.apirest_venta.entity.CategoriaProducto;
import com.example.apirest_venta.entity.Producto;
import com.example.apirest_venta.entity.Testimonio;
import com.example.apirest_venta.entity.TipoDiseno;
import com.example.apirest_venta.repository.ProductoRepository;
import com.example.apirest_venta.repository.TestimonioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Carga el catalogo de productos y los testimonios de ejemplo en la base de
 * datos en memoria al iniciar la aplicacion. Al no requerir persistencia
 * real (es una landing page), esto reemplaza la necesidad de instalar y
 * administrar un servidor de base de datos externo.
 */
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ProductoRepository productoRepository;
    private final TestimonioRepository testimonioRepository;

    @Override
    public void run(String... args) {
        cargarProductos();
        cargarTestimonios();
    }

    private void cargarProductos() {
        if (productoRepository.count() > 0) {
            return;
        }

        productoRepository.saveAll(List.of(
                Producto.builder()
                        .nombre("Polera \"Mantente Firme\" - Josue 1:9")
                        .descripcion("Polera 100% algodon con estampado del versiculo Josue 1:9, ideal para el servicio del campo.")
                        .precio(new BigDecimal("45.00"))
                        .categoria(CategoriaProducto.POLERA)
                        .tipoDiseno(TipoDiseno.VERSICULO_BIBLICO)
                        .imagenUrl("/images/productos/polera-josue-1-9.jpg")
                        .disponible(true)
                        .destacado(true)
                        .build(),
                Producto.builder()
                        .nombre("Polo \"Predicando las Buenas Nuevas\"")
                        .descripcion("Polo bordado con la frase \"Predicando las buenas nuevas del Reino\", tela pique premium.")
                        .precio(new BigDecimal("55.00"))
                        .categoria(CategoriaProducto.POLO)
                        .tipoDiseno(TipoDiseno.FRASE_CRISTIANA)
                        .imagenUrl("/images/productos/polo-buenas-nuevas.jpg")
                        .disponible(true)
                        .destacado(true)
                        .build(),
                Producto.builder()
                        .nombre("Polera Vehiculo de Servicio")
                        .descripcion("Diseño ilustrado de un vehiculo de predicacion, personalizable con el nombre de tu congregacion.")
                        .precio(new BigDecimal("48.00"))
                        .categoria(CategoriaProducto.POLERA)
                        .tipoDiseno(TipoDiseno.VEHICULO)
                        .imagenUrl("/images/productos/polera-vehiculo.jpg")
                        .disponible(true)
                        .destacado(false)
                        .build(),
                Producto.builder()
                        .nombre("Llavero de Resina Personalizado")
                        .descripcion("Llavero artesanal en resina, personalizado con el texto o diseño que elijas.")
                        .precio(new BigDecimal("18.00"))
                        .categoria(CategoriaProducto.RESINA)
                        .tipoDiseno(TipoDiseno.DISENO_PERSONALIZADO)
                        .imagenUrl("/images/productos/llavero-resina.jpg")
                        .disponible(true)
                        .destacado(true)
                        .build(),
                Producto.builder()
                        .nombre("Cuadro de Resina con Texto Biblico")
                        .descripcion("Cuadro decorativo en resina con el versiculo y nombre que tu elijas, ideal como regalo de bautismo.")
                        .precio(new BigDecimal("65.00"))
                        .categoria(CategoriaProducto.RESINA)
                        .tipoDiseno(TipoDiseno.VERSICULO_BIBLICO)
                        .imagenUrl("/images/productos/cuadro-resina.jpg")
                        .disponible(true)
                        .destacado(false)
                        .build(),
                Producto.builder()
                        .nombre("Polo de Congregacion Personalizado")
                        .descripcion("Polo bordado con el nombre de tu congregacion o grupo, disponible en varias tallas y colores.")
                        .precio(new BigDecimal("52.00"))
                        .categoria(CategoriaProducto.POLO)
                        .tipoDiseno(TipoDiseno.DISENO_PERSONALIZADO)
                        .imagenUrl("/images/productos/polo-congregacion.jpg")
                        .disponible(true)
                        .destacado(false)
                        .build()
        ));
    }

    private void cargarTestimonios() {
        if (testimonioRepository.count() > 0) {
            return;
        }

        testimonioRepository.saveAll(List.of(
                Testimonio.builder()
                        .nombreCliente("Maria Fernandez")
                        .comentario("Excelente calidad en las poleras, el estampado del versiculo quedo hermoso y no se destiñe.")
                        .calificacion(5)
                        .productoAdquirido("Polera \"Mantente Firme\" - Josue 1:9")
                        .fechaPublicacion(LocalDate.of(2026, 3, 12))
                        .aprobado(true)
                        .build(),
                Testimonio.builder()
                        .nombreCliente("Carlos Ramirez")
                        .comentario("Pedi un cuadro de resina personalizado para el bautismo de mi hija y quedo espectacular, supero mis expectativas.")
                        .calificacion(5)
                        .productoAdquirido("Cuadro de Resina con Texto Biblico")
                        .fechaPublicacion(LocalDate.of(2026, 4, 2))
                        .aprobado(true)
                        .build(),
                Testimonio.builder()
                        .nombreCliente("Ana Torres")
                        .comentario("Muy buena atencion, me ayudaron a diseñar el polo de mi congregacion desde cero. Recomendado 100%.")
                        .calificacion(5)
                        .productoAdquirido("Polo de Congregacion Personalizado")
                        .fechaPublicacion(LocalDate.of(2026, 5, 20))
                        .aprobado(true)
                        .build(),
                Testimonio.builder()
                        .nombreCliente("Jose Luis Vargas")
                        .comentario("El llavero de resina llego rapido y con excelente acabado, ideal para regalar en el ministerio.")
                        .calificacion(4)
                        .productoAdquirido("Llavero de Resina Personalizado")
                        .fechaPublicacion(LocalDate.of(2026, 6, 15))
                        .aprobado(true)
                        .build()
        ));
    }
}
