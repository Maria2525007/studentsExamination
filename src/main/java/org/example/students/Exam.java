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
    private final Map<String, Score> scores = new LinkedHashMap<>(INITIAL_CAPACITY);
    private final Set<String> multipleSubmissionsStudentNames = new HashSet<>();

    @Override
    public void addScore(Score score) {
        scores.compute(score.name(), (key, existingScore) -> {
            if (existingScore != null) {
                multipleSubmissionsStudentNames.add(key);
            }
            return score;
        });

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
        // return average mark using cache
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
        Set<String> lastFiveStudents = new LinkedHashSet<>(5);
        int count = 0;
        //add to linked hash set only five last added students with excellent mark, use for loop from the end of the scores set
        // reverse set
        List<Score> scoresList = new ArrayList<>(scores.values());
        Collections.reverse(scoresList);
        for (Score score : scoresList) {
            if (score.score() == 5) {
                lastFiveStudents.add(score.name());
                count++;
            }
            if (count == 5) {
                break;
            }
        }
        return lastFiveStudents;
    }

    @Override
    public Collection<Score> getAllScores() {
        return new ArrayList<>(this.scores.values());
    }
}
