package com.icesi.fit.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Permiso {

    // Admin permissions
    GESTIONAR_ENTRENADORES,
    GESTIONAR_BASE_DATOS,
    GESTIONAR_EVENTOS,

    // Entrenador permissions
    VER_PROGRESO_ASIGNADOS,
    CREAR_RUTINA,
    ENVIAR_RECOMENDACION,

    // Estudiante permissions
    VER_PROGRESO_PROPIO,
    REGISTRAR_PROGRESO,
    INSCRIBIRSE_EVENTO;

    public static List<Permiso> getPermisosByRol(Rol rol) {
        return switch (rol) {
            case ADMIN -> Arrays.asList(
                    GESTIONAR_ENTRENADORES,
                    GESTIONAR_BASE_DATOS,
                    GESTIONAR_EVENTOS);
            case ENTRENADOR -> Arrays.asList(
                    VER_PROGRESO_ASIGNADOS,
                    CREAR_RUTINA,
                    ENVIAR_RECOMENDACION);
            case ESTUDIANTE -> Arrays.asList(
                    VER_PROGRESO_PROPIO,
                    REGISTRAR_PROGRESO,
                    INSCRIBIRSE_EVENTO);
        };
    }
}
