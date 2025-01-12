INSERT INTO app_user (
    first_name,
    last_name,
    email,
    password,
    role,
    doctor_id
)
VALUES
('Doc', 'Uno', 'docuno@telemed.com', 'docuno', 'DOCTOR', NULL),
('Duje', 'Doc', 'docduje@telemed.com', 'docduje', 'DOCTOR', NULL),
('Pero', 'Peric', 'peroperic@gmail.com', 'peroperic', 'PATIENT', 1),
('Gogo', 'Roschanu', 'gogoroschanu@gmail.com', 'gogoroschanu', 'PATIENT', 1),
('Ana', 'Anic', 'anaanic@gmail.com', 'anaanic', 'PATIENT', 2);

INSERT INTO blood_pressure_record (
    systolic_blood_pressure,
    diastolic_blood_pressure,
    heart_rate,
    short_description,
    date_of_measurement,
    patient_id
 )
VALUES
(120, 80, 75, 'Odmarao se u stolcu nekoliko minuta prije mjerenja.', '2025-01-01 15:00:00', 3),
(130, 85, 82, 'Završio sa pospremanjem po kući i sjeo da se opustim.', '2025-01-03 16:45:00', 3),
(140, 90, 88, 'Završio trening i mjerio poslje treninga.', '2025-01-02 10:30:00', 3),
(110, 70, 70, 'Sjedio, čitao knjigu i gledano televiziju nekoliko sati prije mjerenja.', '2025-01-02 18:15:00', 4),
(160, 100, 95, 'Osjećaj stresa zbog problema na poslu neposredno prije mjerenja.', '2025-01-01 17:00:00', 4),
(125, 85, 78, 'Upravo sam završio s laganim obrokom i sjeo sam se odmoriti.', '2025-01-03 14:30:00', 5);