package com.icesi.fit.controller;

import com.icesi.fit.model.Evento;
import com.icesi.fit.model.UsuarioEvento;
import com.icesi.fit.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    @GetMapping
    public ResponseEntity<List<Evento>> getAllEventos() {
        return ResponseEntity.ok(eventoService.findAllEventos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        return eventoService.findEventoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoService.saveEvento(evento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{eventoId}/inscribir/{usuarioId}")
    public ResponseEntity<UsuarioEvento> inscribirUsuario(@PathVariable Long eventoId, @PathVariable Long usuarioId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoService.inscribirUsuario(eventoId, usuarioId));
    }
}
