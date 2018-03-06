/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.BLL.Bot;

import java.util.List;
import ultimatetictactoe.BLL.Game.IGameState;
import ultimatetictactoe.BLL.Move.IMove;

/**
 *
 * @author B
 */
public class Bot implements IBot{

 
    private IGameState currentState;
    private String[][] board;
    
    @Override
    public IMove doMove(IGameState state) {    
        currentState = state;
        board = currentState.getField().getBoard();
        
        int x = 1;
        int y = 1;
        IMove iMove = new IMove() {
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
    
    public int negaMax(IMove move, int depth, int player, int rootPlayer)
    {

        if(checkWinner(move, xOrO(player)))
        {
            int points = player*calcPoints(player, rootPlayer);
            return points;
        }
        else if(depth == 0 || getAvailableCells(move).isEmpty())
        {
            int points = player*checkPossiblekWinner(rootPlayer);
            return points;
        }
        
        
        int bestValue = Integer.MIN_VALUE;
        for(IMove cell : getAvailableCells(move))
        {
            
            board[cell.getX()][cell.getY()] = xOrO(player);
            int currentValue = -negaMax(move, depth-1, -player, rootPlayer);
            bestValue = Math.max(bestValue, currentValue);
            board[cell.getX()][cell.getY()] = ".";
        }
        return bestValue;

    }
    
    
      
    public String xOrO(int player)
    {
        String symbol;
        if(player == 1)
        {
             symbol = "X";
        }
        else
        {
            symbol = "O";
        }
        return symbol;
    }
    
    
    
    private boolean checkWinner(IMove move, String player)
    {
        int localX = move.getX() / 3;
        int localY = move.getY() / 3;
        
        int integerI = 0;
        int integerY = 0;

        
        if(localX != 0)
        {
            integerI = localX*3;
        }
        if(localY != 0)
        {
            integerY = localY*3;
        }

            for(int i = integerI;i<3+integerI;i++)
            {
                for(int y = integerY;y<3+integerY;y++)
                {
                    if(board[i][y].equals(board[i][y]) 
                    && board[i][y].equals(board[i][y]) 
                    && board[i][y].equals(player))
                    {
                        return true;
                    }
                }
            }
            
            for(int y = integerI;y<3+integerI;y++)
            {
                for(int i = integerY;i<3+integerY;i++)
                {
                    if(board[i][y].equals(board[i][y]) 
                    && board[i][y].equals(board[i][y]) 
                    && board[i][y].equals(player))
                    {
                        return true; 
                    }
                }
            }
            
            return false;
    }

    private List<IMove> getAvailableCells(IMove move) {
        
    }

    private int calcPoints(int player, int rootPlayer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int checkPossiblekWinner(int rootPlayer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
