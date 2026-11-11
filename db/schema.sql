CREATE DATABASE IF NOT EXISTS toeic_learning_system;
USE toeic_learning_system;

-- 既存の legacy table は残すが、新実装では主役は以下
CREATE TABLE IF NOT EXISTS passage_groups (
    group_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    part_type INT NOT NULL,
    group_type VARCHAR(20) NOT NULL, -- INDEPENDENT, SINGLE, DOUBLE, TRIPLE
    title VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS passage_group_items (
    item_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    group_id BIGINT NOT NULL,
    sequence_no INT NOT NULL,          -- 1, 2, 3 の順番
    speaker VARCHAR(100),              -- James, Anna, Manager など
    text_body TEXT NOT NULL,
    FOREIGN KEY (group_id) REFERENCES passage_groups(group_id)
);

CREATE TABLE IF NOT EXISTS questions (
    question_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    group_id BIGINT NULL,              -- NULL の場合は独立問題
    sequence_no INT NULL,
    part_type INT NOT NULL,
    question_text TEXT NOT NULL,
    option_a VARCHAR(255) NOT NULL,
    option_b VARCHAR(255) NOT NULL,
    option_c VARCHAR(255) NOT NULL,
    option_d VARCHAR(255) NOT NULL,
    correct_answer CHAR(1) NOT NULL,
    explanation TEXT,
    FOREIGN KEY (group_id) REFERENCES passage_groups(group_id)
);

-- 既存のテーブルを残す
CREATE TABLE IF NOT EXISTS learning_history (
    history_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    question_id BIGINT NOT NULL,
    user_answer CHAR(1) NOT NULL,
    is_correct BOOLEAN DEFAULT NULL,
    answered_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    answer_time_seconds INT,
    FOREIGN KEY (question_id) REFERENCES questions(question_id)
);

CREATE TABLE IF NOT EXISTS mock_exam_results (
    mock_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    total_questions INT NOT NULL DEFAULT 100,
    answered_count INT NOT NULL,
    correct_count INT NOT NULL,
    incorrect_count INT NOT NULL,
    unanswered_count INT NOT NULL
);
