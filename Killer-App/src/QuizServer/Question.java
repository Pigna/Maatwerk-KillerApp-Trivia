package QuizServer;

import Shared.IQuestion;

/**
 * Created by myron on 11-12-17.
 */
public class Question implements IQuestion
{
    String question;
    String answerA;
    String answerB;
    String answerC;
    String answerD;

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

    @Override
    public String getAnswer(int nr)
    {
        switch (nr)
        {
            case 1: return answerA;
            case 2: return answerB;
            case 3: return answerC;
            case 4: return answerD;
            default: return "";
        }
    }

    public Question(String question, String answerA, String answerB, String answerC, String answerD)
    {
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
    }
}
