package Tests;

import QuizServer.Player;
import QuizServer.Question;
import QuizServer.QuestionAnswer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by myron on 14-01-18.
 */
public class PlayerTest
{
    @Before
    public void setUp() throws Exception
    {

    }

    @Test
    public void getName() throws Exception
    {
        Player testPlayer = new Player("TestName");
        assertEquals("Name must be: TestName", "TestName", testPlayer.getName());
    }

    @Test
    public void addQuestionAnswer() throws Exception
    {
        Player testPlayer = new Player("TestName");
        assertEquals("Must contain one element.", 0, testPlayer.getAllAnswers().size());
        QuestionAnswer questionAnswer = new QuestionAnswer(new Question("Vraag", "", "","",""), 1);
        testPlayer.AddQuestionAnswer(questionAnswer);
        assertEquals("Must contain one element.", 1, testPlayer.getAllAnswers().size());
        assertSame("Must contain same element.", questionAnswer, testPlayer.getAllAnswers().get(0));
    }

    @Test
    public void getAllAnswers() throws Exception
    {
        Player testPlayer = new Player("TestName");
        assertEquals("Must contain one element.", 0, testPlayer.getAllAnswers().size());
        testPlayer.AddQuestionAnswer(new QuestionAnswer(new Question("Vraag", "", "","",""), 1));
        testPlayer.AddQuestionAnswer(new QuestionAnswer(new Question("Vraag", "", "","",""), 1));
        testPlayer.AddQuestionAnswer(new QuestionAnswer(new Question("Vraag", "", "","",""), 1));
        assertEquals("Must contain one element.", 3, testPlayer.getAllAnswers().size());
    }

}