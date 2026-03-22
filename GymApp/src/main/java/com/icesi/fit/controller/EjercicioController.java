package com.icesi.fit.controller;

import com.icesi.fit.model.Ejercicio;
import com.icesi.fit.service.EjercicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ejercicios")
@RequiredArgsConstructor
public class EjercicioController {

    private final EjercicioService ejercicioService;

    @GetMapping
    public ResponseEntity<List<Ejercicio>> getAllEjercicios() {
        return ResponseEntity.ok(ejercicioService.findAllEjercicios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ejercicio> getEjercicioById(@PathVariable Long id) {
        return ejercicioService.findEjercicioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ejercicio> createEjercicio(@RequestBody Ejercicio ejercicio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ejercicioService.saveEjercicio(ejercicio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ejercicio> updateEjercicio(@PathVariable Long id, @RequestBody Ejercicio ejercicio) {
        return ejercicioService.findEjercicioById(id)
                .map(existing -> {
                    ejercicio.setId(id);
                    return ResponseEntity.ok(ejercicioService.saveEjercicio(ejercicio));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEjercicio(@PathVariable Long id) {
        ejercicioService.deleteEjercicio(id);
        return ResponseEntity.noContent().build();
    }
}
