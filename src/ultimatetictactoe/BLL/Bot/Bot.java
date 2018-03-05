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

    int x = 1;
    int y = 1;
    
    @Override
    public IMove doMove(IGameState state) {    
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
    
    
    
}
