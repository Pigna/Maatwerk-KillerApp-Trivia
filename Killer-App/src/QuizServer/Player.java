package QuizServer;

import Shared.IPlayer;

/**
 * Created by myron on 11-12-17.
 */
public class Player implements IPlayer
{
    private String name;

    public Player(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
