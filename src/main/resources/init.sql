DROP DATABASE IF EXISTS `facultative`;

CREATE DATABASE `facultative` DEFAULT CHARACTER SET utf8;

USE `facultative`;

CREATE TABLE `teachers` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `students` (
     `id` INTEGER NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `registered_users` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `role` VARCHAR(20) NOT NULL,
    `student_id` INT,
    `teacher_id` INT,
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `courses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `teacher_id` INT NOT NULL,
  `course_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
  ) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

  CREATE TABLE `courses_students` (
  `course_id` INT NOT NULL,
  `student_id` INT NOT NULL,
  `raiting` INT,
  `resume` VARCHAR(1000),
  PRIMARY KEY (`course_id`, `student_id`)
  )ENGINE=INNODB DEFAULT CHARACTER SET utf8;

ALTER TABLE `courses`
ADD CONSTRAINT `fk_course_teacher`
  FOREIGN KEY (`teacher_id`)
  REFERENCES `facultative`.`teachers` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `facultative`.`courses_students`
ADD CONSTRAINT `courses_student_courses`
  FOREIGN KEY (`course_id`)
  REFERENCES `facultative`.`courses` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_courses_students_students`
  FOREIGN KEY (`student_id`)
  REFERENCES `facultative`.`students` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;


ALTER TABLE `facultative`.`registered_users`
ADD CONSTRAINT `fk_registered_users_teachers`
  FOREIGN KEY (`teacher_id`)
  REFERENCES `facultative`.`teachers` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_registered_users_students`
  FOREIGN KEY (`student_id`)
  REFERENCES `facultative`.`students` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;
