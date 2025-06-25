package com.project.autobackend3.service.impl;

import com.project.autobackend3.entity.EstadoReserva;
import com.project.autobackend3.entity.Reserva;
import com.project.autobackend3.entity.Vehiculo;
import com.project.autobackend3.entity.dto.ReservaRequest;
import com.project.autobackend3.entity.dto.ReservaResponse;
import com.project.autobackend3.entity.usuario;
import com.project.autobackend3.repository.ReservaRepository;
import com.project.autobackend3.repository.UsuarioRepository;
import com.project.autobackend3.repository.VehiculoRepository;
import com.project.autobackend3.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final VehiculoRepository vehiculoRepository;

    @Override
    public List<ReservaResponse> listarTodas() {
        return reservaRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservaResponse> listarPorUsuario(Long usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ReservaResponse obtenerPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        return mapToResponse(reserva);
    }

    @Override
    public ReservaResponse crear(ReservaRequest request) {
        usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Vehiculo vehiculo = vehiculoRepository.findById(request.getVehiculoId())
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        Reserva reserva = Reserva.builder()
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .estado(EstadoReserva.PENDIENTE)
                .usuario(usuario)
                .vehiculo(vehiculo)
                .build();

        return mapToResponse(reservaRepository.save(reserva));
    }

    @Override
    public ReservaResponse cancelar(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reserva.setEstado(EstadoReserva.CANCELADA);
        return mapToResponse(reservaRepository.save(reserva));
    }

    private ReservaResponse mapToResponse(Reserva reserva) {
        return ReservaResponse.builder()
                .id(reserva.getId())
                .fechaInicio(reserva.getFechaInicio())
                .fechaFin(reserva.getFechaFin())
                .estado(reserva.getEstado().name())
                .usuarioId(reserva.getUsuario().getId())
                .usuarioNombre(reserva.getUsuario().getNombre() + " " + reserva.getUsuario().getApellido())
                .vehiculoId(reserva.getVehiculo().getId())
                .vehiculoModelo(reserva.getVehiculo().getModelo())
                .build();
    }
}