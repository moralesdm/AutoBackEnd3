package com.project.autobackend3.service;

import com.project.autobackend3.entity.dto.ReservaRequest;
import com.project.autobackend3.entity.dto.ReservaResponse;

import java.util.List;

public interface ReservaService {

    List<ReservaResponse> listarTodas();

    List<ReservaResponse> listarPorUsuario(Long usuarioId);

    ReservaResponse obtenerPorId(Long id);

    ReservaResponse crear(ReservaRequest request);

    ReservaResponse cancelar(Long id);
}