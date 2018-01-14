package Tests;

import QuizServer.Question;
import QuizServer.QuestionAnswer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by myron on 14-01-18.
 */
public class QuestionAnswerTest
{
    @Before
    public void setUp() throws Exception
    {

    }

    @Test
    public void getQuestion() throws Exception
    {
        Question question = new Question("question", "answerA", "answerB","answerC", "answerD");
        QuestionAnswer questionAnswer = new QuestionAnswer(question, 1);
        assertSame("Must be same object", question, questionAnswer.getQuestion());
    }

    @Test
    public void getAnswer() throws Exception
    {
        Question question = new Question("question", "answerA", "answerB","answerC", "answerD");
        QuestionAnswer questionAnswer1 = new QuestionAnswer(question, 1);
        QuestionAnswer questionAnswer2 = new QuestionAnswer(question, 2);
        QuestionAnswer questionAnswer3 = new QuestionAnswer(question, 3);
        QuestionAnswer questionAnswer4 = new QuestionAnswer(question, 4);
        assertEquals("Answer must be: 1", 1, questionAnswer1.getAnswer());
        assertEquals("Answer must be: 2", 2, questionAnswer2.getAnswer());
        assertEquals("Answer must be: 3", 3, questionAnswer3.getAnswer());
        assertEquals("Answer must be: 4", 4, questionAnswer4.getAnswer());
    }

}