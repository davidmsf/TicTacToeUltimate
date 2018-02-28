/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.GUI.Model;

import ultimatetictactoe.BLL.Game.GameManager;
import ultimatetictactoe.BLL.Game.GameStateManager;
import ultimatetictactoe.BLL.Move.IMove;

/**
 *
 * @author Hussain
 */
public class Model {

    private GameManager gameManager;

    public Model() {
        gameManager = new GameManager(new GameStateManager());
    }
    
    
    public void makeMove(IMove move) {
        gameManager.UpdateGame(move);
    }
    
}
