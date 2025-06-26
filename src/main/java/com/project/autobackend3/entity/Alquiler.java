package com.project.autobackend3.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaInicioReal;

    private LocalDateTime fechaFinReal;

    private Double kilometrajeInicio;

    private Double kilometrajeFin;

    @Enumerated(EnumType.STRING)
    private EstadoAlquiler estado;

    @OneToOne(optional = false)
    private Reserva reserva;
}