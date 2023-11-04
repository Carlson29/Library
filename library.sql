-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 04, 2023 at 09:17 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `bookId` int(11) NOT NULL,
  `genreId` int(11) NOT NULL,
  `title` varchar(24) NOT NULL,
  `author` varchar(24) NOT NULL,
  `numberOfCopies` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`bookId`, `genreId`, `title`, `author`, `numberOfCopies`) VALUES
(1, 1, 'Battle Zone', 'Jacob smith', 40),
(2, 2, 'Before dawn', 'Paul mcKevin', 10),
(3, 3, 'Harry potter', 'Voldermott', 5);

-- --------------------------------------------------------

--
-- Table structure for table `genres`
--

CREATE TABLE `genres` (
  `genreId` int(11) NOT NULL,
  `genreName` varchar(24) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `genres`
--

INSERT INTO `genres` (`genreId`, `genreName`) VALUES
(1, 'horror'),
(2, 'fiction'),
(3, 'magic');

-- --------------------------------------------------------

--
-- Table structure for table `loanfee`
--

CREATE TABLE `loanfee` (
  `loanId` int(11) NOT NULL,
  `loanFee` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `loans`
--

CREATE TABLE `loans` (
  `loanId` int(11) NOT NULL,
  `bookId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `dateOfLoan` date NOT NULL,
  `dueDate` date NOT NULL,
  `returnDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `loans`
--

INSERT INTO `loans` (`loanId`, `bookId`, `userId`, `dateOfLoan`, `dueDate`, `returnDate`) VALUES
(1, 1, 1, '2023-10-16', '2023-10-20', NULL),
(2, 2, 2, '2023-10-15', '2023-11-01', NULL),
(3, 3, 3, '2023-10-14', '2023-10-17', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userId` int(6) NOT NULL,
  `userName` varchar(24) NOT NULL,
  `password` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `phoneNumber` varchar(24) NOT NULL,
  `userType` int(11) NOT NULL DEFAULT 1,
  `disable` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userId`, `userName`, `password`, `email`, `phoneNumber`, `userType`, `disable`) VALUES
(1, 'Carlson', 'carl', 'carl@gmail.com', '0895666431', 1, 1),
(2, 'Samuel', 'sam', 'sam@gmail.com', '0895666581', 2, 1),
(3, 'Tom', 'tom', 'tom@gmail.com', '0892966581', 1, 2),
(46, 'Naomi', 'nao', 'naomi@gmail.com', '895576248', 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`bookId`),
  ADD KEY `fk_genreId` (`genreId`);

--
-- Indexes for table `genres`
--
ALTER TABLE `genres`
  ADD PRIMARY KEY (`genreId`);

--
-- Indexes for table `loanfee`
--
ALTER TABLE `loanfee`
  ADD KEY `fk_loanId` (`loanId`);

--
-- Indexes for table `loans`
--
ALTER TABLE `loans`
  ADD PRIMARY KEY (`loanId`),
  ADD KEY `fk_userId` (`userId`),
  ADD KEY `fk_bookId` (`bookId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `bookId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `genres`
--
ALTER TABLE `genres`
  MODIFY `genreId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `loans`
--
ALTER TABLE `loans`
  MODIFY `loanId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userId` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `books`
--
ALTER TABLE `books`
  ADD CONSTRAINT `fk_genreId` FOREIGN KEY (`genreId`) REFERENCES `genres` (`genreId`);

--
-- Constraints for table `loanfee`
--
ALTER TABLE `loanfee`
  ADD CONSTRAINT `fk_loanId` FOREIGN KEY (`loanId`) REFERENCES `loans` (`loanId`);

--
-- Constraints for table `loans`
--
ALTER TABLE `loans`
  ADD CONSTRAINT `fk_bookId` FOREIGN KEY (`bookId`) REFERENCES `books` (`bookId`),
  ADD CONSTRAINT `fk_userId` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
