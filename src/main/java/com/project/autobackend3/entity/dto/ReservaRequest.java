package com.project.autobackend3.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservaRequest {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Integer usuarioId;
    private Long vehiculoId;
}