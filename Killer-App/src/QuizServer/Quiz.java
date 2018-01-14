package QuizServer;

import Shared.IPlayer;
import Shared.IQuestion;
import Shared.IQuestionAnswer;
import Shared.IQuiz;

import java.util.ArrayList;

/**
 * Created by myron on 11-12-17.
 */
public class Quiz implements IQuiz
{
    String quizCode;
    ArrayList<IQuestion> questions;
    ArrayList<IPlayer> players;

    public Quiz(String quizCode)
    {
        this.quizCode = quizCode;
        players = new ArrayList<>();
        this.questions = new ArrayList<>();
    }

    @Override
    public String getQuizCode()
    {
        return quizCode;
    }

    @Override
    public ArrayList<IPlayer> getPlayers()
    {
        return players;
    }

    private IPlayer getPlayer(String name)
    {
        for (IPlayer p : players)
        {
            if (p.getName().equals(name))
            {
                return p;
            }
        }
        return null;
    }

    @Override
    public ArrayList<IQuestion> getQuestions()
    {
        return questions;
    }

    @Override
    public void AddQuestion(IQuestion question)
    {
        questions.add(question);
    }

    @Override
    public void AddPlayerAnswer(IPlayer player, IQuestionAnswer questionAnswer)
    {
        IPlayer p = getPlayer(player.getName());
        if (p == null)
        {
            player.AddQuestionAnswer(questionAnswer);
            players.add(player);
        }
        else
        {
            p.AddQuestionAnswer(questionAnswer);
        }

    }
}
