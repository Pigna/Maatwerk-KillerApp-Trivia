package Shared;

import QuizServer.Question;

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

    //void PlayerAnswerQuestion(Question question, Player player, Integer answerID) throws RemoteException;
}
