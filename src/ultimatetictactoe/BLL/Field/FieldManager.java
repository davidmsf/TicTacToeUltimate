/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.BLL.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ultimatetictactoe.BLL.Move.IMove;

/**
 *
 * @author B
 */
public class FieldManager implements IField
{
    String microBoard[][];
    String macroBoard[][];
    
    public FieldManager()
    {
        microBoard = new String[3][3];
        macroBoard = new String[9][9];
    }
    
    @Override
    public void clearBoard() 
    {
        Arrays.fill(macroBoard, AVAILABLE_FIELD);
        Arrays.fill(microBoard, EMPTY_FIELD);
    }

    @Override
    public List<IMove> getAvailableMoves() {
        List<IMove> freeCells = new ArrayList();

        for(int i = 0; i < macroBoard.length; i++)
        {
            
            for(int q = 0; q < macroBoard.length; q++)
            {
                            
                int x = i;
                int y = q;
                
                if(macroBoard[x][y].equals(EMPTY_FIELD))
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
    public String getPlayerId(int column, int row) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isFull() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean isInActiveMicroboard(int x, int y) 
    {
        int macroX = x / 3;
        int macroY = y / 3;
        
        return microBoard[macroX][macroY].equals(AVAILABLE_FIELD);
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
        this.macroBoard = macroBoard;
    }
    
}
