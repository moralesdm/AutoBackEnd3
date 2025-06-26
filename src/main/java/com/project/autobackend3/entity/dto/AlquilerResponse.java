package com.project.autobackend3.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AlquilerResponse {
    private Long id;
    private Long reservaId;
    private LocalDateTime fechaInicioReal;
    private LocalDateTime fechaFinReal;
    private Double kilometrajeInicio;
    private Double kilometrajeFin;
    private String estado;
}