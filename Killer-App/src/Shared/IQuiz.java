package Shared;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by myron on 11-12-17.
 */
public interface IQuiz extends Serializable
{
    String getQuizCode();

    ArrayList<IPlayer> getPlayers();

    ArrayList<IQuestion> getQuestions();

    void AddQuestion(IQuestion question);

    void AddPlayerAnswer(IPlayer player, IQuestionAnswer questionAnswer);
}
