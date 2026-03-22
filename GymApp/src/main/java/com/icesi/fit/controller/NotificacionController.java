package com.icesi.fit.controller;

import com.icesi.fit.model.Notificacion;
import com.icesi.fit.service.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    @GetMapping
    public ResponseEntity<List<Notificacion>> getAllNotificaciones() {
        return ResponseEntity.ok(notificacionService.findAllNotificaciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> getNotificacionById(@PathVariable Long id) {
        return notificacionService.findNotificacionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/receptor/{receptorId}")
    public ResponseEntity<List<Notificacion>> getNotificacionesByReceptor(@PathVariable Long receptorId) {
        return ResponseEntity.ok(notificacionService.findByReceptorId(receptorId));
    }

    @PostMapping
    public ResponseEntity<Notificacion> createNotificacion(@RequestBody Notificacion notificacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacionService.saveNotificacion(notificacion));
    }

    @PutMapping("/{id}/leer")
    public ResponseEntity<Notificacion> markAsRead(@PathVariable Long id) {
        return ResponseEntity.ok(notificacionService.markAsRead(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificacion(@PathVariable Long id) {
        notificacionService.deleteNotificacion(id);
        return ResponseEntity.noContent().build();
    }
}
