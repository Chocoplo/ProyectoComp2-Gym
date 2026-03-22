-- =============================================
-- Datos iniciales para Icesi Fit Application
-- =============================================

-- Usuarios: 1 Admin
INSERT INTO usuario (correo_institucional, nombre, rol) VALUES ('admin@icesi.edu.co', 'Carlos Admin', 'ADMIN');

-- Usuarios: 2 Entrenadores
INSERT INTO usuario (correo_institucional, nombre, rol) VALUES ('entrenador1@icesi.edu.co', 'Laura Entrenadora', 'ENTRENADOR');
INSERT INTO usuario (correo_institucional, nombre, rol) VALUES ('entrenador2@icesi.edu.co', 'Miguel Entrenador', 'ENTRENADOR');

-- 5 Ejercicios base
INSERT INTO ejercicio (nombre, tipo, descripcion, duracion_minutos, dificultad, url_video) VALUES ('Sentadillas', 'FUERZA', 'Ejercicio de fuerza para piernas y glúteos', 15, 'MEDIO', 'https://example.com/sentadillas');
INSERT INTO ejercicio (nombre, tipo, descripcion, duracion_minutos, dificultad, url_video) VALUES ('Correr en cinta', 'CARDIO', 'Ejercicio cardiovascular en cinta de correr', 30, 'FACIL', 'https://example.com/cinta');
INSERT INTO ejercicio (nombre, tipo, descripcion, duracion_minutos, dificultad, url_video) VALUES ('Peso muerto', 'FUERZA', 'Ejercicio compuesto de fuerza para espalda y piernas', 20, 'DIFICIL', 'https://example.com/peso-muerto');
INSERT INTO ejercicio (nombre, tipo, descripcion, duracion_minutos, dificultad, url_video) VALUES ('Yoga básico', 'MOVILIDAD', 'Rutina de yoga para movilidad y flexibilidad', 45, 'FACIL', 'https://example.com/yoga');
INSERT INTO ejercicio (nombre, tipo, descripcion, duracion_minutos, dificultad, url_video) VALUES ('Burpees', 'CARDIO', 'Ejercicio de alta intensidad para todo el cuerpo', 10, 'DIFICIL', 'https://example.com/burpees');

-- 1 Evento en el gimnasio
INSERT INTO evento (nombre, descripcion, fecha_hora, lugar, id_administrador) VALUES ('Jornada Fitness Icesi', 'Gran jornada deportiva abierta a toda la comunidad universitaria', '2026-04-15 08:00:00', 'Gimnasio Principal - Universidad Icesi', 1);
