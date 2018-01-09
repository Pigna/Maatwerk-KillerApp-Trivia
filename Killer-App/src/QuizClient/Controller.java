package QuizClient;

import Shared.IQuestion;
import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForListener;

import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by myron on 22-11-17.
 */
public class Controller extends UnicastRemoteObject implements IRemotePropertyListener
{
    Main clientMain;

    //RMI stuff
    private static final String bindingName = "serverInform";
    private static final String propertyName = "quiz"; //TODO: change to player input Game UUID
    private Registry registry = null;

    private IRemotePublisherForListener remotePublisherForListener;

    public Controller(Main clientMain) throws RemoteException
    {
        ConnectToServer("10.0.0.9",1098);
        this.clientMain = clientMain;
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) throws RemoteException
    {
        clientMain.addQuestion((IQuestion) pce.getNewValue());
    }

    private void ConnectToServer(String ip, int poort)
    {
        try
        {
            registry = LocateRegistry.getRegistry(ip, poort);
        }
        catch (RemoteException ex)
        {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            registry = null;
        }

        // Print result locating registry
        if (registry != null)
        {
            System.out.println("Client: Registry located");
        }
        else
        {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: Registry is null pointer");
        }
        // Bind student administration using registry
        if (registry != null)
        {
            try
            {
                this.remotePublisherForListener = (IRemotePublisherForListener) registry.lookup(bindingName);
                System.out.println("Client: Lookup listener successful");
            }
            catch (RemoteException ex)
            {
                System.out.println("Client: Cannot bind listener");
                System.out.println("Client: RemoteException: " + ex.getMessage());
            }
            catch (NotBoundException ex)
            {
                System.out.println("Client: Cannot bind listener");
                System.out.println("Client: NotBoundException: " + ex.getMessage());
            }
        }
        try
        {
            remotePublisherForListener.subscribeRemoteListener(this, propertyName);
        }
        catch (RemoteException ex)
        {
            System.out.println("Client: Cannot bind property");
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }

    }
}
