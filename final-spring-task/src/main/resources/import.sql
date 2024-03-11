INSERT INTO City (name) VALUES ("Cuenca");
INSERT INTO City (name) VALUES ("Quito");
INSERT INTO City (name) VALUES ("Guayaquil");
INSERT INTO City (name) VALUES ("Loja");
INSERT INTO City (name) VALUES ("Ambato");
INSERT INTO City (name) VALUES ("Machala");

INSERT INTO Company (name, phone, code_city) VALUES ("Rena CIA LTDA", "12346", 1);
INSERT INTO Company (name, phone, code_city) VALUES ("Venta Autos CIA LTDA", "546564", 2);
INSERT INTO Company (name, phone, code_city) VALUES ("Tech Solutions S.A.", "0987654321", 3);
INSERT INTO Company (name, phone, code_city) VALUES ("EduSoft S.A.", "0981234567", 4);
INSERT INTO Company (name, phone, code_city) VALUES ("AgroExport S.A.", "072345678", 2);
INSERT INTO Company (name, phone, code_city) VALUES ("EducaBooks S.A.", "072987654", 4);

INSERT INTO Department (name, code_company) VALUES ("Marketing", 1);
INSERT INTO Department (name, code_company) VALUES ("Sales", 2);
INSERT INTO Department (name, code_company) VALUES ("IT Support", 3);
INSERT INTO Department (name, code_company) VALUES ("Human Resources", 4);
INSERT INTO Department (name, code_company) VALUES ("Desarrollo de Software", 3);
INSERT INTO Department (name, code_company) VALUES ("Soporte Técnico", 4);

INSERT INTO User (id_card, name, last_name, email, phone, code_city, password) VALUES ("0106659139", "Lenin", "Cordova", "cordovalenin.44@gmail.com", "0969475973", 1, "123");
INSERT INTO User (id_card, name, last_name, email, phone, code_city, password) VALUES ("0103533766", "Carmen", "Tacuri", "carmen.tacuri@tecazuay.edu.ec", "0978831315", 1, "123");
INSERT INTO User (id_card, name, last_name, email, phone, code_city, password) VALUES ("0107894561", "Juan", "Perez", "juan.perez@example.com", "0987654321", 3, "123");
INSERT INTO User (id_card, name, last_name, email, phone, code_city, password) VALUES ("0106543218", "Maria", "Lopez", "maria.lopez@example.com", "0981234567", 4, "123");
INSERT INTO User (id_card, name, last_name, email, phone, code_city, password) VALUES ("0102030405", "Andrea", "Gomez", "andrea.gomez@example.com", "0998765432", 3, "password123");
INSERT INTO User (id_card, name, last_name, email, phone, code_city, password) VALUES ("0104050607", "Roberto", "Navarro", "roberto.navarro@example.com", "0996543218", 4, "password123");

INSERT INTO ticket_case(name) VALUES ("Infraestructura (Equipo, redes)");
INSERT INTO ticket_case(name) VALUES ("Hardware (Software no responde, programa caído)");
INSERT INTO ticket_case(name) VALUES ("Software (Errores de sistema)");
INSERT INTO ticket_case(name) VALUES ("Servicio al Cliente (Consultas generales)");
INSERT INTO ticket_case(name) VALUES ("Mantenimiento Preventivo");
INSERT INTO ticket_case(name) VALUES ("Consultas de Software");

INSERT INTO Ticket (code_ticket, state, create_at, title, description, code_case, code_department, id_card) VALUES (10001, 1, "2024-03-09", "Problema de red", "La conexión a internet está fallando en el tercer piso.", 3, 3, "0107894561");
INSERT INTO Ticket (code_ticket, state, create_at, title, description, code_case, code_department, id_card) VALUES (10002, 1, "2024-03-09", "Error de software", "El programa de contabilidad no abre.", 4, 4, "0106543218");
INSERT INTO Ticket (code_ticket, state, create_at, title, description, code_case, code_department, id_card) VALUES (10003, 1, "2024-03-10", "Actualización de Sistema", "Se requiere actualizar el sistema operativo de las estaciones de trabajo.", 5, 5, "0102030405");
INSERT INTO Ticket (code_ticket, state, create_at, title, description, code_case, code_department, id_card) VALUES (10004, 1, "2024-03-11", "Falla de Impresora", "La impresora del departamento de ventas no está funcionando.", 6, 6, "0104050607");

INSERT INTO Attached_file (file_name, code_ticket) VALUES ('', 10001);
INSERT INTO Attached_file (file_name, code_ticket) VALUES ('', 10002);
INSERT INTO Attached_file (file_name, code_ticket) VALUES ('', 10003);
INSERT INTO Attached_file (file_name, code_ticket) VALUES ('', 10004);