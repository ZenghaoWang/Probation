package com.teamacademicprobation.probation.game.implementations.triviagame;

//TODO: Documentation
public class TriviaGame {
    private boolean finished;
    private Question currentQuestion;
    private QuestionManager questionManager;
    private int numQuestionsAnswered;
    private int numQuestionsAnsweredCorrectly;

    public TriviaGame() {
        finished = false;
        questionManager = new QuestionManager();
        numQuestionsAnswered = 0;
        numQuestionsAnsweredCorrectly = 0;
    }

    public int getNumQuestionsAnswered() {
        return numQuestionsAnswered;
    }

    public int getNumQuestionsAnsweredCorrectly() {
        return numQuestionsAnsweredCorrectly;
    }

    /**
     * Calls questionmanager to get a new question.
     */
    public void updateQuestion() {
        try {
            currentQuestion = questionManager.getRandomQuestion();
        } catch (OutOfQuestionsException e) {
            finished = true;
        }
    }

    public String getQuestion() {
        return currentQuestion.getQuestion();
    }

    public String getAnswer1() {
        return currentQuestion.getAnswer1();
    }

    public String getAnswer2() {
        return currentQuestion.getAnswer2();
    }

    public String getAnswer3() {
        return currentQuestion.getAnswer3();
    }

    public String getAnswer4() {
        return currentQuestion.getAnswer4();
    }


    public void answerQuestion(String answer) {
        numQuestionsAnswered += 1;
        if (currentQuestion.isAnswerCorrect(answer)) {
            numQuestionsAnsweredCorrectly += 1;
        }
    }

    public boolean isFinished() {
        return finished;
    }
}
