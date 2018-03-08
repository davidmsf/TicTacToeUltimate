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
    @Override
    public IMove doMove(IGameState state) 
    {    
        macroBoard = state.getField().getMacroboard();
        board = state.getField().getBoard();
        getAvailableField();
        
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
                    int yy = y*3;
                    if(board[ii+1][yy+1].equals("."))
                    {
                        this.x = ii+1;
                        this.y = yy+1;
                    }
                    else if(board[ii][yy].equals("."))
                    {
                        this.x = ii;
                        this.y = yy;
                    }
                    else if(board[ii+2][yy+2].equals("."))
                    {
                        this.x = ii;
                        this.y = yy;
                    }
                }
            }
        }
    }
    
    
    
}
