package controller;

import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
    	/*
        System.out.println("データベースからのデータ取得テストを開始します...");

        QuestionDao dao = new QuestionDao();
        List<Question> questions = dao.getAllQuestions();

        System.out.println("取得件数: " + questions.size() + " 件\n");

        for (Question q : questions) {
            System.out.println("--- 設問ID: " + q.getQuestionId() + " (Part " + q.getPartType() + ") ---");
            System.out.println("問題文: " + q.getQuestionText());
            System.out.println("選択肢 A: " + q.getOptionA());
            System.out.println("選択肢 B: " + q.getOptionB());
            System.out.println("選択肢 C: " + q.getOptionC());
            System.out.println("選択肢 D: " + q.getOptionD());
            System.out.println("正解: " + q.getCorrectAnswer());
            System.out.println("解説: " + q.getExplanation());
            System.out.println();
        }
        */
    	
    	// 練習モードを起動する
    	Scanner sc = new Scanner(System.in);
    	while (true) {
            System.out.println("\n=== メニュー ===");
            System.out.println("1. 練習モード");
            System.out.println("2. 模試モード");
            System.out.println("3. 終了");
            System.out.print("選択してください: ");

            String menu = sc.nextLine().trim();

            if ("1".equals(menu)) {
                PracticeController pc = new PracticeController();
                pc.startPractice(sc);
            } else if ("2".equals(menu)) {
                TestController tc = new TestController();
                tc.startTest(sc);
            } else if ("3".equals(menu)) {
                System.out.println("終了します。");
                break;
            } else {
                System.out.println("無効な入力です。");
            }
        }

        sc.close();
    }
}
