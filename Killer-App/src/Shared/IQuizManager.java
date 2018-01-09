package Shared;

import QuizServer.Player;
import QuizServer.Question;
import QuizServer.Quiz;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by myron on 05-01-18.
 */
public interface IQuizManager extends Remote
{
    public IQuiz NewQuiz() throws RemoteException;

    public void NewQuestion(String quizCode, IQuestion question) throws RemoteException;

    public void NewPlayer(String quizCode, String playerName) throws RemoteException;

    public void PlayerAnswerQuestion(Question question, Player player, Integer answerID) throws RemoteException;
}
