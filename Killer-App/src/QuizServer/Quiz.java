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
    public void addQuestion(IQuestion question)
    {
        questions.add(question);
    }
}
