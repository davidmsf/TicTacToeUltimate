/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.BLL.Bot;

import ultimatetictactoe.BLL.Game.IGameState;
import ultimatetictactoe.BLL.Move.IMove;

/**
 *
 * @author B
 */
public class Bot implements IBot{

    int x = 4;
    int y = 4;
    String macroBoard [][];
    String board[][];
    IGameState gameState;
    @Override
    public IMove doMove(IGameState state) 
    {    
        macroBoard = state.getField().getMacroboard();
        board = state.getField().getBoard();
        gameState = state;
        IMove iMove = new IMove() 
        {
            @Override
            public int getX() {
                return x;
            }

            @Override
            public int getY() {
                return y;
            }
        };
            
        return iMove;
    }
    
    public void getAvailableField()
    {
        for(int i = 0;i<3;i++)
        {
            for(int y = 0;y<3;y++)
            {
                if(macroBoard[i][y].equals("-1"))
                {
                    int ii = i*3;
                    if(board[ii].equals("."))
                    {
                        
                    }
                    
                }
            }
        }
    }
    
    
    
}
