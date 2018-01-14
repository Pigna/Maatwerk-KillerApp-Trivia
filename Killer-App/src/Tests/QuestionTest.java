package Tests;

import QuizServer.Question;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by myron on 14-01-18.
 */
public class QuestionTest
{
    @Before
    public void setUp() throws Exception
    {

    }

    @Test
    public void getQuestion() throws Exception
    {
        Question question = new Question("question", "answerA", "answerB","answerC", "answerD");
        assertEquals("Result must be 'question'", "question", question.getQuestion());
    }

    @Test
    public void getAnswerA() throws Exception
    {
        Question question = new Question("question", "answerA", "answerB","answerC", "answerD");
        assertEquals("Result must be 'answerA'", "answerA", question.getAnswerA());
    }

    @Test
    public void getAnswerB() throws Exception
    {
        Question question = new Question("question", "answerA", "answerB","answerC", "answerD");
        assertEquals("Result must be 'answerB'", "answerB", question.getAnswerB());
    }

    @Test
    public void getAnswerC() throws Exception
    {
        Question question = new Question("question", "answerA", "answerB","answerC", "answerD");
        assertEquals("Result must be 'answerC'", "answerC", question.getAnswerC());
    }

    @Test
    public void getAnswerD() throws Exception
    {
        Question question = new Question("question", "answerA", "answerB","answerC", "answerD");
        assertEquals("Result must be 'answerD'", "answerD", question.getAnswerD());
    }

    @Test
    public void getAnswer() throws Exception
    {
        Question question = new Question("question", "answerA", "answerB","answerC", "answerD");
        assertEquals("Result must be 'answerA'", "answerA", question.getAnswer(1));
        assertEquals("Result must be 'answerB'", "answerB", question.getAnswer(2));
        assertEquals("Result must be 'answerC'", "answerC", question.getAnswer(3));
        assertEquals("Result must be 'answerD'", "answerD", question.getAnswer(4));
        assertEquals("Result must be ''", "", question.getAnswer(5));
    }

}