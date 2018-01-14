package Shared;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by myron on 13-01-18.
 */
public interface IPlayer extends Serializable
{
    String getName();

    void AddQuestionAnswer(IQuestionAnswer questionAnswer);
    ArrayList<IQuestionAnswer> getAllAnswers();
}
