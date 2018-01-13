package Shared;

import java.io.Serializable;

/**
 * Created by myron on 11-12-17.
 */
public interface IQuestion extends Serializable
{
    String getQuestion();
    String getAnswerA();
    String getAnswerB();
    String getAnswerC();
    String getAnswerD();
}
