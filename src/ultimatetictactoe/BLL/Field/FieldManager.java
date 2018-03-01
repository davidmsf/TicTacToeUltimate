/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.BLL.Field;

import java.util.ArrayList;
import java.util.List;
import ultimatetictactoe.BLL.Move.IMove;

/**
 *
 * @author B
 */
public class FieldManager implements IField
{
    String[][] macroBoard;
    String[][] microBoard;
    
    final String not_Available_Field = "1";
    
    public FieldManager()
    {
        macroBoard = new String[3][3];
        
        microBoard = new String[9][9];
        clearBoard();
    }
    
    @Override
    public void clearBoard() 
    {
       for(int i = 0;i<3;i++)
       {
           for(int y = 0;y<3;y++)
           {
               macroBoard[i][y] = not_Available_Field;
             
           }
       }
        macroBoard[1][1] = AVAILABLE_FIELD;
        
        for(int x = 0;x<9;x++)
        {
            for (int i = 0; i < 9; i++) 
            {
                microBoard[x][i] = EMPTY_FIELD;
                
            }
        }
    }

    @Override
    public List<IMove> getAvailableMoves() {
        List<IMove> freeCells = new ArrayList();

        for(int i = 0; i < microBoard.length; i++)
        {
            
            for(int q = 0; q < microBoard.length; q++)
            {
                            
                int x = i;
                int y = q;
                
                if(microBoard[x][y].equals(EMPTY_FIELD))
                {
                    freeCells.add(new IMove() {
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

        return freeCells;
    }

    @Override
    public String getPlayerId(int column, int row) 
    {
        return microBoard[column][row];
    }

    @Override
    public boolean isEmpty()
    {
//        for(IMove imove : getAvailableMoves())
//        {
//           if(imove.getX() == playerMove.getX() && imove.getY() == playerMove.getY());
//           {
//           return true;
//           }
//        }
//        return false;
        
        throw new UnsupportedOperationException("Not supported YET");
        
    }

    @Override
    public boolean isFull() 
    {
        return getAvailableMoves().isEmpty();
    }
    /**
     * Checks if it is in active microboard, and if the cell is empty.
     * @param x
     * @param y
     * @return 
     */
    @Override
    public Boolean isInActiveMicroboard(int x, int y) 
    {
        int macroX = x / 3;
        int macroY = y / 3;
        Boolean emptyCell = microBoard[x][y].equals(EMPTY_FIELD);
        Boolean availableField = macroBoard[macroX][macroY].equals(AVAILABLE_FIELD);

        if(availableField)
        {
            if(emptyCell)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public String[][] getBoard() 
    {
        return microBoard;
    }

    @Override
    public String[][] getMacroboard() 
    {
      return macroBoard;
    }

    @Override
    public void setBoard(String[][] board)
    {
        this.microBoard = board;
    }

    @Override
    public void setMacroboard(String[][] macroboard) 
    {
        this.macroBoard = microBoard;
    }
    
    
}
