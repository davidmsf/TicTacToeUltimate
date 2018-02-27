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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMoveNumber(int moveNumber) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRoundNumber() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRoundNumber(int roundNumber) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
