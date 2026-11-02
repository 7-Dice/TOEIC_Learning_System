package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.MockExamDao;
import model.PassageGroup;
import model.PassageGroupDao;
import model.PassageGroupItem;
import model.Question;
import model.QuestionDao;

public class TestController {

    private final PassageGroupDao passageGroupDao = new PassageGroupDao();

    public void startTest(Scanner sc) {
        QuestionDao questionDao = new QuestionDao();
        MockExamDao mockExamDao = new MockExamDao();

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
        mockQuestions.addAll(part5List.subList(0, 30));

        mockQuestions.addAll(selectByPassageGroup(part6List, 16));
        mockQuestions.addAll(selectByPassageGroup(part7List, 54));

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

            // 本文を group_id から取得して表示
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

            if (q.getExplanation() != null) {
                System.out.println("【解説】 " + q.getExplanation());
            }
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

    private List<Question> selectByPassageGroup(List<Question> source, int targetCount) {
        Map<Long, List<Question>> groupMap = new HashMap<>();

        for (Question q : source) {
            long groupId = q.getGroupId();
            if (groupId <= 0) {
                continue;
            }
            groupMap.computeIfAbsent(groupId, k -> new ArrayList<>()).add(q);
        }

        List<List<Question>> groups = new ArrayList<>(groupMap.values());
        for (List<Question> group : groups) {
            Collections.shuffle(group);
        }

        Collections.shuffle(groups);

        List<Question> selected = new ArrayList<>();
        for (List<Question> group : groups) {
            if (selected.size() >= targetCount) {
                break;
            }
            selected.addAll(group);
            if (selected.size() >= targetCount) {
                break;
            }
        }

        if (selected.size() > targetCount) {
            return new ArrayList<>(selected.subList(0, targetCount));
        }

        return selected;
    }
}
