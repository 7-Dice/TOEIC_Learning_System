package controller;

import java.util.List;
import java.util.Scanner;

import model.LearningHistoryDao;
import model.PassageGroup;
import model.PassageGroupDao;
import model.PassageGroupItem;
import model.Question;
import model.QuestionDao;

public class PracticeController {

    private final PassageGroupDao passageGroupDao = new PassageGroupDao();

    public void startPractice(Scanner sc) {
        QuestionDao questionDao = new QuestionDao();
        LearningHistoryDao historyDao = new LearningHistoryDao();

        System.out.println("=== 練習モードを開始します ===");
        System.out.println("出題モードを選んでください:");
        System.out.println("1. すべての問題を順番に出題");
        System.out.println("2. パートを指定して出題");
        System.out.println("3. 誤答・未回答の問題を優先して出題");
        System.out.println("4. モードを終了する場合はQを入力");
        System.out.print("選択肢を入力してください: ");

        String choiceStr = sc.nextLine().trim();
        int partType = 0;
        boolean prioritizeIncorrect = false;

        switch (choiceStr) {
            case "2":
                System.out.print("対象とするパート番号を入力してください (例: 5, 6, 7): ");
                try {
                    partType = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("無効な入力です。全問題モードで開始します。");
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

        // ここが必須
        List<Question> questionList = questionDao.getQuestionsForPractice(partType, prioritizeIncorrect);

        if (questionList.isEmpty()) {
            System.out.println("条件に一致する問題が登録されていません。");
            return;
        }

        System.out.println("\n問題の読み込みが完了しました (" + questionList.size() + "問)\n");

        int totalAnswered = 0;
        int correctCount = 0;

        for (int i = 0; i < questionList.size(); i++) {
            Question q = questionList.get(i);

            System.out.println("\n----------------------------------------");
            System.out.println("問題 " + (i + 1) + " / " + questionList.size() + " (Part " + q.getPartType() + ")");

            if (q.getGroupId() > 0) {
                String passageText = buildPassageText(q.getGroupId());
                if (!passageText.isEmpty()) {
                    System.out.println(passageText);
                }
            }

            System.out.println(q.getQuestionText());
            System.out.println("  [A] " + q.getOptionA());
            System.out.println("  [B] " + q.getOptionB());
            System.out.println("  [C] " + q.getOptionC());
            System.out.println("  [D] " + q.getOptionD());
            System.out.print("解答を入力してください (A, B, C, D / 終了は Q): ");

            String userAnswer = sc.nextLine().trim().toUpperCase();

            if ("Q".equals(userAnswer)) {
                System.out.println("練習モードを途中で終了します。");
                break;
            }

            if (userAnswer.isEmpty()) {
                System.out.println("無効な入力です。もう一度この問題をやり直してください。");
                i--;
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

            if (q.getExplanation() != null) {
                System.out.println("【解説】 " + q.getExplanation());
            }

            boolean saved = historyDao.insertHistory(q.getQuestionId(), isCorrect, userAnswer, 0);
            if (!saved) {
                System.out.println("（警告: 学習履歴の保存に失敗しました）");
            }
        }

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

        System.out.println("お疲れ様でした！");
    }

    private String buildPassageText(long groupId) {
        PassageGroup group = passageGroupDao.getGroupById(groupId);
        if (group == null || group.getItems() == null || group.getItems().isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (PassageGroupItem item : group.getItems()) {
            if (item.getSpeaker() != null && !item.getSpeaker().isEmpty()) {
                sb.append(item.getSpeaker()).append(": ");
            }
            sb.append(item.getTextBody()).append(System.lineSeparator()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
