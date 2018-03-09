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
    private List<IMove> madeMoves;
    private final int DEPTH = 4;
    private final int THREEONROW = 30;
    private int x;
    private int y;
    
    @Override
    public IMove doMove(IGameState state) {    
        currentState = state;
        board = currentState.getField().getBoard();
        madeMoves = new ArrayList();
        
        getFirstMove();
        int bestValue = negaMax(firstMove, DEPTH, 1, 1);
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
    
    /**
     * The Recursive method (method that calls itself) first checks if it has 
     * reached its end to do evaluations that returns the score/points for the play.
     * Then it fetches all the free cells(IMove) and foreach cell it sets the X or O
     * in the 9x9 board from cells coorordinates. Then the metod calls itself changing the player
     * and depth. When it reaches the previously mentioned end the points are returned and it then 
     * compares them with the other cells(moves) to find the best point/value
     * for the current plater which is returned to the root of the game tree. 
     * Here it finds the cell(move) with the best points.
     * https://en.wikipedia.org/wiki/Negamax
     * @param move
     * @param currentDepth
     * @param player
     * @param rootPlayer
     * @return bestValue which stems from the comparison of best points for all the cells(moves).
     */
    public int negaMax(IMove move, int currentDepth, int player, int rootPlayer)
    {
        if(checkWinner(xOrO(player)))
        {
            int points = player*calcPoints(player, rootPlayer);
            return points;
        }
        else if(currentDepth == 0 || getAvailableCells(move).isEmpty())
        {
            int points = player*checkPossiblekWinner();
            return points;
        }
        
        
        int bestValue = Integer.MIN_VALUE;
        for(IMove cell : getAvailableCells(move))
        {
            board[cell.getX()][cell.getY()] = xOrO(player);
            int currentValue = -negaMax(cell, currentDepth-1, -player, rootPlayer);
            bestValue = Math.max(bestValue, currentValue);
            //Test print of the root nodes values & coordinates 
            if(currentDepth == DEPTH)
            {
                System.out.println("------------ X: "+cell.getX()
                +" Y"+cell.getY()+" SCORE:"+currentValue+" ------------------");
            }
            if((currentValue == bestValue) && currentDepth == DEPTH)
            {
                x = cell.getX();
                y = cell.getY();
            }
            board[cell.getX()][cell.getY()] = ".";
        }
        
        return bestValue;
    }
    





    private int simple_points() {
        int point = 0;
        
        for(int i = 0; i < 3; i++)
        {
        }
        return 1;

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
    
    
    
    private boolean checkWinner(String player)
    {
        String[][] moves = currentState.getField().getMacroboard();
        if((moves[0][0].equals(moves[1][1]) && moves[1][1].equals(moves[2][2]) && moves[0][0].equals(player)) 
        || (moves[0][2].equals(moves[1][1]) && moves[1][1].equals(moves[2][0]) && moves[0][2].equals(player)))
        {
            return true;
        }

        for(int i = 0; i < 3; i++)
        {
            if((moves[i][0].equals(moves[i][1]) && moves[i][1].equals(moves[i][2]) && moves[i][0].equals(player)) 
            || (moves[0][i].equals(moves[1][i]) && moves[1][i].equals(moves[2][i]) && moves[0][i].equals(player)))
            {
                return true;
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
            point = 40;
        }
        else
        {
            point = -40;
        }
        return point;

    }

    private int checkPossiblekWinner() {
        int point = 0;
        
        //Diagonal in multidimentional array
        point += diagonalCheckLeftToRight("X"); 
        point -= diagonalCheckLeftToRight("O"); 
        point += diagonalCheckRightToLeft("X");
        point -= diagonalCheckRightToLeft("O");
        //sideways and downward in multidimentional array
        point += verticalCheck("X");
        point -= verticalCheck("O");
        point += horizontalCheck("X");
        point -= horizontalCheck("O");
        
        point += macroBoardCheck("X");
        point -= macroBoardCheck("O");
        
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
                        
                        if(board[x][y].equals(board[x+1][y+1]) 
                        && board[x+2][y+2].equals(".") 
                        && board[x][y].equals(player)) 
                        {
                            point += 2;
                        }
                        if(board[x+1][y+1].equals(board[x+2][y+2]) 
                        && board[x][y].equals(".") 
                        && board[x+1][y+1].equals(player)) 
                        {
                            point += 2;
                        }
                        
                        if(board[x][y].equals(board[x+1][y+1]) 
                        && board[x+1][y+1].equals(board[x+2][y+2]) 
                        && board[x][y].equals(player))
                        {
                            point += THREEONROW;
                          
                        }
                        
                        if(player.equals("X"))
                        {
                            if(board[x][y].equals(board[x+1][y+1]) 
                            && board[x][y].equals("O") 
                            && board[x+2][y+2].equals("X"))
                            {

                                point += THREEONROW;
                            }
                            if(board[x][y].equals(board[x+2][y+2]) 
                            && board[x][y].equals("O") 
                            && board[x+1][y+1].equals("X"))
                            {

                                point += THREEONROW;
                            }
                            if(board[x+1][y+1].equals(board[x+2][y+2]) 
                            && board[x+1][y+1].equals("O") 
                            && board[x][y].equals("X"))
                            {

                                point += THREEONROW;
                            }
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
                        
                        if(board[x][y+2].equals(board[x+1][y+1]) 
                        && board[x+2][y].equals(".") 
                        && board[x][y+2].equals(player)) 
                        {
                            point += 2;
                        }
                        if(board[x+1][y+1].equals(board[x+2][y]) 
                        && board[x][y+2].equals(".") 
                        && board[x+1][y+1].equals(player)) 
                        {
                            point += 2;
                        }
                        
                        if(board[x][y+2].equals(board[x+1][y+1]) 
                        && board[x+1][y+1].equals(board[x+2][y]) 
                        && board[x][y+2].equals(player))
                        {
                            point += THREEONROW;
                        }
                        
                        if(player.equals("X")){
                            if(board[x][y+2].equals(board[x+1][y+1]) 
                            && board[x][y+2].equals("O") 
                            && board[x+2][y].equals("X"))
                            {

                                point += THREEONROW;
                            }
                            if(board[x][y+2].equals(board[x+2][y]) 
                            && board[x][y+2].equals("O") 
                            && board[x+1][y+1].equals("X"))
                            {

                                point += THREEONROW;
                            }
                            if(board[x+1][y+1].equals(board[x+2][y]) 
                            && board[x+1][y+1].equals("O") 
                            && board[x][y+2].equals("X"))
                            {

                                point += THREEONROW;
                            }
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
                    
                    if(board[x][y].equals(board[x][y+1]) 
                    && board[x][y+2].equals(".") 
                    &&  board[x][y].equals(player)) 
                    {
                        point += 2;
                    }
                    if(board[x][y+1].equals(board[x][y+2]) 
                    && board[x][y].equals(".") 
                    && board[x][y+1].equals(player)) 
                    {
                        point += 2;

                    }
                    
                    if(board[x][y].equals(board[x][y+1]) 
                    && board[x][y+1].equals(board[x][y+2]) 
                    && board[x][y].equals(player))
                    {
                        point += THREEONROW;
                    }
                    
                    
                    
                    if(player.equals("X")){
                        if(board[x][y].equals(board[x][y+1]) 
                        && board[x][y].equals("O") 
                        && board[x][y+2].equals("X"))
                        {

                            point += THREEONROW;
                        }
                        if(board[x][y].equals(board[x][y+2]) 
                        && board[x][y].equals("O") 
                        && board[x][y+1].equals("X"))
                        {

                            point += THREEONROW;
                        }
                        if(board[x][y+1].equals(board[x][y+2]) 
                        && board[x][y+1].equals("O") 
                        && board[x][y].equals("X"))
                        {

                            point += THREEONROW;
                        }
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
                    
                    if(board[x][y].equals(board[x+1][y]) 
                    && board[x+2][y].equals(".") 
                    && board[x][y].equals(player)) 
                    {
                        point += 2;

                    }
                    
                    if(board[x+1][y].equals(board[x+2][y]) 
                    && board[x][y].equals(".") 
                    && board[x+1][y].equals(player)) 
                    {
                        point += 2;
                    }
                    


                    if(board[x][y].equals(board[x+1][y]) 
                    && board[x+1][y].equals(board[x+2][y]) 
                    && board[x][y].equals(player))
                    {
                        point += THREEONROW;
                    }
                    
                    if(player.equals("X")){
                        if(board[x][y].equals(board[x+1][y]) 
                        && board[x][y].equals("O") 
                        && board[x+2][y].equals("X"))
                        {

                            point += THREEONROW;
                        }
                        if(board[x][y].equals(board[x+2][y]) 
                        && board[x][y].equals("O") 
                        && board[x+1][y].equals("X"))
                        {

                            point += THREEONROW;
                        }
                        if(board[x+1][y].equals(board[x+2][y]) 
                        && board[x+1][y].equals("O") 
                        && board[x][y].equals("X"))
                        {

                            point += THREEONROW;
                        }
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

    private int macroBoardCheck(String player) {
        int point = 0;
        
        String[][] moves = currentState.getField().getMacroboard();

        if(moves[0][0].equals(moves[1][1]) && moves[0][0].equals(player))
        {
            point += 20;
        }
        if(moves[1][1].equals(moves[2][2]) && moves[1][1].equals(player))
        {
            point += 20;
        }
        
        if(moves[0][2].equals(moves[1][1]) && moves[0][2].equals(player))
        {
            point += 20;
        }
        if(moves[1][1].equals(moves[2][0]) && moves[1][1].equals(player))
        {
            point += 20;
        }
        
        for(int i = 0; i < 3; i++)
        {
            if(moves[i][0].equals(moves[i][1]) && moves[i][0].equals(player))
            {
                point += 20;
            }
            if(moves[i][1].equals(moves[i][2]) && moves[i][1].equals(player))
            {
                point += 20;
            }

            if(moves[0][i].equals(moves[1][i]) && moves[0][i].equals(player))
            {
                point += 20;
            }
            if(moves[1][i].equals(moves[2][i]) && moves[1][i].equals(player))
            {
                point += 20;
            }
        }
        
        return point;
    }
    
    
    
    
    private void setMadeMoves(IMove move)
    {
        madeMoves.add(move);
    }
    
    private void checkMovesHelpingOpponent(String player) {
        
        int point = 0; 
        for(IMove move : madeMoves)
        {
            int localX = move.getX() % 3;
            int localY = move.getY() % 3;

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
                for(int q = integerY;q<3+integerY;q++)
                {
                    if(board[i][q].equals(board[i][1]) && board[i][0].equals(player))
                    {
                        point += 20;
                    }
                    if(board[i][1].equals(board[i][2]) && board[i][1].equals(player))
                    {
                        point += 20;
                    }
                    
                }
            }

        }
    }
    
}
