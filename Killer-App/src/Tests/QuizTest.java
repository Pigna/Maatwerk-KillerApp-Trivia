package Tests;

import QuizServer.Player;
import QuizServer.Question;
import QuizServer.QuestionAnswer;
import QuizServer.Quiz;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by myron on 14-01-18.
 */
public class QuizTest
{
    @Before
    public void setUp() throws Exception
    {

    }

    @Test
    public void getQuizCode() throws Exception
    {
        Quiz quiz = new Quiz("QuizCode1");
        assertEquals("Result must be 'QuizCode1'", "QuizCode1", quiz.getQuizCode());
    }

    @Test
    public void getPlayers() throws Exception
    {
        Quiz quiz = new Quiz("QuizCode1");
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Player player3 = new Player("Player3");
        assertEquals("Result must be '0'", 0, quiz.getPlayers().size());
        QuestionAnswer questionAnswer = new QuestionAnswer(new Question("question", "answerA", "answerB","answerC", "answerD"),1);
        quiz.AddPlayerAnswer(player1, questionAnswer);
        assertEquals("Result must be '1'", 1, quiz.getPlayers().size());
        quiz.AddPlayerAnswer(player2, questionAnswer);
        assertEquals("Result must be '2'", 2, quiz.getPlayers().size());
        quiz.AddPlayerAnswer(player3, questionAnswer);
        assertEquals("Result must be '3'", 3, quiz.getPlayers().size());
    }

    @Test
    public void getQuestions() throws Exception
    {
        Quiz quiz = new Quiz("QuizCode1");
        assertEquals("Result must be '0'", 0, quiz.getQuestions().size());
        Question question = new Question("question", "answerA", "answerB","answerC", "answerD");
        quiz.AddQuestion(question);
        assertEquals("Result must be '1'", 1, quiz.getQuestions().size());
        quiz.AddQuestion(question);
        assertEquals("Result must be '2'", 2, quiz.getQuestions().size());
        quiz.AddQuestion(question);
        assertEquals("Result must be '3'", 3, quiz.getQuestions().size());
    }

    @Test
    public void addQuestion() throws Exception
    {
        Quiz quiz = new Quiz("QuizCode1");
        assertEquals("Result must be '0'", 0, quiz.getQuestions().size());
        Question question = new Question("question", "answerA", "answerB","answerC", "answerD");
        quiz.AddQuestion(question);
        assertEquals("Result must be '1'", 1, quiz.getQuestions().size());
        assertSame("Result must be the same", question, quiz.getQuestions().get(0));
    }

    @Test
    public void addPlayerAnswer() throws Exception
    {
        Quiz quiz = new Quiz("QuizCode1");
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Player player3 = new Player("Player3");
        assertEquals("Result must be '0'", 0, quiz.getPlayers().size());
        QuestionAnswer questionAnswer = new QuestionAnswer(new Question("question", "answerA", "answerB","answerC", "answerD"),1);
        quiz.AddPlayerAnswer(player1, questionAnswer);
        assertEquals("Result must be '1'", 1, quiz.getPlayers().size());
        quiz.AddPlayerAnswer(player2, questionAnswer);
        assertEquals("Result must be '2'", 2, quiz.getPlayers().size());
        quiz.AddPlayerAnswer(player3, questionAnswer);
        assertEquals("Result must be '3'", 3, quiz.getPlayers().size());
        assertSame("Result must be the same", questionAnswer, quiz.getPlayers().get(0).getAllAnswers().get(0));
        assertSame("Result must be the same", questionAnswer, quiz.getPlayers().get(1).getAllAnswers().get(0));
        assertSame("Result must be the same", questionAnswer, quiz.getPlayers().get(2).getAllAnswers().get(0));
    }

}