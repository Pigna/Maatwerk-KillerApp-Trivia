package QuizServer;

import Shared.IPlayer;
import Shared.IQuestionAnswer;

import java.util.ArrayList;

/**
 * Created by myron on 11-12-17.
 */
public class Player implements IPlayer
{
    private String name;
    private ArrayList<IQuestionAnswer> answerList = new ArrayList<>();

    public Player(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public void AddQuestionAnswer(IQuestionAnswer questionAnswer)
    {
        answerList.add(questionAnswer);
    }

    @Override
    public ArrayList<IQuestionAnswer> getAllAnswers()
    {
        return answerList;
    }
}
