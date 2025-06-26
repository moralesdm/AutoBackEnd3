package com.project.autobackend3.repository;

import com.project.autobackend3.entity.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
    boolean existsByReservaId(Long reservaId);
}