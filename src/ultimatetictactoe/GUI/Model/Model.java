/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.GUI.Model;

import javafx.scene.layout.GridPane;
import ultimatetictactoe.BLL.Game.GameManager;
import ultimatetictactoe.BLL.Game.GameStateManager;
import ultimatetictactoe.BLL.Move.IMove;

/**
 *
 * @author Hussain
 */
public class Model {

    private GameManager gameManager;

    public Model() 
    {
        gameManager = new GameManager(new GameStateManager());
    }
    
    
    public boolean makeMove(IMove move) 
    {
       boolean validMove = gameManager.UpdateGame(move);
       return validMove;
    }

    public String getPlayer() {
        String player = gameManager.xOrO();
        return player;
    }

    public String[][] getAvailableMacroBoards() 
    {
        String[][] getAvailableMicroBoard = gameManager.getMacroBoard();
        return getAvailableMicroBoard;
    }
    
}
