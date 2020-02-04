-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 09, 2019 at 06:14 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `document`
--
CREATE DATABASE IF NOT EXISTS `document` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `document`;

-- --------------------------------------------------------

--
-- Table structure for table `bookmark`
--

CREATE TABLE `bookmark` (
  `pdfName` varchar(100) NOT NULL,
  `pdfPath` varchar(300) NOT NULL,
  `pdfPage` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bookmark`
--

INSERT INTO `bookmark` (`pdfName`, `pdfPath`, `pdfPage`) VALUES
('aaaa', '/home/wajahat/IdeaProjects/pdfReaderProject/MergeResult.pdf', '3'),
('asdf', '/home/wajahat/IdeaProjects/pdfReaderProject/MergeResult.pdf', '4'),
('asdfg', '/home/wajahat/IdeaProjects/pdfReaderProject/MergeResult.pdf', '4'),
('assignment', '/home/wajahat/IdeaProjects/pdfReaderProject/Assignment 01-FALL 2019.pdf', '1'),
('bio', '/home/wajahat/IdeaProjects/pdfReaderProject/MergeResult.pdf', '1'),
('bioPAge', '/home/wajahat/IdeaProjects/pdfReaderProject/MergeResult.pdf', '4'),
('SecondPage', '/home/wajahat/IdeaProjects/pdfReaderProject/Assignment 01-FALL 2019.pdf', '2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bookmark`
--
ALTER TABLE `bookmark`
  ADD UNIQUE KEY `pdf` (`pdfName`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
