DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS board;
DROP TABLE IF EXISTS reply;

CREATE TABLE member (
    MEMBER_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    nickname VARCHAR(255) NOT NULL,
    gender VARCHAR(255) NOT NULL,
    height DOUBLE NOT NULL,
    weight DOUBLE NOT NULL,
    age INT NOT NULL,
    dietKcal DOUBLE,
    maintainKcal DOUBLE,
    massUpKcal DOUBLE,
    bulkUpKcal DOUBLE
);

CREATE TABLE board (
    BOARD_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    viewCount INT,
    member_id BIGINT,
    FOREIGN KEY (member_id) REFERENCES Member(MEMBER_ID)
        ON DELETE CASCADE
);

CREATE TABLE reply (
    REPLY_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    reply TEXT NOT NULL,
    board_id BIGINT,
    member_id BIGINT,
    FOREIGN KEY (board_id) REFERENCES Board(BOARD_ID)
        ON DELETE CASCADE,
    FOREIGN KEY (member_id) REFERENCES Member(MEMBER_ID)
        ON DELETE CASCADE
);