package com.icesi.fit.controller;

import com.icesi.fit.model.Progreso;
import com.icesi.fit.service.ProgresoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progresos")
@RequiredArgsConstructor
public class ProgresoController {

    private final ProgresoService progresoService;

    @GetMapping
    public ResponseEntity<List<Progreso>> getAllProgresos() {
        return ResponseEntity.ok(progresoService.findAllProgresos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Progreso> getProgresoById(@PathVariable Long id) {
        return progresoService.findProgresoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Progreso>> getProgresosByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(progresoService.findByUsuarioId(usuarioId));
    }

    @PostMapping
    public ResponseEntity<Progreso> createProgreso(@RequestBody Progreso progreso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(progresoService.saveProgreso(progreso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgreso(@PathVariable Long id) {
        progresoService.deleteProgreso(id);
        return ResponseEntity.noContent().build();
    }
}
