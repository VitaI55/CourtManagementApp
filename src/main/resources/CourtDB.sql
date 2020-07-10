CREATE DATABASE db;
USE db;
CREATE TABLE judges
(
    id          bigint auto_increment not null,
    name        varchar(120)          not null,
    surname     varchar(120)          not null,
    email       varchar(100)          not null,
    phoneNumber int                   not null,
    CONSTRAINT pk_J_id PRIMARY KEY (id)
);
INSERT INTO judges (name, surname, email, phoneNumber)
VALUES ('Yurii', 'Lkymych', 'user1@user.com', 0508480905);
INSERT INTO judges (name, surname, email, phoneNumber)
VALUES ('Vovan', 'Fran', 'user2@user.com', 0502859634);
INSERT INTO judges (name, surname, email, phoneNumber)
VALUES ('Duke', 'Nukem', 'user6@user.com', 0683748947);
INSERT INTO judges (name, surname, email, phoneNumber)
VALUES ('Puka', 'Monat', 'user8@user.com', 0694447589);

CREATE TABLE cases
(
    id          bigint auto_increment not null,
    caseType    varchar(50)           not null,
    level       varchar(50)           not null,
    description varchar(200),
    judgeId     bigint                not null,
    CONSTRAINT pk_C_id PRIMARY KEY (id),
    CONSTRAINT fk_JI_judges FOREIGN KEY (judgeId) REFERENCES judges (id)
);


