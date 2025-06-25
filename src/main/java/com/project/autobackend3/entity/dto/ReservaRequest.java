package com.project.autobackend3.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservaRequest {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Long usuarioId;
    private Long vehiculoId;
}