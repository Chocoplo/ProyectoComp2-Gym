package com.icesi.fit.controller;

import com.icesi.fit.model.Progreso;
import com.icesi.fit.model.Usuario;
import com.icesi.fit.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.findAllUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        return usuarioService.findUsuarioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario saved = usuarioService.saveUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.findUsuarioById(id)
                .map(existing -> {
                    usuario.setId(id);
                    return ResponseEntity.ok(usuarioService.saveUsuario(usuario));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/entrenador/{entrenadorId}")
    public ResponseEntity<Usuario> assignEntrenador(@PathVariable Long id, @PathVariable Long entrenadorId) {
        return ResponseEntity.ok(usuarioService.assignEntrenador(id, entrenadorId));
    }

    @GetMapping("/{entrenadorId}/asignados/progreso")
    public ResponseEntity<List<Progreso>> getProgresoDeAsignados(@PathVariable Long entrenadorId) {
        return ResponseEntity.ok(usuarioService.getProgresoDeAsignados(entrenadorId));
    }
}
