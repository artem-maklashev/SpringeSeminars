CREATE TABLE IF NOT EXISTS userTable (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     description varchar(150) NOT NULL,
     task_status varchar(10) NOT NULL,
     creation_date DATETIME
);