package controller;

import java.util.List;
import java.util.Scanner;

import model.LearningHistoryDao;
import model.Question;
import model.QuestionDao;

public class PracticeController {

	// ScannerをMainで1つ作って渡す、Close処理をMainで1度だけ行う
    public void startPractice(Scanner sc) {
        QuestionDao questionDao = new QuestionDao();
        LearningHistoryDao historyDao = new LearningHistoryDao();

        try {
            System.out.println("=== 練習モードを開始します ===");
            System.out.println("出題モードを選んでください:");
            System.out.println("1. すべての問題を順番に出題");
            System.out.println("2. パートを指定して出題");
            System.out.println("3. 誤答・未回答の問題を優先して出題");
            System.out.println("4. モードを終了する場合はQを入力\n");
            System.out.println("選択肢を入力してください");

            String choiceStr = sc.nextLine().trim();
            int partType = 0;
            boolean prioritizeIncorrect = false;
            
            switch (choiceStr) {
                case "2":
                    System.out.print("対象とするパート番号を入力してください (例: 5, 6, 7): ");
                    if (sc.hasNextLine()) {
                        try {
                            partType = Integer.parseInt(sc.nextLine().trim());
                        } catch (NumberFormatException e) {
                            System.out.println("無効な入力です。全問題モードで開始します。");
                        }
                    }
                    break;
                case "3":
                    prioritizeIncorrect = true;
                    System.out.println("【誤答・未回答優先モードで開始します】");
                    break;
                default:
                    System.out.println("【通常（全問）モードで開始します】");
                    break;
            }

            List<Question> questionList = questionDao.getQuestionsForPractice(partType, prioritizeIncorrect);

            if (questionList.isEmpty()) {
                System.out.println("条件に一致する問題が登録されていません。");
                return;
            }

            System.out.println("\n問題の読み込みが完了しました (" + questionList.size() + "問)\n");

            // 成績集計用の変数
            int totalAnswered = 0;
            int correctCount = 0;
            
            for (int i = 0; i < questionList.size(); i++) {
                Question q = questionList.get(i);

                System.out.println("\n----------------------------------------");
                System.out.println("問題 " + (i + 1) + " / " + questionList.size() + " (Part " + q.getPartType() + ")");
                System.out.println(q.getQuestionText());
                System.out.println("  [A] " + q.getOptionA());
                System.out.println("  [B] " + q.getOptionB());
                System.out.println("  [C] " + q.getOptionC());
                System.out.println("  [D] " + q.getOptionD());
                System.out.print("解答を入力してください (A, B, C, D / 終了は Q): ");

                String userAnswer = "";
                if (sc.hasNextLine()) {
                    userAnswer = sc.nextLine().trim().toUpperCase();
                }

                if ("Q".equals(userAnswer)) {
                    System.out.println("練習モードを途中で終了します。");
                    break;
                }

                // 入力が空だった場合のガード
                if (userAnswer.isEmpty()) {
                    System.out.println("無効な入力です。もう一度この問題をやり直してください。");
                    i--; // 同じ問題を再出題
                    continue;
                }

                boolean isCorrect = userAnswer.equals(q.getCorrectAnswer().toUpperCase());
                totalAnswered++;

                if (isCorrect) {
                    correctCount++;
                    System.out.println("👉 【正解！】");
                } else {
                    System.out.println("👉 【不正解...】 正解は " + q.getCorrectAnswer() + " です。");
                }

                System.out.println("【解説】 " + q.getExplanation());

                boolean saved = historyDao.insertHistory(q.getQuestionId(), isCorrect, userAnswer, 0);
                if (!saved) {
                    System.out.println("（警告: 学習履歴の保存に失敗しました）");
                }
            }

            // セッション終了時の成績サマリー表示
            System.out.println("\n========================================");
            System.out.println("          今回の練習結果サマリー          ");
            System.out.println("========================================");
            System.out.println(" 解答数 : " + totalAnswered + " 問");
            System.out.println(" 正解数 : " + correctCount + " 問");
            if (totalAnswered > 0) {
                double accuracy = (double) correctCount / totalAnswered * 100.0;
                System.out.printf(" 正答率 : %.1f%%\n", accuracy);
            } else {
                System.out.println(" 正答率 : -");
            }
            System.out.println("========================================");

        } finally {}

        System.out.println("お疲れ様でした！");
    }
}
