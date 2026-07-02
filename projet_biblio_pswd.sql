-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 02 juil. 2026 à 07:57
-- Version du serveur : 8.4.7
-- Version de PHP : 8.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `projet_biblio`
--

-- --------------------------------------------------------

--
-- Structure de la table `auteur`
--

DROP TABLE IF EXISTS `auteur`;
CREATE TABLE IF NOT EXISTS `auteur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nationalite` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `auteur`
--

INSERT INTO `auteur` (`id`, `nationalite`, `nom`, `prenom`) VALUES
(1, 'Française', 'Hugo', 'Victor'),
(2, 'Française', 'Camus', 'Albert'),
(3, 'Britannique', 'Rowling', 'J.K.'),
(4, 'Britannique', 'Orwell', 'George');

-- --------------------------------------------------------

--
-- Structure de la table `collection`
--

DROP TABLE IF EXISTS `collection`;
CREATE TABLE IF NOT EXISTS `collection` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `collection`
--

INSERT INTO `collection` (`id`, `nom`) VALUES
(1, 'Classiques'),
(2, 'Fantasy'),
(3, 'Philosophie'),
(4, 'Dystopie');

-- --------------------------------------------------------

--
-- Structure de la table `editeur`
--

DROP TABLE IF EXISTS `editeur`;
CREATE TABLE IF NOT EXISTS `editeur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `pays` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `editeur`
--

INSERT INTO `editeur` (`id`, `nom`, `pays`) VALUES
(1, 'Gallimard', 'France'),
(2, 'Le Seuil', 'France'),
(3, 'Bloomsbury', 'Royaume-Uni'),
(4, 'Penguin Books', 'Royaume-Uni');

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

DROP TABLE IF EXISTS `livre`;
CREATE TABLE IF NOT EXISTS `livre` (
  `année` int NOT NULL,
  `auteur` int NOT NULL,
  `collection` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `éditeur` int NOT NULL,
  `résumer` varchar(255) DEFAULT NULL,
  `titre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK797gishit1th7hqugsf4njqsh` (`auteur`),
  KEY `FKj1xtsdn4tnyotrhq5gapi4dh4` (`collection`),
  KEY `FKad6bp0s29nx5kvuvd87wh4no1` (`éditeur`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`année`, `auteur`, `collection`, `id`, `éditeur`, `résumer`, `titre`) VALUES
(1862, 1, 1, 1, 1, 'Roman social', 'Les Misérables'),
(1942, 2, 3, 2, 2, 'Roman philosophique', 'L’Étranger'),
(1997, 3, 2, 3, 3, 'Jeune sorcier', 'Harry Potter à l’école des sorciers'),
(1949, 4, 4, 4, 4, 'Dystopie totalitaire', '1984');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `login`, `password`) VALUES
(1, 'yohann', '$2a$10$WXzEVdTjhaFgvQ7kpN83MOi4b4U1RTbyrKI7BoKXeT31LH1qc.Oli'),
(2, 'ronan', '$2a$10$d.CEcpfjAlK0YJmxVmbuNO51dKKJHmIysFNn9hevTlO0cXyDSSfTi'),
(3, 'clea', '$2a$10$X5ImNxZwprCRFc8EVvWEROFpZvx7o.eW5/trAa/f8zMYtLVq5f1EO'),
(4, 'marie', '$2a$10$05jMIGLhuG1nM2568O46SOgjXFHEmLgiurd2JI7wyvPOZ1OSTOqcq');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `livre`
--
ALTER TABLE `livre`
  ADD CONSTRAINT `FK797gishit1th7hqugsf4njqsh` FOREIGN KEY (`auteur`) REFERENCES `auteur` (`id`),
  ADD CONSTRAINT `FKad6bp0s29nx5kvuvd87wh4no1` FOREIGN KEY (`éditeur`) REFERENCES `editeur` (`id`),
  ADD CONSTRAINT `FKj1xtsdn4tnyotrhq5gapi4dh4` FOREIGN KEY (`collection`) REFERENCES `collection` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
