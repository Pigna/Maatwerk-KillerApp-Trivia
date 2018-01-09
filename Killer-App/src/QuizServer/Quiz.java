package QuizServer;

import Shared.IQuiz;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by myron on 11-12-17.
 */
public class Quiz extends UnicastRemoteObject implements IQuiz
{
    String quizCode;
    ArrayList<Player> players;
    ArrayList<Question> questions;

    protected Quiz(String quizCode) throws RemoteException
    {
        this.quizCode = quizCode;
        players = new ArrayList<>();
        questions = new ArrayList<>();
    }

    @Override
    public String getQuizCode()
    {
        return quizCode;
    }
    @Override
    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    @Override
    public Player getPlayer(String name)
    {
        for (Player p : players)
        {
            if (p.getName().equals(name))
            {
                return p;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Question> getQuestions()
    {
        return questions;
    }

    @Override
    public Question getQuestion()
    {
        if (questions.size() > 0)
        {
            return questions.get(questions.size() - 1);
        }
        return null;
    }
}
