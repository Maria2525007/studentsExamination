package org.example.students;

import org.example.students.exceptions.ItemNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExamTest {
    private final Examination exam = new Exam();

    @Test
    void addAndGetScore() throws ItemNotFoundException {
        Score score = new Score("John Doe", "Math", 5);
        exam.addScore(score);
        Score actual = exam.getScore("John Doe", "Math");
        assertEquals(score, actual);
    }

    @Test
    void getScore() throws ItemNotFoundException {
        exam.getScore("John Doe", "Math");
       //expected exception 'no such student name'
        Assertions.assertThrows(ItemNotFoundException.class, () -> {
            exam.getScore("John Doe", "Math");
        });
    }

    @Test
    void getAverageForSubject() {
        exam.addScore(new Score("Genry March", "Math", 4));
        exam.addScore(new Score("Frank Konnel", "Philosophy", 3));
        exam.addScore(new Score("Mary Rosher", "Math", 5));
        Double avgScore = exam.getAverageForSubject("Math");
        assertEquals(4.5, avgScore);
    }

    @Test
    void multipleSubmissionsStudentNames() {
        exam.addScore(new Score("Genry March", "Math", 4));
        exam.addScore(new Score("Frank Konnel", "Philosophy", 3));
        exam.addScore(new Score("Mary Rosher", "Math", 5));
        exam.addScore(new Score("Genry March", "Math", 5));
        exam.addScore(new Score("Frank Konnel", "Philosophy", 5));
        exam.addScore(new Score("Mary Rosher", "Math", 3));
        Set<String> result = new LinkedHashSet<>();
        for (Score score : exam.getAllScores()) {
            result.add(score.name());
        }
        Assertions.assertEquals(result, exam.multipleSubmissionsStudentNames());

    }

    @Test
    void lastFiveStudentsWithExcellentMarkOnAnySubject() {
        exam.addScore(new Score("Genry March", "Math", 5));
        exam.addScore(new Score("Frank Konnel", "Philosophy", 5));
        exam.addScore(new Score("Mary Rosher", "Math", 5));
        exam.addScore(new Score("Daniel Creak", "Geography", 5));
        exam.addScore(new Score("Vera Lorean", "PE", 5));
        exam.addScore(new Score("Genry March", "Math", 2));
        exam.addScore(new Score("Nino Rasputin", "IT", 5));
        exam.addScore(new Score("Piter Smith", "Math", 3));
        Set<String> result = new LinkedHashSet<>();
        result.add("Nino Rasputin");
        result.add("Vera Lorean");
        result.add("Daniel Creak");
        result.add("Mary Rosher");
        result.add("Frank Konnel");

        Assertions.assertEquals(result, exam.lastFiveStudentsWithExcellentMarkOnAnySubject());
    }

    @Test
    void getAllScores() {
        exam.addScore(new Score("Genry March", "Math", 2));
        exam.addScore(new Score("Frank Konnel", "Philosophy", 3));
        exam.addScore(new Score("Mary Rosher", "Math", 5));
        Assertions.assertEquals(3, exam.getAllScores().size());
    }
}