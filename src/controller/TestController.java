package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import model.Question;
import model.QuestionDao;
import model.mockExamDao;

public class TestController {

    public void startTest(Scanner sc) {
        QuestionDao questionDao = new QuestionDao();
        mockExamDao mockExamDao = new mockExamDao();

        List<Question> part5List = questionDao.getQuestionsByPart(5);
        List<Question> part6List = questionDao.getQuestionsByPart(6);
        List<Question> part7List = questionDao.getQuestionsByPart(7);

        if (part5List.size() < 30 || part6List.size() < 16 || part7List.size() < 54) {
            System.out.println("模試を開始できません。問題数が不足しています。");
            System.out.println("Part 5: " + part5List.size() + "問");
            System.out.println("Part 6: " + part6List.size() + "問");
            System.out.println("Part 7: " + part7List.size() + "問");
            return;
        }

        List<Question> mockQuestions = new ArrayList<>();
        Collections.shuffle(part5List);
        Collections.shuffle(part6List);
        Collections.shuffle(part7List);

        mockQuestions.addAll(part5List.subList(0, 30));
        mockQuestions.addAll(part6List.subList(0, 16));
        mockQuestions.addAll(part7List.subList(0, 54));
        Collections.shuffle(mockQuestions);

        final int TOTAL_QUESTIONS = mockQuestions.size();
        final int TIME_LIMIT_SECONDS = 75 * 60;

        System.out.println("=== 模試モードを開始します ===");
        System.out.println("問題数: " + TOTAL_QUESTIONS + "問");
        System.out.println("制限時間: 75分");
        System.out.println("※未回答は0点として扱います。\n");

        long startTimeMillis = System.currentTimeMillis();
        int answeredCount = 0;
        int correctCount = 0;
        int incorrectCount = 0;
        int unansweredCount = 0;

        for (int i = 0; i < mockQuestions.size(); i++) {
            if (System.currentTimeMillis() - startTimeMillis >= TIME_LIMIT_SECONDS * 1000L) {
                System.out.println("\n時間が終了しました。模試を終了します。");
                break;
            }

            Question q = mockQuestions.get(i);

            System.out.println("----------------------------------------");
            System.out.println("問題 " + (i + 1) + " / " + TOTAL_QUESTIONS + " (Part " + q.getPartType() + ")");
            System.out.println(q.getQuestionText());
            System.out.println("  [A] " + q.getOptionA());
            System.out.println("  [B] " + q.getOptionB());
            System.out.println("  [C] " + q.getOptionC());
            System.out.println("  [D] " + q.getOptionD());
            System.out.print("解答を入力してください (A, B, C, D / 未回答は Enter): ");

            String userAnswer = sc.nextLine().trim().toUpperCase();

            if (userAnswer.isEmpty()) {
                unansweredCount++;
                System.out.println("未回答として記録しました。");
                continue;
            }

            if (!userAnswer.matches("[ABCD]")) {
                System.out.println("無効な入力です。未回答として扱います。");
                unansweredCount++;
                continue;
            }

            answeredCount++;
            boolean isCorrect = userAnswer.equals(q.getCorrectAnswer().toUpperCase());

            if (isCorrect) {
                correctCount++;
                System.out.println("👉 【正解】");
            } else {
                incorrectCount++;
                System.out.println("👉 【不正解】 正解は " + q.getCorrectAnswer() + " です。");
            }

            System.out.println("【解説】 " + q.getExplanation());
        }

        long endTimeMillis = System.currentTimeMillis();

        boolean saved = mockExamDao.insertMockExamResult(
                new java.sql.Timestamp(startTimeMillis),
                new java.sql.Timestamp(endTimeMillis),
                TOTAL_QUESTIONS,
                answeredCount,
                correctCount,
                incorrectCount,
                unansweredCount
        );

        if (saved) {
            System.out.println("\n模試結果をDBに保存しました。");
        } else {
            System.out.println("\n模試結果の保存に失敗しました。");
        }

        System.out.println("\n========================================");
        System.out.println("          模試結果サマリー          ");
        System.out.println("========================================");
        System.out.println("回答数: " + answeredCount + " 問");
        System.out.println("正解数: " + correctCount + " 問");
        System.out.println("不正解数: " + incorrectCount + " 問");
        System.out.println("未回答数: " + unansweredCount + " 問");
        System.out.println("========================================");
    }
}
