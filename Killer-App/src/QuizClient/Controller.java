package QuizClient;

import QuizServer.Player;
import Shared.IPlayer;
import Shared.IQuestion;
import Shared.IQuiz;
import Shared.IQuizManager;
import fontyspublisher.IRemotePublisherForListener;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

/**
 * Created by myron on 22-11-17.
 */
public class Controller extends Main implements Initializable
{
    //RMI stuff
    private static final String bindingName = "serverInform";
    private static final String bindingNameQM = "gameManager";

    private Registry registry = null;

    private IRemotePublisherForListener remotePublisherForListener;

    IQuizManager quizManager = null;
    IQuiz quiz = null;
    IQuestion currentQuestion = null;
    int counter = 0;
    IPlayer player = null;
    PropertyListener listener = null;

    @FXML
    transient TextField tfUsername;
    @FXML
    transient TextField tfQuizCode;
    @FXML
    transient TabPane tabPane;
    @FXML
    transient Tab tabJoin;
    @FXML
    transient Tab tabQuiz;
    @FXML
    transient Tab tabHighscore;
    @FXML
    transient Label lbQuestion;
    @FXML
    transient Button btnAnswerQuestionA;
    @FXML
    transient Button btnAnswerQuestionB;
    @FXML
    transient Button btnAnswerQuestionC;
    @FXML
    transient Button btnAnswerQuestionD;

    public Controller()
    {
        ConnectToServer("10.0.0.9", 1098);
        try
        {
            listener = new PropertyListener(this);
        }
        catch (Exception ex)
        {
            System.out.println("Error: Creating listener");
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
                this.quizManager = (IQuizManager) registry.lookup(bindingNameQM);
                System.out.println("Client: Lookup QuizManager successful");
            }
            catch (RemoteException ex)
            {
                System.out.println("Client: Cannot bind listener / QuizMaster");
                System.out.println("Client: RemoteException: " + ex.getMessage());
                quizManager = null;
            }
            catch (NotBoundException ex)
            {
                System.out.println("Client: Cannot bind listener / QuizMaster");
                System.out.println("Client: NotBoundException: " + ex.getMessage());
                quizManager = null;
            }
        }
    }

    public void JoinQuiz(Event event)
    {
        String inputUsername = tfUsername.getText().trim();
        String inputQuizCode = tfQuizCode.getText().trim();
        if (!inputQuizCode.equals("") && !inputUsername.equals(""))
        {
            try
            {
                quiz = quizManager.GetQuiz(inputQuizCode);
            }
            catch (RemoteException ex)
            {
                System.out.println("Client: Cannot get Quiz with inputCode");
                System.out.println("Client: RemoteException: " + ex.getMessage());
            }
            if (quiz != null)
            {
                SubscribeListener(quiz.getQuizCode());
                System.out.println("Client: Subscribed!");

                tabPane.getSelectionModel().select(tabQuiz);
                tabQuiz.setDisable(false);
                tabJoin.setDisable(true);

                SetQuestion();
            }
            player = new Player(inputUsername);
        }
    }

    private void SubscribeListener(String QuizCode)
    {
        try
        {
            System.out.println("Client trying to subscribe");
            remotePublisherForListener.subscribeRemoteListener(listener, QuizCode);
            System.out.println("Client subscribed!");
        }
        catch (RemoteException ex)
        {
            System.out.println("Client: Cannot bind property");
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }
    }

    void SetQuestion()
    {
        currentQuestion = getQuestion();
        if (currentQuestion != null)
        {
            EnableQuestion();
            Platform.runLater(() ->
            {
                lbQuestion.setText(currentQuestion.getQuestion());
                btnAnswerQuestionA.setText(currentQuestion.getAnswerA());
                btnAnswerQuestionB.setText(currentQuestion.getAnswerB());
                btnAnswerQuestionC.setText(currentQuestion.getAnswerC());
                btnAnswerQuestionD.setText(currentQuestion.getAnswerD());
            });
        }
        else
        {
            ClearQuestion();
        }
    }

    private IQuestion getQuestion()
    {
        if(quiz.getQuestions().listIterator(counter).hasNext())
        {
            IQuestion q = quiz.getQuestions().listIterator(counter).next();
            counter++;
            return q;
        }
        return null;
    }

    private void ClearQuestion()
    {
        lbQuestion.setText("");
        btnAnswerQuestionA.setText("");
        btnAnswerQuestionB.setText("");
        btnAnswerQuestionC.setText("");
        btnAnswerQuestionD.setText("");

        btnAnswerQuestionA.setDisable(true);
        btnAnswerQuestionB.setDisable(true);
        btnAnswerQuestionC.setDisable(true);
        btnAnswerQuestionD.setDisable(true);
    }

    private void EnableQuestion()
    {
        btnAnswerQuestionA.setDisable(false);
        btnAnswerQuestionB.setDisable(false);
        btnAnswerQuestionC.setDisable(false);
        btnAnswerQuestionD.setDisable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    @FXML
    public void AnswerQuestion(Event event)
    {
        String id = ((Control) event.getSource()).getId();
        int answerid = 0;
        switch (id)
        {
            case "btnAnswerQuestionA":
            {
                answerid = 1;
            }
            case "btnAnswerQuestionB":
            {
                answerid = 2;
            }
            case "btnAnswerQuestionC":
            {
                answerid = 3;
            }
            case "btnAnswerQuestionD":
            {
                answerid = 4;
            }
        }
        try
        {
            quizManager.PlayerAnswerQuestion(quiz.getQuizCode(), currentQuestion, player, answerid);
            SetQuestion();
            System.out.println("Question answered.");
        }
        catch (RemoteException e)
        {
            System.out.println("Client: Remote exception answering the question.");
        }
    }
}
