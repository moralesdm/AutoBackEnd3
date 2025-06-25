package com.project.autobackend3.repository;

import com.project.autobackend3.entity.usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<usuario, Integer> {
    Optional<usuario> findByEmail(String email);
}