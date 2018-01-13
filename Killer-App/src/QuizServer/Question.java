package QuizServer;

import Shared.IQuestion;

/**
 * Created by myron on 11-12-17.
 */
public class Question implements IQuestion
{
    int id;
    String question;
    String answerA;
    String answerB;
    String answerC;
    String answerD;

    @Override
    public int getQuestionId()
    {
        return id;
    }
    @Override
    public String getQuestion()
    {
        return question;
    }
    @Override
    public String getAnswerA()
    {
        return answerA;
    }
    @Override
    public String getAnswerB()
    {
        return answerB;
    }
    @Override
    public String getAnswerC()
    {
        return answerC;
    }
    @Override
    public String getAnswerD()
    {
        return answerD;
    }

    public Question(int id, String question, String answerA, String answerB, String answerC, String answerD)
    {
        this.id = id;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
    }
}
