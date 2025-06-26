package com.project.autobackend3.service.impl;

import com.project.autobackend3.entity.Alquiler;
import com.project.autobackend3.entity.EstadoAlquiler;
import com.project.autobackend3.entity.Reserva;
import com.project.autobackend3.entity.dto.AlquilerFinalizarRequest;
import com.project.autobackend3.entity.dto.AlquilerInicioRequest;
import com.project.autobackend3.entity.dto.AlquilerResponse;
import com.project.autobackend3.repository.AlquilerRepository;
import com.project.autobackend3.repository.ReservaRepository;
import com.project.autobackend3.service.AlquilerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlquilerServiceImpl implements AlquilerService {

    private final AlquilerRepository alquilerRepository;
    private final ReservaRepository reservaRepository;

    @Override
    public List<AlquilerResponse> listar() {
        return alquilerRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AlquilerResponse obtener(Long id) {
        return alquilerRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Alquiler no encontrado"));
    }

    @Override
    public AlquilerResponse iniciarAlquiler(AlquilerInicioRequest request) {
        if (alquilerRepository.existsByReservaId(request.getReservaId())) {
            throw new RuntimeException("Ya existe un alquiler para esta reserva.");
        }

        Reserva reserva = reservaRepository.findById(request.getReservaId())
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        Alquiler alquiler = Alquiler.builder()
                .reserva(reserva)
                .fechaInicioReal(LocalDateTime.now())
                .kilometrajeInicio(request.getKilometrajeInicio())
                .estado(EstadoAlquiler.EN_CURSO)
                .build();

        return mapToResponse(alquilerRepository.save(alquiler));
    }

    @Override
    public AlquilerResponse finalizarAlquiler(Long alquilerId, AlquilerFinalizarRequest request) {
        Alquiler alquiler = alquilerRepository.findById(alquilerId)
                .orElseThrow(() -> new RuntimeException("Alquiler no encontrado"));

        alquiler.setFechaFinReal(LocalDateTime.now());
        alquiler.setKilometrajeFin(request.getKilometrajeFin());
        alquiler.setEstado(EstadoAlquiler.FINALIZADO);

        return mapToResponse(alquilerRepository.save(alquiler));
    }

    private AlquilerResponse mapToResponse(Alquiler a) {
        return AlquilerResponse.builder()
                .id(a.getId())
                .reservaId(a.getReserva().getId())
                .fechaInicioReal(a.getFechaInicioReal())
                .fechaFinReal(a.getFechaFinReal())
                .kilometrajeInicio(a.getKilometrajeInicio())
                .kilometrajeFin(a.getKilometrajeFin())
                .estado(a.getEstado().name())
                .build();
    }
}
