CREATE TABLE myusers(
    userId varchar(5) PRIMARY KEY,
    fullName varchar(30),
    email varchar(50) UNIQUE,
    phone bigint,
    loginId varchar(15) UNIQUE,
    loginPassword VARCHAR(255) NOT NULL,
    ROLE varchar(20));

INSERT INTO myusers VALUES ('U-001', 'Manish Ranjan', 'manish@tbaba.com',1111111111,'manish','$2a$10$Ru10DuV/alx9NB0sSCXiNeCAXnwunI1NLJIUYEoLmYj7cYJnlM8.S','ROLE_ADMIN');
-- manish / manish@1


CREATE TABLE mybooks(
    bookId varchar(5) PRIMARY KEY,
    bookName varchar(30)
);

