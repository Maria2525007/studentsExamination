package org.example.students;

import java.util.Objects;

public final class Score {
    private final String name;
    private final String subject;
    private final int score;

    public Score(String name, String subject, int score) {
        this.name = name;
        this.subject = subject;
        this.score = score;
    }

    public String name() {
        return name;
    }

    public String subject() {
        return subject;
    }

    public int score() {
        return score;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Score) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.subject, that.subject) &&
                this.score == that.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, subject, score);
    }

    @Override
    public String toString() {
        return "Score[" +
                "name=" + name + ", " +
                "subject=" + subject + ", " +
                "score=" + score + ']';
    }

}
