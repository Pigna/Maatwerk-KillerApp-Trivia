package Shared;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by myron on 05-01-18.
 */
public interface IQuizManager extends Remote, Serializable
{
    IQuiz NewQuiz() throws RemoteException;

    void NewQuestion(String quizCode, IQuestion question) throws RemoteException;

    IQuiz GetQuiz(String quizCode) throws RemoteException;
//    void NewPlayer(String quizCode, String playerName) throws RemoteException;

    void PlayerAnswerQuestion(String quizCode, IQuestion question, IPlayer player, Integer answerID) throws RemoteException;
}
