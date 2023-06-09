-- 테스트용 table
CREATE TABLE NOTICE (
                        ID INT AUTO_INCREMENT,
                        TITLE NVARCHAR(100),
                        WRITER_ID NVARCHAR(50),
                        CONTENT LONGTEXT,
                        REGDATE TIMESTAMP,
                        HIT INT,
                        FILES NVARCHAR(1000),
                        PRIMARY KEY (ID)
);


CREATE TABLE COMMENT (
                         ID NUMBER,
                         CONTENT NVARCHAR(2000),
                         REGDATE DATETIME,
                         WRITER_ID NVARCHAR(50),
                         NOTICE_ID NUMBER
);

CREATE TABLE ROLE (
                      ID VARCHAR2(50),
                      DESCRIPTION NVARCHAR(500)
);

CREATE TABLE MEMBER_ROLE (
                             MEMBER_ID NVARCHAR(50),
                             ROLE_ID VARCHAR(50)
);

CREATE TABLE MEMBER
(
    ID NVARCHAR(50),
    PWD NVARCHAR(50),
    NAME (50),
    GENDER NCHAR(2),
    BIRTHDAY CHAR(10),
    PHONE CHAR(13),
    REGDATE DATE,
    EMAIL VARCHAR(200)
);

-- 멤버
INSERT INTO MEMBER (ID, PWD) VALUES ('user1', '1111');
INSERT INTO MEMBER (ID, PWD) VALUES ('user2', '2222');

-- 댓글
INSERT INTO COMMENT (ID, CONTENT, REGDATE, WRITER_ID, NOTICE_ID)
VALUES (1, 'Comment content', '2023-06-02 10:00:00', 'user2', 56);

INSERT INTO COMMENT (ID, CONTENT, REGDATE, WRITER_ID, NOTICE_ID)
VALUES
    (2, '야미야미', '2023-06-01 10:00:00', 'user1', 55),
    (3, '슷고이', '2023-03-01 10:00:00', 'user2', 55),
    (4, '허니달달', '2023-05-01 10:00:00', 'user1', 54);

-- 더미데이터
INSERT INTO NOTICE (TITLE, WRITER_ID, CONTENT, REGDATE, HIT, FILES)
VALUES
    ('첫 번째 공지', 'writer1', '첫 번째 공지 내용입니다.', '2023-05-30 10:00:00', 10, ''),
    ('두 번째 공지', 'writer2', '두 번째 공지 내용입니다.', '2023-05-30 11:00:00', 5, ''),
    ('세 번째 공지', 'writer1', '세 번째 공지 내용입니다.', '2023-05-30 12:00:00', 15, ''),
    ('네 번째 공지', 'writer3', '네 번째 공지 내용입니다.', '2023-05-30 13:00:00', 8, ''),
    ('다섯 번째 공지', 'writer2', '다섯 번째 공지 내용입니다.', '2023-05-30 14:00:00', 12, '');

INSERT INTO NOTICE (TITLE, WRITER_ID, CONTENT, REGDATE, HIT, FILES)
SELECT
    CONCAT('더미 제목 ', num),
    CONCAT('writer', num),
    CONCAT('더미 내용 ', num),
    NOW(),
    FLOOR(RAND() * 50) + 1,
    CONCAT('더미 파일 ', num)
FROM
    (SELECT 1 AS num UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5
     UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10
     UNION SELECT 11 UNION SELECT 12 UNION SELECT 13 UNION SELECT 14 UNION SELECT 15
     UNION SELECT 16 UNION SELECT 17 UNION SELECT 18 UNION SELECT 19 UNION SELECT 20
     UNION SELECT 21 UNION SELECT 22 UNION SELECT 23 UNION SELECT 24 UNION SELECT 25
     UNION SELECT 26 UNION SELECT 27 UNION SELECT 28 UNION SELECT 29 UNION SELECT 30
     UNION SELECT 31 UNION SELECT 32 UNION SELECT 33 UNION SELECT 34 UNION SELECT 35
     UNION SELECT 36 UNION SELECT 37 UNION SELECT 38 UNION SELECT 39 UNION SELECT 40
     UNION SELECT 41 UNION SELECT 42 UNION SELECT 43 UNION SELECT 44 UNION SELECT 45
     UNION SELECT 46 UNION SELECT 47 UNION SELECT 48 UNION SELECT 49 UNION SELECT 50
    ) AS dummy;

UPDATE NOTICE
SET FILES = CONCAT(FILES, 'test.zip')
WHERE TITLE = '첫 번째 공지';


CREATE TABLE NOTICE (
                        ID INT AUTO_INCREMENT,
                        TITLE NVARCHAR(100),
                        WRITER_ID NVARCHAR(50),
                        CONTENT LONGTEXT,
                        REGDATE TIMESTAMP,
                        HIT INT,
                        FILES NVARCHAR(1000),
                        PUB int DEFAULT 0,
                        PRIMARY KEY (ID)
);