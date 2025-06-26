package com.project.autobackend3.entity.dto;

import lombok.Data;

@Data
public class AlquilerInicioRequest {
    private Long reservaId;
    private Double kilometrajeInicio;
}