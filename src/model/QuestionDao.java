package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {

    // すべての設問を取得するメソッド
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        String sql = "SELECT * FROM questions";

        // DBManagerを使って安全にコネクションを取得・クローズする
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                long questionId = rs.getLong("question_id");
                
                // passage_idはNULL許容のため、getObjectで取得してLongにキャスト
                Long passageId = (Long) rs.getObject("passage_id");
                
                int partType = rs.getInt("part_type");
                String questionText = rs.getString("question_text");
                String optionA = rs.getString("option_a");
                String optionB = rs.getString("option_b");
                String optionC = rs.getString("option_c");
                String optionD = rs.getString("option_d");
                String correctAnswer = rs.getString("correct_answer");
                String explanation = rs.getString("explanation");

                // Lombokの @AllArgsConstructor で生成されたコンストラクタを活用
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
    
    // パート指定や誤答優先を考慮して問題を取得するメソッド
    // partType: 0の場合は全パート対象、それ以外は指定Partのみ
    // onlyIncorrect: trueの場合は間違えたことがある問題・未解答を優先（または抽出）
    public List<Question> getQuestionsForPractice(int partType, boolean prioritizeIncorrect) {
        List<Question> questionList = new ArrayList<>();
        
        // 基本のSQL構築
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT q.* FROM questions q ");
        
        if (prioritizeIncorrect) {
            // 各問題の最新の正誤状態を取得するサブクエリとLEFT JOIN
            sql.append("LEFT JOIN (");
            sql.append("  SELECT question_id, is_correct, ");
            sql.append("         ROW_NUMBER() OVER(PARTITION BY question_id ORDER BY answered_at DESC) as rn ");
            sql.append("  FROM learning_history");
            sql.append(") latest_h ON q.question_id = latest_h.question_id AND latest_h.rn = 1 ");
        }
        
        // 抽出条件 (WHERE)
        boolean hasWhere = false;
        if (partType > 0) {
            sql.append("WHERE q.part_type = ? ");
            hasWhere = true;
        }
        
        if (prioritizeIncorrect) {
            // 未回答 (latest_h.is_correct IS NULL) または 不正解 (latest_h.is_correct = 0) を優先、あるいは絞り込み
            // ここでは「不正解だったりまだ解いていない問題を優先的に上に持ってくる」並び順にします
            sql.append(hasWhere ? "AND " : "WHERE ");
            sql.append("(latest_h.is_correct IS NULL OR latest_h.is_correct = 0) ");
            // 順序の調整: 不正解・未回答を先、正解済みのものを後ろに回す
            sql.append("ORDER BY (latest_h.is_correct IS NULL) DESC, latest_h.is_correct ASC, q.question_id ASC");
        } else {
            sql.append("ORDER BY q.question_id ASC");
        }

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            // パラメータの設定（Part指定がある場合）
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

                    Question question = new Question(
                        questionId, passageId, pt, questionText,
                        optionA, optionB, optionC, optionD, correctAnswer, explanation
                    );
                    questionList.add(question);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questionList;
    }
}
