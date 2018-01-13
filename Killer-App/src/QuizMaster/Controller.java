package QuizMaster;

import QuizServer.Question;
import Shared.IQuestion;
import Shared.IQuiz;
import Shared.IQuizManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

public class Controller extends Main implements Initializable
{

    @FXML
    Button btnCreateNewQuestion;

    @FXML
    TextArea taQuestion;

    @FXML
    TextArea taAnswerQuestionA;

    @FXML
    TextArea taAnswerQuestionB;

    @FXML
    TextArea taAnswerQuestionC;

    @FXML
    TextArea taAnswerQuestionD;

    @FXML
    Label lblRoomCode;
    //RMI stuff
    private static final String bindingNameQM = "gameManager";
    private Registry registry = null;

    IQuizManager quizManager;
    IQuiz quiz;

    public Controller()
    {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try
        {
            ConnectToServer("10.0.0.9",1098);
        }
        catch (Exception ex)
        {
            System.out.println("Master: Cannot create MasterController");
            System.out.println("Master: Exception: " + ex.getMessage());
        }
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
            System.out.println("Master: QuizManager is ready!");
            try
            {
                quiz = quizManager.NewQuiz();
                SetRoomCode(quiz.getQuizCode());
            }
            catch (RemoteException ex)
            {
                System.out.println("Master: Remote Exception: " + ex.getMessage());
            }
        }
        else
        {
            System.out.println("Master: Error: 'QuizManager' is null");
        }
    }
    @FXML
    public void SendNewQuestion(Event event)
    {
        String question = taQuestion.getText();
        String answerA = taAnswerQuestionA.getText();
        String answerB = taAnswerQuestionB.getText();
        String answerC = taAnswerQuestionC.getText();
        String answerD = taAnswerQuestionD.getText();

        if(!question.trim().equals("") && !answerA.trim().equals("") && !answerB.trim().equals("") && !answerC.trim().equals("") && !answerD.trim().equals(""))
        {
            try
            {
                IQuestion q = new Question(quiz.GetQuestionId(), question, answerA, answerB, answerC, answerD);
                quiz.addQuestion(q);
                quizManager.NewQuestion(quiz.getQuizCode(), q);
                ClearTextAreas();
            }
            catch (RemoteException e)
            {
                System.out.println("Master: Send Question Remote Exception");
            }
        }
    }
    private void ClearTextAreas()
    {
        taQuestion.setText("");
        taAnswerQuestionA.setText("");
        taAnswerQuestionB.setText("");
        taAnswerQuestionC.setText("");
        taAnswerQuestionD.setText("");
    }
    public void GetAnswers()
    {

    }
    private void SetRoomCode(String roomCode)
    {
        lblRoomCode.setText(roomCode);
    }
}
