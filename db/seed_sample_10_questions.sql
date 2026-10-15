USE toeic_learning_system;

START TRANSACTION;

-- ======= passages (Part6 / Part7) =======
-- 既存データに重複しないよう、ID を明示的に指定して投入する
INSERT INTO passages (passage_id, part_type, passage_body) VALUES
(1001, 6, 'Part6 Passage 1: The office will be closed next Monday due to building maintenance. Please plan accordingly.'),
(1002, 6, 'Part6 Passage 2: Our new policy regarding remote work requires employees to submit requests two weeks in advance.'),
(2001, 7, 'Part7 Passage 1: Company newsletter: This month we launched a new customer portal to improve support.'),
(2002, 7, 'Part7 Passage 2: Event announcement: The conference will feature panels on technology and education.');

-- ======= questions: 10問サンプル =======
-- Part5: 5問（passage_id = NULL）
INSERT INTO questions (passage_id, part_type, question_text, option_a, option_b, option_c, option_d, correct_answer, explanation) VALUES
(NULL, 5, 'Q1: Please _____ the form by Friday.', 'A. submit', 'B. submitted', 'C. submitting', 'D. to submit', 'A', '動詞の原形を用いる: submit が正しい。'),
(NULL, 5, 'Q2: The meeting _____ at 10:00 AM sharp.', 'A. start', 'B. starts', 'C. started', 'D. starting', 'B', '主語が三人称単数なので starts を用いる。'),
(NULL, 5, 'Q3: She is responsible _____ the marketing budget.', 'A. on', 'B. for', 'C. to', 'D. with', 'B', 'responsible の後は for が続く。'),
(NULL, 5, 'Q4: The report was completed _____ the deadline.', 'A. in', 'B. at', 'C. before', 'D. during', 'C', '期限前は before を使う。'),
(NULL, 5, 'Q5: We would like to _____ you to the team meeting tomorrow.', 'A. invite', 'B. invited', 'C. inviting', 'D. to invite', 'A', 'would like to の後は動詞の原形。'),

-- Part6: 2問（passage_id -> 1001 / 1002）
(1001, 6, 'Q6: Why will the office be closed next Monday?', 'A. For maintenance', 'B. For a meeting', 'C. For a holiday', 'D. For training', 'A', '本文に building maintenance とあるため。'),
(1002, 6, 'Q7: How far in advance must employees submit remote work requests?', 'A. One day', 'B. One week', 'C. Two weeks', 'D. One month', 'C', '本文に two weeks と明記されているため。'),

-- Part7: 3問（passage_id -> 2001 / 2002）
(2001, 7, 'Q8: What was launched this month according to the newsletter?', 'A. A new product', 'B. A new portal', 'C. A training program', 'D. A new office', 'B', '本文に customer portal をローンチしたとあるため。'),
(2002, 7, 'Q9: What is the main theme of the conference?', 'A. Health care', 'B. Finance', 'C. Technology and education', 'D. Marketing', 'C', '本文に panels on technology and education とあるため。'),
(2001, 7, 'Q10: The customer portal aims to improve which area?', 'A. Sales', 'B. Support', 'C. Recruitment', 'D. Logistics', 'B', '本文に improve support とあるため。');

COMMIT;

-- 必要なら、AUTO_INCREMENT の値を調整
-- ALTER TABLE passages AUTO_INCREMENT = 1003;
-- ALTER TABLE questions AUTO_INCREMENT = 11;
