/*
Требования
    - Реализовать интерфейс у которого есть методы
    - добавить сдачу студента (в зачет идет только последняя сдача, хранить все сдачи студента по одному и тому же предмету не нужно)
    - получить сдачу студента по имени и фамилии и предмету
    - вывод средней оценки по предмету
    - вывод студентов кто сдавал более одного раза
    - вывод последних пяти студентов сдавших на отлично по любому предмету
    - вывод всех сданных предметов
    - Сделать кеш для вывода средней оценки по предмету за пределами интерфейса `Examination`
*/
package org.example.students;

import org.example.students.exceptions.ItemNotFoundException;

import java.util.*;

public class Exam implements Examination{
    public static final int INITIAL_CAPACITY = 128;
    private final Map<String, Score> scores = new HashMap<>(INITIAL_CAPACITY);
    private final Set<String> multipleSubmissionsStudentNames = new HashSet<>();

    @Override
    public void addScore(Score score) {
        if (!scores.containsKey(score.name())) {
            scores.put(score.name(), score);
        }
        else {
            scores.replace(score.name(), score);
            multipleSubmissionsStudentNames.add(score.name());
        }

    }

    @Override
    public Score getScore(String name, String subject) throws ItemNotFoundException {
        if (scores.containsKey(name)) {
            return scores.get(name);
        }
        throw new ItemNotFoundException("No such student name");
    }

    @Override
    public double getAverageForSubject(String subject) {
        double sum = 0;
        int quantity = 0;
        for (Score score : scores.values()) {
            if (score.subject().equals(subject)) {
                sum += score.score();
                quantity++;
            }
        }
        return sum / quantity;
    }

    @Override
    public Set<String> multipleSubmissionsStudentNames() {
        return multipleSubmissionsStudentNames;
    }

    @Override
    public Set<String> lastFiveStudentsWithExcellentMarkOnAnySubject() {
        Set<String> lastFiveStudents = new HashSet<>();

        return lastFiveStudents;
    }

    @Override
    public Collection<Score> getAllScores() {
        Collection<Score> scores = new ArrayList<>();
        for (Score score : this.scores.values()) {
            scores.add(score);
        }
        return scores;
    }
}
