package QuizMaster;

import Shared.IQuiz;
import Shared.IQuizManager;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Controller {

    Main masterMain;
    //RMI stuff
    private static final String bindingNameQM = "gameManager";
    private Registry registry = null;

    IQuizManager quizManager;
    IQuiz quiz;

    public Controller(Main masterMain)
    {
        this.masterMain = masterMain;
        ConnectToServer("10.0.0.9",1098);
    }

    private void ConnectToServer(String ip, int poort)
    {
        try
        {
            registry = LocateRegistry.getRegistry(ip, poort);
        }
        catch (RemoteException ex)
        {
            System.out.println("Master: Cannot locate registry");
            System.out.println("Master: RemoteException: " + ex.getMessage());
            registry = null;
        }

        // Print result locating registry
        if (registry != null)
        {
            System.out.println("Master: Registry located");
        }
        else
        {
            System.out.println("Master: Cannot locate registry");
            System.out.println("Master: Registry is null pointer");
        }
        // Bind student administration using registry
        if (registry != null)
        {
            try
            {
                quizManager = (IQuizManager) registry.lookup(bindingNameQM);
            }
            catch (RemoteException ex)
            {
                System.out.println("Master: Cannot bind QuizManager");
                System.out.println("Master: RemoteException: " + ex.getMessage());
                quizManager = null;
            }
            catch (NotBoundException ex)
            {
                System.out.println("Master: Cannot bind QuizManager");
                System.out.println("Master: NotBoundException: " + ex.getMessage());
                quizManager = null;
            }
        }
        if (quizManager != null)
        {
            //testStudentAdministration();
            System.out.println("QuizManager is ready!");
            try
            {
                quiz = quizManager.NewQuiz();
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Error: 'QuizManager' is null");
        }
    }
}
