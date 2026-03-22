package com.icesi.fit.controller;

import com.icesi.fit.model.Rutina;
import com.icesi.fit.service.RutinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutinas")
@RequiredArgsConstructor
public class RutinaController {

    private final RutinaService rutinaService;

    @GetMapping
    public ResponseEntity<List<Rutina>> getAllRutinas() {
        return ResponseEntity.ok(rutinaService.findAllRutinas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rutina> getRutinaById(@PathVariable Long id) {
        return rutinaService.findRutinaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Rutina> createRutina(@RequestBody Rutina rutina) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rutinaService.saveRutina(rutina));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rutina> updateRutina(@PathVariable Long id, @RequestBody Rutina rutina) {
        return rutinaService.findRutinaById(id)
                .map(existing -> {
                    rutina.setId(id);
                    return ResponseEntity.ok(rutinaService.saveRutina(rutina));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRutina(@PathVariable Long id) {
        rutinaService.deleteRutina(id);
        return ResponseEntity.noContent().build();
    }
}
