package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class mockExamDao {

    public boolean insertMockExamResult(
            Timestamp startTime,
            Timestamp endTime,
            int totalQuestions,
            int answeredCount,
            int correctCount,
            int incorrectCount,
            int unansweredCount
    ) {
        String sql = "INSERT INTO mock_exam_results " +
                "(start_time, end_time, total_questions, answered_count, correct_count, incorrect_count, unanswered_count) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setTimestamp(1, startTime);
            pstmt.setTimestamp(2, endTime);
            pstmt.setInt(3, totalQuestions);
            pstmt.setInt(4, answeredCount);
            pstmt.setInt(5, correctCount);
            pstmt.setInt(6, incorrectCount);
            pstmt.setInt(7, unansweredCount);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
