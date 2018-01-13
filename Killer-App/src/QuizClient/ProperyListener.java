package QuizClient;

import Shared.IQuestion;
import fontyspublisher.IRemotePropertyListener;

import java.beans.PropertyChangeEvent;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by myron on 13-01-18.
 */
public class ProperyListener extends UnicastRemoteObject implements IRemotePropertyListener
{
    Controller controller;
    ProperyListener(Controller controller) throws RemoteException
    {
        this.controller = controller;
    }
    @Override
    public void propertyChange(PropertyChangeEvent pce) throws RemoteException
    {
        controller.quiz.addQuestion((IQuestion) pce.getNewValue());
        if(controller.currentQuestion == null)
        {
            controller.SetQuestion();
        }
        System.out.println("propertychange");
    }
}
