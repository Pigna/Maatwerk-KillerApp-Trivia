package QuizClient;

import Shared.IQuestion;
import fontyspublisher.IRemotePropertyListener;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by myron on 13-01-18.
 */
public class PropertyListener extends UnicastRemoteObject implements IRemotePropertyListener
{
    Controller controller;

    PropertyListener(Controller controller) throws RemoteException
    {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) throws RemoteException
    {
        controller.quiz.AddQuestion((IQuestion) pce.getNewValue());
        if (controller.currentQuestion == null)
        {
            controller.SetQuestion();
        }
        System.out.println("propertychange");
    }
}
