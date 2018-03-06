/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.BLL.Bot;

import java.util.ArrayList;
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
    private IMove firstMove;
    private int x;
    private int y;
    
    @Override
    public IMove doMove(IGameState state) {    
        currentState = state;
        board = currentState.getField().getBoard();
        getFirstMove();
        int bestValue = negaMax(firstMove, 4, 1, 1);
        System.out.println("!!!POINT: "+bestValue+" "+x+" "+y);
        

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
            printBoard();
            int currentValue = -negaMax(cell, depth-1, -player, rootPlayer);
            bestValue = Math.max(bestValue, currentValue);
            if((currentValue == bestValue) && depth == 4)
            {
                x = cell.getX();
                y = cell.getY();
            }
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
                    if(y % 3 == 0 || y == 0)
                    {
                        
                        if(board[i][y].equals(board[i][y+1]) 
                        && board[i][y+1].equals(board[i][y+2]) 
                        && board[i][y].equals(player))
                        {
                            return true;
                        }
                    }
                }
            }
            
            for(int y = integerI;y<3+integerI;y++)
            {
                for(int i = integerY;i<3+integerY;i++)
                {
                    if(i % 3 == 0 || i == 0)
                    {
                        if(board[i][y].equals(board[i+1][y]) 
                        && board[i+1][y].equals(board[i+2][y]) 
                        && board[i][y].equals(player))
                        {
                            return true; 
                        }
                    }
                }
            }
            
            return false;
    }

    private List<IMove> getAvailableCells(IMove move) {
        int localX = move.getX() % 3;
        int localY = move.getY() % 3;
        
        int integerI = 0;
        int integerY = 0;
        
        List<IMove> availableCells = new ArrayList();
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
                for(int q = integerY;q<3+integerY;q++)
                {
                    int x = i;
                    int y = q;
                    
                    if(board[i][y].equals("."))
                    {
                        availableCells.add(new IMove() {
                            @Override
                            public int getX() {
                                return x;
                            }

                            @Override
                            public int getY() {
                                return y;
                            }
                        });
                    }
                }
            }
            
        return availableCells;
    }

    private int calcPoints(int player, int rootPlayer) 
    {
        int point = 0;
        if(player == rootPlayer){
            point = 10;
        }
        else
        {
            point = 1;
        }
        return point;

    }

    private int checkPossiblekWinner(int rootPlayer) {
        int point = 2;
        
        //Diagonal in multidimentional array
        point += diagonalCheckLeftToRight(xOrO(rootPlayer)); 
        point += diagonalCheckRightToLeft(xOrO(rootPlayer));
        //sideways og downward in multidimentional array
        point += verticalCheck(xOrO(rootPlayer));
        point += horizontalCheck(xOrO(rootPlayer));
        
        return point;
    }
    
    
    private int diagonalCheckLeftToRight(String player) 
    {
        int point = 0;
        
        for(int x = 0; x < 7; x++)
        {
            if(x % 3 == 0 || x == 0)
            {
                for(int y = 0; y < 7; y++)
                {
                    if(y % 3 == 0 || y == 0)
                    {
                        if(board[x][y].equals(board[x+1][y+1]) && board[x][y].equals(player)) 
                        {
                            point += 4;
                        }
                        if(board[x+1][y+1].equals(board[x+2][y+2]) && board[x+1][y+1].equals(player)) 
                        {
                            point += 4;
                        }
                    }

                }
            }
        }
        
       return point;

        
    }
  
    
    
    private int diagonalCheckRightToLeft(String player) 
    {
        int point = 0;
        
        for(int x = 0; x < 7; x++)
        {
            if(x % 3 == 0 || x == 0)
            {
                for(int y = 0; y < 7; y++)
                {
                    if(y % 3 == 0 || y == 0)
                    {
                        
                        if(board[x][y+2].equals(board[x+1][y+1]) && board[x][y+2].equals(player)) 
                        {
                            point += 4;
                        }
                        if(board[x+1][y+1].equals(board[x+2][y]) && board[x+1][y+1].equals(player)) 
                        {
                            point += 4;
                        }
                        
                    }

                }
            }
        }
        
        return point;

    }
    
    
    
    private int verticalCheck(String player)
    {
        int point = 0;
        for(int x = 0; x < 9; x++)
        {
            for(int y = 0; y < 7; y++)
            {
                if(y % 3 == 0 || y == 0)
                {
                    
                    if(board[x][y].equals(board[x][y+1]) && board[x][y].equals(player)) 
                    {
                        point += 4;
                    }
                    if(board[x][y+1].equals(board[x][y+2]) && board[x][y+1].equals(player)) 
                    {
                        point += 4;

                    }
                    
                }

            }
        }
        return point;
    }
    
    
    
    private int horizontalCheck(String player)
    {
        int point = 0;
        for(int y = 0; y < 9; y++)
        {
            for(int x = 0; x < 7; x++)
            {
                if(x % 3 == 0 || x == 0)
                {
                    
                    if(board[x][y].equals(board[x+1][y]) && board[x][y].equals(player)) 
                    {
                        point += 4;

                    }
                    if(board[x+1][y].equals(board[x+2][y]) && board[x+1][y].equals(player)) 
                    {
                        point += 4;
                    }
                }

            }
        }
        return point;
    }
    
    
    
    private void printBoard()
    {
        for(int x = 0; x < 9; x++)
        {
            for(int y = 0; y < 9; y++)
            {
                System.out.print(board[x][y]);
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
    }

    private void getFirstMove() {
        String[][] macroBoard = currentState.getField().getMacroboard();
        for(int x = 0; x < macroBoard.length; x++)
        {
            for(int y = 0; y < macroBoard.length; y++)
            {
                if(macroBoard[x][y].equals("-1"))
                {
                    int i = x;
                    int q = y;
                    firstMove = new IMove() {
                        @Override
                        public int getX() {
                            return i;
                        }

                        @Override
                        public int getY() {
                            return q;
                        }
                    };
                    
                }
            }
        }
        
    }
    
}
