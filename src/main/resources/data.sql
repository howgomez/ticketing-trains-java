-- Script de carga inicial de datos (se ejecuta al arrancar la aplicación)

-- 1. Insertar trenes de prueba
INSERT INTO trains (train_number, start_station, end_station, start_time, end_time, seat_number, price)
VALUES 
('G101', 'Madrid', 'Barcelona', '08:00', '10:30', 50, 120),
('G102', 'Madrid', 'Sevilla', '09:15', '11:45', 40, 95),
('G103', 'Valencia', 'Madrid', '07:30', '09:15', 35, 75),
('G104', 'Barcelona', 'Valencia', '14:00', '17:00', 60, 85),
('G105', 'Sevilla', 'Malaga', '18:00', '19:00', 30, 45)
ON CONFLICT DO NOTHING;

-- 2. Insertar usuarios y administradores de prueba
INSERT INTO members (username, password, real_name, gender, age, id_card, is_active, role, created_at)
VALUES 
('admin', 'admin123', 'Administrador Principal', 'M', 35, '00000000A', true, 'ROLE_ADMIN', NOW()),
('wilfredo', 'secretpassword', 'Wilfredo Gómez', 'M', 28, '12345678X', true, 'ROLE_MEMBER', NOW()),
('maria', 'pass1234', 'María López', 'F', 25, '87654321Y', true, 'ROLE_MEMBER', NOW())
ON CONFLICT DO NOTHING;
