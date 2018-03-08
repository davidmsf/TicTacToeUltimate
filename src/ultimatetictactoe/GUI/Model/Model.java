/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.GUI.Model;

import ultimatetictactoe.BLL.Bot.Bot;
import ultimatetictactoe.BLL.Game.GameManager;
import ultimatetictactoe.BLL.Game.GameManager.gameOverState;
import ultimatetictactoe.BLL.Game.GameStateManager;
import ultimatetictactoe.BLL.Move.IMove;

/**
 *
 * @author Hussain
 */
public class Model {

    private GameManager gameManager;
    private Bot bot;
    private Bot bot2;
    
    public Model() 
    {
        bot = new Bot();
        bot2 = new Bot();
        gameManager = new GameManager(new GameStateManager(), bot, bot2);
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
    
    public boolean gameWon()
    {
        return gameManager.checkMacroWinner();
    }
    
    public void clearBoard()
    {
        // To be made;
    }

    public void botMove() {
        gameManager.UpdateGame();
    }

    public IMove getBotMove() {
       return gameManager.getBotMove();
    }
    
        
        public void setGameOver(gameOverState state) 
        {
        gameManager.setGameOver(state);
    }
    public gameOverState getGameOver() 
    {
        return gameManager.getGameOver();
    }
    
    public String[][] macroBoard()
    {
        return gameManager.getMacroBoard();
    }

    
}
