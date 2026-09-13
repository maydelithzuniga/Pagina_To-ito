package com.example.apirest_venta.exception;

/**
 * Se lanza cuando se busca un recurso (producto, testimonio, etc.) que no
 * existe en el repositorio.
 */
public class RecursoNoEncontradoException extends RuntimeException {

    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
