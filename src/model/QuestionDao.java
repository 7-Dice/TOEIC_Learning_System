package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        String sql = "SELECT * FROM questions";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                long questionId = rs.getLong("question_id");
                Long passageId = (Long) rs.getObject("passage_id");
                int partType = rs.getInt("part_type");
                String questionText = rs.getString("question_text");
                String optionA = rs.getString("option_a");
                String optionB = rs.getString("option_b");
                String optionC = rs.getString("option_c");
                String optionD = rs.getString("option_d");
                String correctAnswer = rs.getString("correct_answer");
                String explanation = rs.getString("explanation");

                Question question = new Question(
                        questionId, passageId, partType, questionText,
                        optionA, optionB, optionC, optionD, correctAnswer, explanation
                );
                questionList.add(question);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questionList;
    }

    public List<Question> getQuestionsByPart(int partType) {
        List<Question> questionList = new ArrayList<>();
        String sql = "SELECT * FROM questions WHERE part_type = ? ORDER BY question_id";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, partType);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    long questionId = rs.getLong("question_id");
                    Long passageId = (Long) rs.getObject("passage_id");
                    int pt = rs.getInt("part_type");
                    String questionText = rs.getString("question_text");
                    String optionA = rs.getString("option_a");
                    String optionB = rs.getString("option_b");
                    String optionC = rs.getString("option_c");
                    String optionD = rs.getString("option_d");
                    String correctAnswer = rs.getString("correct_answer");
                    String explanation = rs.getString("explanation");

                    questionList.add(new Question(
                            questionId, passageId, pt, questionText,
                            optionA, optionB, optionC, optionD,
                            correctAnswer, explanation
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questionList;
    }

    public List<Question> getQuestionsForPractice(int partType, boolean prioritizeIncorrect) {
        List<Question> questionList = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT q.* FROM questions q ");

        if (prioritizeIncorrect) {
            sql.append("LEFT JOIN (");
            sql.append("  SELECT question_id, is_correct, ");
            sql.append("         ROW_NUMBER() OVER(PARTITION BY question_id ORDER BY answered_at DESC) as rn ");
            sql.append("  FROM learning_history");
            sql.append(") latest_h ON q.question_id = latest_h.question_id AND latest_h.rn = 1 ");
        }

        boolean hasWhere = false;
        if (partType > 0) {
            sql.append("WHERE q.part_type = ? ");
            hasWhere = true;
        }

        if (prioritizeIncorrect) {
            sql.append(hasWhere ? "AND " : "WHERE ");
            sql.append("(latest_h.is_correct IS NULL OR latest_h.is_correct = 0) ");
            sql.append("ORDER BY (latest_h.is_correct IS NULL) DESC, latest_h.is_correct ASC, q.question_id ASC");
        } else {
            sql.append("ORDER BY q.question_id ASC");
        }

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            if (partType > 0) {
                pstmt.setInt(1, partType);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    long questionId = rs.getLong("question_id");
                    Long passageId = (Long) rs.getObject("passage_id");
                    int pt = rs.getInt("part_type");
                    String questionText = rs.getString("question_text");
                    String optionA = rs.getString("option_a");
                    String optionB = rs.getString("option_b");
                    String optionC = rs.getString("option_c");
                    String optionD = rs.getString("option_d");
                    String correctAnswer = rs.getString("correct_answer");
                    String explanation = rs.getString("explanation");

                    questionList.add(new Question(
                            questionId, passageId, pt, questionText,
                            optionA, optionB, optionC, optionD,
                            correctAnswer, explanation
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questionList;
    }
}
