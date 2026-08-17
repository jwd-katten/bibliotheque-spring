-- Auteurs
INSERT INTO auteur (nom, prenom, nationalite, date_naissance) VALUES
('Martin', 'Jean', 'Française', '1980-05-15'),
('Dubois', 'Marie', 'Française', '1975-08-22'),
('El Fassi', 'Ahmed', 'Marocaine', '1990-03-10'),
('Smith', 'John', 'Américaine', '1985-11-30');

-- Livres
INSERT INTO livre (titre, annee, editeur, nb_exemplaires, categorie) VALUES
('Programmation Java', 2025, 'Dunod', 5, 'SCIENCE'),
('Python Facile', 2024, 'Eyrolles', 3, 'SCIENCE'),
('Le Petit Prince', 1943, 'Gallimard', 10, 'ROMAN');

-- Relations livre_auteur
INSERT INTO livre_auteur (id_livre, id_auteur) VALUES
(1, 1),
(1, 3),
(2, 1),
(3, 2);
