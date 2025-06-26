package com.project.autobackend3.service;


import com.project.autobackend3.entity.dto.AlquilerFinalizarRequest;
import com.project.autobackend3.entity.dto.AlquilerInicioRequest;
import com.project.autobackend3.entity.dto.AlquilerResponse;

import java.util.List;

public interface AlquilerService {

    List<AlquilerResponse> listar();

    AlquilerResponse obtener(Long id);

    AlquilerResponse iniciarAlquiler(AlquilerInicioRequest request);

    AlquilerResponse finalizarAlquiler(Long alquilerId, AlquilerFinalizarRequest request);
}