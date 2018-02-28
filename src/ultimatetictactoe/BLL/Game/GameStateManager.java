/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.BLL.Game;

import ultimatetictactoe.BLL.Field.FieldManager;
import ultimatetictactoe.BLL.Field.IField;

/**
 *
 * @author Hussain
 */
public class GameStateManager implements IGameState
{
    IField iField;
    
    private int moveNumber;
    private int roundNumber;
    
    public GameStateManager()
    {
        iField = new FieldManager();
    }

    @Override
    public IField getField() 
    {
        return iField;
    }

    @Override
    public int getMoveNumber() 
    {
        return moveNumber;
    }

    @Override
    public void setMoveNumber(int moveNumber) 
    {
        this.moveNumber = moveNumber;
    }

    @Override
    public int getRoundNumber() 
    {
        return roundNumber;
    }

    @Override
    public void setRoundNumber(int roundNumber) 
    {
        this.roundNumber = roundNumber;
    }
    
}
