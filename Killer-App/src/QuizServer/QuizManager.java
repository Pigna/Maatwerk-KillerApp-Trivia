package QuizServer;

import Shared.IQuestion;
import Shared.IQuiz;
import Shared.IQuizManager;
import fontyspublisher.IRemotePublisherForDomain;
import fontyspublisher.RemotePublisher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by myron on 22-11-17.
 */
public class QuizManager extends UnicastRemoteObject implements IQuizManager
{
    List<IQuiz> quizzes;
    IRemotePublisherForDomain remotePublisherForDomain;

    public QuizManager(IRemotePublisherForDomain remotePublisherForDomain) throws RemoteException
    {
        this.quizzes = new ArrayList<>();
        this.remotePublisherForDomain = remotePublisherForDomain;
        //NewQuiz();
    }
    public Quiz NewQuiz()
    {
        //TODO: Generate UUID
        String code = "quiz";
        try
        {
            Quiz newQuiz = new Quiz(code);
            remotePublisherForDomain.registerProperty(code);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public void NewQuestion(String quizCode, IQuestion question)
    {
        //inform player
    }
    public void NewPlayer(String quizCode, String playerName)
    {

    }
    public void PlayerAnswerQuestion(Question question, Player player, Integer answerID)
    {

    }
}
