package com.project.autobackend3.controller;

import com.project.autobackend3.entity.dto.AlquilerFinalizarRequest;
import com.project.autobackend3.entity.dto.AlquilerInicioRequest;
import com.project.autobackend3.entity.dto.AlquilerResponse;
import com.project.autobackend3.service.AlquilerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alquileres/alquileres")
@RequiredArgsConstructor
public class AlquilerController {

    private final AlquilerService alquilerService;

    @GetMapping
    public ResponseEntity<List<AlquilerResponse>> listar() {
        return ResponseEntity.ok(alquilerService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlquilerResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(alquilerService.obtener(id));
    }

    @PostMapping("/iniciar")
    public ResponseEntity<AlquilerResponse> iniciar(@RequestBody AlquilerInicioRequest request) {
        return ResponseEntity.status(201).body(alquilerService.iniciarAlquiler(request));
    }

    @PostMapping("/finalizar/{id}")
    public ResponseEntity<AlquilerResponse> finalizar(@PathVariable Long id, @RequestBody AlquilerFinalizarRequest request) {
        return ResponseEntity.ok(alquilerService.finalizarAlquiler(id, request));
    }
}