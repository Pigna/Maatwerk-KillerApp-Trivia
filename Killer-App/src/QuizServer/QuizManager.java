package QuizServer;

import Shared.IQuiz;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by myron on 22-11-17.
 */
public class QuizManager extends UnicastRemoteObject
{
    List<IQuiz> activeQuiz;

    public QuizManager() throws RemoteException
    {
        this.activeQuiz = new ArrayList<>();
    }
    //add question to quiz
    //add player to quiz
    //add player answer to question
    //add question to quiz
}
