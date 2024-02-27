-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Feb 14, 2023 at 10:55 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mycinemadb`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `Username` varchar(30) NOT NULL,
  `FirstName` varchar(25) NOT NULL,
  `LastName` varchar(25) NOT NULL,
  `Password` varchar(25) NOT NULL,
  `DoB` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


--
-- Stand-in structure for view `customersinfo`
-- (See below for the actual view)
--
CREATE TABLE `customersinfo` (
`Username` varchar(30)
,`Full_Name` varchar(51)
,`DoB` date
);

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `Employee_ID` varchar(10) NOT NULL,
  `FirstName` varchar(25) NOT NULL,
  `LastName` varchar(25) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Password` varchar(25) NOT NULL,
  `Role_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`Employee_ID`, `FirstName`, `LastName`, `Email`, `Password`, `Role_ID`) VALUES
('1234567890', 'Admin', 'Admin', 'admin@admin.com', '12345678', 1)

-- --------------------------------------------------------

--
-- Stand-in structure for view `employeesinfo`
-- (See below for the actual view)
--
CREATE TABLE `employeesinfo` (
`Employee_ID` varchar(10)
,`Full_Name` varchar(51)
,`Email` varchar(100)
,`Role_Title` varchar(25)
);

-- --------------------------------------------------------

--
-- Table structure for table `languages`
--

CREATE TABLE `languages` (
  `lang_ID` int(11) NOT NULL,
  `lang_name` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `languages`
--

INSERT INTO `languages` (`lang_ID`, `lang_name`) VALUES
(1, 'Arabic'),
(2, 'English'),
(3, 'Spanish'),
(5, 'Japanese'),
(6, 'Hindi'),
(7, 'Russian'),
(8, 'Turkish'),
(9, 'Korean'),
(10, 'French');

-- --------------------------------------------------------

--
-- Table structure for table `movies`
--

CREATE TABLE `movies` (
  `Movie_ID` int(11) NOT NULL,
  `Movie_Name` varchar(100) NOT NULL,
  `Language` int(3) DEFAULT NULL,
  `Runtime` int(3) NOT NULL,
  `Age_Rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `Role_ID` int(11) NOT NULL,
  `Role_Title` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`Role_ID`, `Role_Title`) VALUES
(1, 'Manager'),
(2, 'Cashier');

-- --------------------------------------------------------

--
-- Table structure for table `shows`
--

CREATE TABLE `shows` (
  `Show_ID` int(11) NOT NULL,
  `Movie_ID` int(11) NOT NULL,
  `Theater_Number` int(11) NOT NULL,
  `Start_Time` datetime DEFAULT NULL,
  `Price` float NOT NULL,
  `Available_Seats` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;



--
-- Stand-in structure for view `show_times`
-- (See below for the actual view)
--
CREATE TABLE `show_times` (
`Show_ID` int(11)
,`Movie_Name` varchar(100)
,`Movie_ID` int(11)
,`Theater_Number` int(4)
,`Theater_Type` varchar(25)
,`Start_Time` datetime
,`End_Time` datetime
,`Price` float
,`Available_Seats` int(11)
);

-- --------------------------------------------------------

--
-- Table structure for table `theaters`
--

CREATE TABLE `theaters` (
  `Theater_Number` int(4) NOT NULL,
  `Theater_Type` int(11) NOT NULL,
  `Number_of_Seats` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;



--
-- Table structure for table `theater_types`
--

CREATE TABLE `theater_types` (
  `Type_ID` int(11) NOT NULL,
  `Type_Name` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `theater_types`
--

INSERT INTO `theater_types` (`Type_ID`, `Type_Name`) VALUES
(1, 'STANDARD'),
(2, 'VIP'),
(3, 'IMAX');

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

CREATE TABLE `tickets` (
  `Ticket_ID` int(11) NOT NULL,
  `Customer_Username` varchar(30) NOT NULL,
  `Show_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--

--
-- Stand-in structure for view `ticketsinfo`
-- (See below for the actual view)
--
CREATE TABLE `ticketsinfo` (
`Ticket_ID` int(11)
,`Customer` varchar(30)
,`Show_ID` int(11)
,`Movie_Name` varchar(100)
,`Theater` varchar(39)
,`Start_Time` datetime
,`End_Time` datetime
);

-- --------------------------------------------------------

--
-- Structure for view `customersinfo`
--
DROP TABLE IF EXISTS `customersinfo`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `customersinfo`  AS SELECT `customers`.`Username` AS `Username`, concat(`customers`.`FirstName`,' ',`customers`.`LastName`) AS `Full_Name`, `customers`.`DoB` AS `DoB` FROM `customers``customers`  ;

-- --------------------------------------------------------

--
-- Structure for view `employeesinfo`
--
DROP TABLE IF EXISTS `employeesinfo`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `employeesinfo`  AS SELECT `employees`.`Employee_ID` AS `Employee_ID`, concat(`employees`.`FirstName`,' ',`employees`.`LastName`) AS `Full_Name`, `employees`.`Email` AS `Email`, `roles`.`Role_Title` AS `Role_Title` FROM (`employees` join `roles` on(`employees`.`Role_ID` = `roles`.`Role_ID`))  ;

-- --------------------------------------------------------

--
-- Structure for view `show_times`
--
DROP TABLE IF EXISTS `show_times`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `show_times`  AS SELECT `shows`.`Show_ID` AS `Show_ID`, `movies`.`Movie_Name` AS `Movie_Name`, `movies`.`Movie_ID` AS `Movie_ID`, `theaters`.`Theater_Number` AS `Theater_Number`, `theater_types`.`Type_Name` AS `Theater_Type`, `shows`.`Start_Time` AS `Start_Time`, `shows`.`Start_Time`+ interval `movies`.`Runtime` minute AS `End_Time`, `shows`.`Price` AS `Price`, `shows`.`Available_Seats` AS `Available_Seats` FROM (((`shows` join `movies` on(`shows`.`Movie_ID` = `movies`.`Movie_ID`)) join `theaters` on(`shows`.`Theater_Number` = `theaters`.`Theater_Number`)) join `theater_types` on(`theaters`.`Theater_Type` = `theater_types`.`Type_ID`))  ;

-- --------------------------------------------------------

--
-- Structure for view `ticketsinfo`
--
DROP TABLE IF EXISTS `ticketsinfo`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ticketsinfo`  AS SELECT `tickets`.`Ticket_ID` AS `Ticket_ID`, `customers`.`Username` AS `Customer`, `show_times`.`Show_ID` AS `Show_ID`, `show_times`.`Movie_Name` AS `Movie_Name`, concat(`show_times`.`Theater_Number`,' | ',`show_times`.`Theater_Type`) AS `Theater`, `show_times`.`Start_Time` AS `Start_Time`, `show_times`.`End_Time` AS `End_Time` FROM ((`tickets` join `show_times` on(`tickets`.`Show_ID` = `show_times`.`Show_ID`)) join `customers` on(`tickets`.`Customer_Username` = `customers`.`Username`))  ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`Username`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`Employee_ID`),
  ADD KEY `employees_ibfk_1` (`Role_ID`);

--
-- Indexes for table `languages`
--
ALTER TABLE `languages`
  ADD PRIMARY KEY (`lang_ID`);

--
-- Indexes for table `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`Movie_ID`),
  ADD KEY `movies_ibfk_1` (`Language`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`Role_ID`);

--
-- Indexes for table `shows`
--
ALTER TABLE `shows`
  ADD PRIMARY KEY (`Show_ID`),
  ADD KEY `shows_ibfk_1` (`Movie_ID`),
  ADD KEY `shows_ibfk_2` (`Theater_Number`);

--
-- Indexes for table `theaters`
--
ALTER TABLE `theaters`
  ADD PRIMARY KEY (`Theater_Number`),
  ADD KEY `theaters_ibfk_1` (`Theater_Type`);

--
-- Indexes for table `theater_types`
--
ALTER TABLE `theater_types`
  ADD PRIMARY KEY (`Type_ID`);

--
-- Indexes for table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`Ticket_ID`),
  ADD KEY `tickets_ibfk_2` (`Customer_Username`),
  ADD KEY `tickets_ibfk_1` (`Show_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `languages`
--
ALTER TABLE `languages`
  MODIFY `lang_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `movies`
--
ALTER TABLE `movies`
  MODIFY `Movie_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `Role_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `shows`
--
ALTER TABLE `shows`
  MODIFY `Show_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT for table `theater_types`
--
ALTER TABLE `theater_types`
  MODIFY `Type_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tickets`
--
ALTER TABLE `tickets`
  MODIFY `Ticket_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=96;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`Role_ID`) REFERENCES `roles` (`Role_ID`) ON UPDATE CASCADE;

--
-- Constraints for table `movies`
--
ALTER TABLE `movies`
  ADD CONSTRAINT `movies_ibfk_1` FOREIGN KEY (`Language`) REFERENCES `languages` (`lang_ID`) ON UPDATE CASCADE;

--
-- Constraints for table `shows`
--
ALTER TABLE `shows`
  ADD CONSTRAINT `shows_ibfk_1` FOREIGN KEY (`Movie_ID`) REFERENCES `movies` (`Movie_ID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `shows_ibfk_2` FOREIGN KEY (`Theater_Number`) REFERENCES `theaters` (`Theater_Number`) ON UPDATE CASCADE;

--
-- Constraints for table `theaters`
--
ALTER TABLE `theaters`
  ADD CONSTRAINT `theaters_ibfk_1` FOREIGN KEY (`Theater_Type`) REFERENCES `theater_types` (`Type_ID`) ON UPDATE CASCADE;

--
-- Constraints for table `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`Show_ID`) REFERENCES `shows` (`Show_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tickets_ibfk_2` FOREIGN KEY (`Customer_Username`) REFERENCES `customers` (`Username`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
