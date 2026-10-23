package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LearningHistoryDao {
    public boolean insertHistory(long questionId, boolean isCorrect, String userAnswer, int answerTimeSeconds) {
        String sql = "INSERT INTO learning_history (question_id, user_answer, is_correct, answer_time_seconds) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, questionId);
            pstmt.setString(2, userAnswer);
            pstmt.setBoolean(3, isCorrect);
            pstmt.setInt(4, answerTimeSeconds);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
