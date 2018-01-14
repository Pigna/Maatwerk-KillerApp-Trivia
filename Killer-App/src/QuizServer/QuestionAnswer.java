package QuizServer;

import Shared.IQuestion;
import Shared.IQuestionAnswer;

/**
 * Created by myron on 14-01-18.
 */
public class QuestionAnswer implements IQuestionAnswer
{
    IQuestion question;
    int answer;

    public QuestionAnswer(IQuestion question, int answer)
    {
        this.question = question;
        this.answer = answer;
    }

    @Override
    public IQuestion getQuestion()
    {
        return question;
    }

    @Override
    public int getAnswer()
    {
        return answer;
    }
}
