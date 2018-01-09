package Shared;

import QuizServer.Player;
import QuizServer.Question;

import java.util.ArrayList;

/**
 * Created by myron on 11-12-17.
 */
public interface IQuiz
{
    public String getQuizCode();
    public ArrayList<Player> getPlayers();
    public Player getPlayer(String name);
    public ArrayList<Question> getQuestions();
    public Question getQuestion();
}
