package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {

    public List<Question> getQuestionsByPart(int partType) {
        List<Question> list = new ArrayList<>();
        String sql = """
                SELECT question_id,
                       group_id,
                       sequence_no,
                       part_type,
                       question_text,
                       option_a,
                       option_b,
                       option_c,
                       option_d,
                       correct_answer,
                       explanation
                  FROM questions
                 WHERE part_type = ?
              ORDER BY group_id, sequence_no, question_id
                """;

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, partType);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Question(
                            rs.getLong("question_id"),
                            rs.getObject("group_id") == null ? null : rs.getLong("group_id"),
                            rs.getObject("sequence_no") == null ? null : rs.getInt("sequence_no"),
                            rs.getInt("part_type"),
                            rs.getString("question_text"),
                            rs.getString("option_a"),
                            rs.getString("option_b"),
                            rs.getString("option_c"),
                            rs.getString("option_d"),
                            rs.getString("correct_answer"),
                            rs.getString("explanation")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Question getQuestionById(long questionId) {
        String sql = """
            SELECT question_id,
                   group_id,
                   sequence_no,
                   part_type,
                   question_text,
                   option_a,
                   option_b,
                   option_c,
                   option_d,
                   correct_answer,
                   explanation
              FROM questions
             WHERE question_id = ?
            """;

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, questionId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Question(
                            rs.getLong("question_id"),
                            rs.getObject("group_id") == null ? null : rs.getLong("group_id"),
                            rs.getObject("sequence_no") == null ? null : rs.getInt("sequence_no"),
                            rs.getInt("part_type"),
                            rs.getString("question_text"),
                            rs.getString("option_a"),
                            rs.getString("option_b"),
                            rs.getString("option_c"),
                            rs.getString("option_d"),
                            rs.getString("correct_answer"),
                            rs.getString("explanation")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Question> getQuestionsForPractice(int partType, boolean prioritizeIncorrect) {
        List<Question> questionList = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT q.question_id, q.group_id, q.sequence_no, q.part_type, q.question_text, ");
        sql.append("       q.option_a, q.option_b, q.option_c, q.option_d, ");
        sql.append("       q.correct_answer, q.explanation ");
        sql.append("FROM questions q ");

        if (prioritizeIncorrect) {
            sql.append("LEFT JOIN ( ");
            sql.append("    SELECT question_id, ");
            sql.append("           MAX(CASE WHEN is_correct = TRUE THEN 1 ELSE 0 END) AS has_correct ");
            sql.append("    FROM learning_history ");
            sql.append("    GROUP BY question_id ");
            sql.append(") h ON h.question_id = q.question_id ");
        }

        boolean hasWhere = false;
        if (partType > 0) {
            sql.append("WHERE q.part_type = ? ");
            hasWhere = true;
        }

        if (prioritizeIncorrect) {
            sql.append(hasWhere ? "AND " : "WHERE ");
            sql.append("(h.has_correct IS NULL OR h.has_correct = 0) ");
            sql.append("ORDER BY CASE WHEN h.has_correct IS NULL THEN 0 ELSE 1 END, q.group_id, q.sequence_no, q.question_id");
        } else {
            sql.append("ORDER BY q.group_id, q.sequence_no, q.question_id");
        }

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            if (partType > 0) {
                pstmt.setInt(1, partType);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    questionList.add(new Question(
                            rs.getLong("question_id"),
                            rs.getObject("group_id") == null ? null : rs.getLong("group_id"),
                            rs.getObject("sequence_no") == null ? null : rs.getInt("sequence_no"),
                            rs.getInt("part_type"),
                            rs.getString("question_text"),
                            rs.getString("option_a"),
                            rs.getString("option_b"),
                            rs.getString("option_c"),
                            rs.getString("option_d"),
                            rs.getString("correct_answer"),
                            rs.getString("explanation")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questionList;
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        String sql =
                "SELECT question_id, group_id, sequence_no, part_type, question_text, " +
                        "option_a, option_b, option_c, option_d, " +
                        "correct_answer, explanation " +
                        "FROM questions " +
                        "ORDER BY group_id, sequence_no, question_id";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                questionList.add(new Question(
                        rs.getLong("question_id"),
                        rs.getObject("group_id") == null ? null : rs.getLong("group_id"),
                        rs.getObject("sequence_no") == null ? null : rs.getInt("sequence_no"),
                        rs.getInt("part_type"),
                        rs.getString("question_text"),
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("option_d"),
                        rs.getString("correct_answer"),
                        rs.getString("explanation")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questionList;
    }
}
