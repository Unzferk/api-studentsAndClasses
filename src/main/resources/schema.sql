--
-- Table structure for table `course`
--
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `uid` char(36) NOT NULL,
  `code` varchar(45) NOT NULL,
  `title` varchar(45) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `active` tinyint DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `course_student`
--
DROP TABLE IF EXISTS `course_student`;
CREATE TABLE `course_student` (
  `course_uid` char(36) NOT NULL,
  `student_uid` char(36) NOT NULL,
  PRIMARY KEY (`course_uid`,`student_uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `student`
--
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `uid` char(36) NOT NULL,
  `student_id` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `active` tinyint DEFAULT '1',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `userId_UNIQUE` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
