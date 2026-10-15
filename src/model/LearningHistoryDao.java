package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LearningHistoryDao 
{
	public boolean insertHistory(long questionId, boolean isCorrect, String userAnswer) {
        String sql = "INSERT INTO learning_history (question_id, is_correct, user_answer) VALUES (?, ?, ?)";

        try(Connection conn = DBManager.getConnection();
        	PreparedStatement pstmt = conn.prepareStatement(sql)){
        	pstmt.setLong(1, questionId);
        	pstmt.setBoolean(2, isCorrect);
        	pstmt.setString(3, userAnswer);
        	
        	int affectedRows = pstmt.executeUpdate();
        	return affectedRows > 0;
        	
        }catch(SQLException e) {
        	e.printStackTrace();
        	return false; // ← ここを追加！
        }
	}
}
