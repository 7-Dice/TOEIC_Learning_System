package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    // 接続URL、ユーザー名、パスワードの設定
    private static final String URL = "jdbc:mysql://localhost:3306/toeic_learning_system?useSSL=false&serverTimezone=Asia/Tokyo";
    private static final String USER = "root";       // ご自身の環境に合わせて変更
    private static final String PASSWORD = "password"; // ご自身の環境に合わせて変更

    // データベースとのコネクションを取得するメソッド
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
