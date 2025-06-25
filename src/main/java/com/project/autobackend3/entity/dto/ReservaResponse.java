package com.project.autobackend3.entity.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ReservaResponse {
    private Long id;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String estado;
    private Integer usuarioId;
    private String usuarioNombre;
    private Long vehiculoId;
    private String vehiculoModelo;
}