package QuizServer;

import Shared.*;
import fontyspublisher.IRemotePublisherForDomain;

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
    int counter = 0;

    public QuizManager(IRemotePublisherForDomain remotePublisherForDomain) throws RemoteException
    {
        this.quizzes = new ArrayList<>();
        this.remotePublisherForDomain = remotePublisherForDomain;
        //NewQuiz();
    }

    @Override
    public IQuiz NewQuiz()
    {
        //TODO: Generate UUID
        String code = "quiz" + counter;
        counter++;
        try
        {
            IQuiz newQuiz = new Quiz(code);
            remotePublisherForDomain.registerProperty(code);
            System.out.println("Quiz created: " + code);
            quizzes.add(newQuiz);
            return newQuiz;
        }
        catch (RemoteException e)
        {
            System.out.println("Server: Register Property Error");
        }
        return null;
    }

    @Override
    public void NewQuestion(String quizCode, IQuestion question)
    {
        //inform player
        IQuiz quiz = null;
        try
        {
            quiz = GetQuiz(quizCode);
        }
        catch (RemoteException e)
        {
            System.out.println("Server: Error getting Quiz by code");
        }
        if (quiz != null)
        {
            quiz.AddQuestion(question);
            try
            {
                // Inform player with the new question
                remotePublisherForDomain.inform(quiz.getQuizCode(), question, question);
            }
            catch (RemoteException e)
            {
                System.out.println("Server: Error informing players");
            }
        }
    }

    @Override
    public IQuiz GetQuiz(String quizCode) throws RemoteException
    {
        for (IQuiz q : quizzes)
        {
            if (q.getQuizCode().equals(quizCode))
            {
                return q;
            }
        }
        return null;
    }

    @Override
    public void PlayerAnswerQuestion(String quizCode, IQuestion question, IPlayer player, int answerID) throws RemoteException
    {
        IQuiz q = GetQuiz(quizCode);
        IQuestionAnswer qa = new QuestionAnswer(question, answerID);
        q.AddPlayerAnswer(player, qa);
    }
}
