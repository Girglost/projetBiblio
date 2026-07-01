-- =========================
-- AUTEUR (4 lignes)
-- =========================
INSERT INTO
    auteur (nom, prenom, nationalite)
VALUES ('Hugo', 'Victor', 'Française'),
    (
        'Camus',
        'Albert',
        'Française'
    ),
    (
        'Rowling',
        'J.K.',
        'Britannique'
    ),
    (
        'Orwell',
        'George',
        'Britannique'
    );

-- =========================
-- COLLECTION (4 lignes)
-- =========================
INSERT INTO
    collection (nom)
VALUES ('Classiques'),
    ('Fantasy'),
    ('Philosophie'),
    ('Dystopie');

-- =========================
-- EDITEUR (4 lignes)
-- =========================
INSERT INTO
    editeur (nom, pays)
VALUES ('Gallimard', 'France'),
    ('Le Seuil', 'France'),
    ('Bloomsbury', 'Royaume-Uni'),
    (
        'Penguin Books',
        'Royaume-Uni'
    );

-- =========================
-- LIVRE (4 lignes)
-- IMPORTANT : dépend des IDs générés
-- =========================
INSERT INTO
    livre (
        titre,
        résumer,
        année,
        auteur,
        éditeur,
        collection
    )
VALUES (
        'Les Misérables',
        'Roman social',
        1862,
        1,
        1,
        1
    ),
    (
        'L’Étranger',
        'Roman philosophique',
        1942,
        2,
        2,
        3
    ),
    (
        'Harry Potter à l’école des sorciers',
        'Jeune sorcier',
        1997,
        3,
        3,
        2
    ),
    (
        '1984',
        'Dystopie totalitaire',
        1949,
        4,
        4,
        4
    );