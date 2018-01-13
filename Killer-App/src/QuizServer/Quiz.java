package QuizServer;

import Shared.IQuestion;
import Shared.IQuiz;

import java.util.ArrayList;

/**
 * Created by myron on 11-12-17.
 */
public class Quiz implements IQuiz
{
    String quizCode;
    //ArrayList<Player> players;
    ArrayList<IQuestion> questions;

    protected Quiz(String quizCode)
    {
        this.quizCode = quizCode;
        //players = new ArrayList<>();
        this.questions = new ArrayList<>();
    }

    @Override
    public String getQuizCode()
    {
        return quizCode;
    }
//    @Override
//    public ArrayList<Player> getPlayers()
//    {
//        return players;
//    }
//
//    @Override
//    public Player getPlayer(String name)
//    {
//        for (Player p : players)
//        {
//            if (p.getName().equals(name))
//            {
//                return p;
//            }
//        }
//        return null;
//    }

    @Override
    public ArrayList<IQuestion> getQuestions()
    {
        return questions;
    }

    @Override
    public IQuestion getQuestion(IQuestion previousQuestion)
    {
        if (previousQuestion != null)
        {
            return questions.get(previousQuestion.getQuestionId());
        }
        else if (questions.size() > 0)
        {
            return questions.get(0);
        }
        return null;
    }

    @Override
    public void addQuestion(IQuestion question)
    {
        questions.add(question);
    }

    @Override
    public int GetQuestionId()
    {
        return questions.size();
    }
}
