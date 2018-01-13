package Shared;

import QuizServer.Question;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by myron on 11-12-17.
 */
public interface IQuiz extends Serializable
{
    String getQuizCode();
//    ArrayList<Player> getPlayers();
//    Player getPlayer(String name);
    ArrayList<IQuestion> getQuestions();
    IQuestion getQuestion(IQuestion previousQuestion);
    void addQuestion(IQuestion question);
    int GetQuestionId();
}
