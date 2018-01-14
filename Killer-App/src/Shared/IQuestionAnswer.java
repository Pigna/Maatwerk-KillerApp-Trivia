package Shared;

import java.io.Serializable;

/**
 * Created by myron on 14-01-18.
 */
public interface IQuestionAnswer extends Serializable
{
    IQuestion getQuestion();
    int getAnswer();
}
