ALTER TABLE usuarios ADD COLUMN activo tinyint;
UPDATE usuarios set activo =1;