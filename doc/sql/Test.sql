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

-- 더미데이터
INSERT INTO NOTICE (TITLE, WRITER_ID, CONTENT, REGDATE, HIT, FILES)
VALUES
    ('첫 번째 공지', 'writer1', '첫 번째 공지 내용입니다.', '2023-05-30 10:00:00', 10, ''),
    ('두 번째 공지', 'writer2', '두 번째 공지 내용입니다.', '2023-05-30 11:00:00', 5, ''),
    ('세 번째 공지', 'writer1', '세 번째 공지 내용입니다.', '2023-05-30 12:00:00', 15, ''),
    ('네 번째 공지', 'writer3', '네 번째 공지 내용입니다.', '2023-05-30 13:00:00', 8, ''),
    ('다섯 번째 공지', 'writer2', '다섯 번째 공지 내용입니다.', '2023-05-30 14:00:00', 12, '');

UPDATE NOTICE
SET FILES = CONCAT(FILES, 'test.zip')
WHERE TITLE = '첫 번째 공지';