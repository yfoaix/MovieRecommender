package com.yingjianren.yingjianren.model;

public class ScoreObject {
    private int status;
    
    private int score;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ScoreObject(int status, int score) {
        this.status = status;
        this.score = score;
    }
}