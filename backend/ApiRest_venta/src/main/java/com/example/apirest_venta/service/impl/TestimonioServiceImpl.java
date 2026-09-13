package com.example.apirest_venta.service.impl;

import com.example.apirest_venta.dto.TestimonioDTO;
import com.example.apirest_venta.dto.TestimonioRequestDTO;
import com.example.apirest_venta.entity.Testimonio;
import com.example.apirest_venta.exception.RecursoNoEncontradoException;
import com.example.apirest_venta.mapper.TestimonioMapper;
import com.example.apirest_venta.repository.TestimonioRepository;
import com.example.apirest_venta.service.TestimonioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TestimonioServiceImpl implements TestimonioService {

    private final TestimonioRepository testimonioRepository;

    @Override
    public List<TestimonioDTO> listarAprobados() {
        return testimonioRepository.findByAprobadoTrueOrderByFechaPublicacionDesc().stream()
                .map(TestimonioMapper::toDTO)
                .toList();
    }

    @Override
    public List<TestimonioDTO> listarTodos() {
        return testimonioRepository.findAll().stream()
                .map(TestimonioMapper::toDTO)
                .toList();
    }

    @Override
    public TestimonioDTO obtenerPorId(Long id) {
        return testimonioRepository.findById(id)
                .map(TestimonioMapper::toDTO)
                .orElseThrow(() -> new RecursoNoEncontradoException("Testimonio no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public TestimonioDTO crear(TestimonioRequestDTO dto) {
        Testimonio testimonio = TestimonioMapper.toEntity(dto);
        return TestimonioMapper.toDTO(testimonioRepository.save(testimonio));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!testimonioRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Testimonio no encontrado con id: " + id);
        }
        testimonioRepository.deleteById(id);
    }
}
