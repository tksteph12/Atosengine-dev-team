-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Ven 04 Avril 2014 à 12:40
-- Version du serveur: 5.6.12-log
-- Version de PHP: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `ressources`
--
CREATE DATABASE IF NOT EXISTS `ressources` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `ressources`;

-- --------------------------------------------------------

--
-- Structure de la table `gcm`
--

CREATE TABLE IF NOT EXISTS `gcm` (
  `id_gcm` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(4) NOT NULL,
  `libelle_gcm` varchar(50) NOT NULL,
  `niveau_min` int(11) NOT NULL DEFAULT '2',
  `niveau_max` int(11) NOT NULL DEFAULT '8',
  PRIMARY KEY (`id_gcm`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `gcm`
--

INSERT INTO `gcm` (`id_gcm`, `code`, `libelle_gcm`, `niveau_min`, `niveau_max`) VALUES
(1, 'AD', 'Application Developer', 2, 8),
(2, 'ARC', 'Architect', 5, 9),
(3, 'BMC', 'Business Manager', 2, 8);

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

CREATE TABLE IF NOT EXISTS `personne` (
  `code_das` varchar(7) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `code_gcm` varchar(3) NOT NULL,
  `niveau` int(2) NOT NULL DEFAULT '2',
  PRIMARY KEY (`code_das`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `personne`
--

INSERT INTO `personne` (`code_das`, `nom`, `prenom`, `code_gcm`, `niveau`) VALUES
('A573405', 'TATCHUM', 'Stephane', 'AD', 2),
('A576493', 'BOUCHAIB', 'Farid', 'AD', 2);

-- --------------------------------------------------------

--
-- Structure de la table `rr`
--

CREATE TABLE IF NOT EXISTS `rr` (
  `id_rr` int(6) NOT NULL,
  `nom_rr` varchar(30) NOT NULL,
  `role` varchar(255) NOT NULL,
  `demandeur_rr` varchar(8) NOT NULL,
  `equipe_rm` varchar(8) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `competence_rr` varchar(120) NOT NULL,
  `gcm_rr` varchar(4) NOT NULL,
  `adresse` varchar(120) NOT NULL,
  `ville` varchar(20) NOT NULL,
  `niveau_min` int(2) NOT NULL DEFAULT '2',
  `niveau_max` int(2) NOT NULL DEFAULT '8',
  PRIMARY KEY (`id_rr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `rr`
--

INSERT INTO `rr` (`id_rr`, `nom_rr`, `role`, `demandeur_rr`, `equipe_rm`, `date_debut`, `date_fin`, `competence_rr`, `gcm_rr`, `adresse`, `ville`, `niveau_min`, `niveau_max`) VALUES
(25278, 'FR_TS_OUEST_1Testeur', 'Chef de projet de déploiement ; rédaction de spécifications fonctionnelles ; développement ; dans la gestion d’un projet de bout en bout (de l’étude amont jusqu’à la mise en production) ; habite en Normandie\r\n', 'A573405', ' FR22818', '2001-06-14', '2031-12-14', '', 'AD', 'Route des Alizés\r\n', 'Sandouville', 2, 8),
(26962, 'FR_SI_1_SM3_TRA_MISTRAL_IdF', '"Mission:\r\n#	Ingénieur déploiement matériels.\r\n#	Charge : 100%.\r\n#	Déplacements ponctuels en RP et province.\r\n#	Mission basée à Bezons.\r\n#	Télétravail : A étudier après période de formation\r\nDescription:\r\n#	Rédaction documentaire: (manuels, CR techniques,', 'A576493', 'A205304', '2001-04-14', '2031-12-15', '" - Products\r\nWindows 2000 Professional-Medior, Windows 7-Medior, Windows XP-Medior, TCP/IP IP-Medior, Windows Scripting', 'BMC', '', 'Bezons', 2, 8);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
