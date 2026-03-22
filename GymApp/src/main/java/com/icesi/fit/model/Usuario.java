package com.icesi.fit.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "correo_institucional", nullable = false, length = 255, unique = true)
    private String correoInstitucional;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Rol rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entrenador_asignado")
    private Usuario entrenadorAsignado;

    @OneToMany(mappedBy = "entrenadorAsignado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Usuario> asignados = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Progreso> progresos = new ArrayList<>();

    @OneToMany(mappedBy = "emisor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Notificacion> notificacionesEnviadas = new ArrayList<>();

    @OneToMany(mappedBy = "receptor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Notificacion> notificacionesRecibidas = new ArrayList<>();
}
