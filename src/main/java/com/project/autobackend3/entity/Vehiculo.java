package com.project.autobackend2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "vehiculo")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private String color;
    private String tipo;
    private String anio;
    private Boolean estado;
    @Column(length = 512)
    private String imagenUrl;
    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private Sucursal sucursal;
    @ManyToMany
    @JoinTable(name = "vehiculo_caracteristica")
    private List<Caracteristica> caracteristicas = new ArrayList<>();
}
