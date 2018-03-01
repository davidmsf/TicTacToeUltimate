package ultimatetictactoe.BLL.Game;

import ultimatetictactoe.BLL.Bot.IBot;
import ultimatetictactoe.BLL.Move.IMove;


/**
 * This is a proposed GameManager for Ultimate Tic-Tac-Toe,
 * the implementation of which is up to whoever uses this interface.
 * Note that initializing a game through the constructors means
 * that you have to create a new instance of the game manager 
 * for every new game of a different type (e.g. Human vs Human, Human vs Bot or Bot vs Bot),
 * which may not be ideal for your solution, so you could consider refactoring
 * that into an (re-)initialize method instead.
 * @author mjl
 */
public class GameManager 
{
    
    /**
     * Three different game modes.
     */
    public enum GameMode{
        HumanVsHuman,
        HumanVsBot,
        BotVsBot
    }
    
    private final IGameState currentState;
    private int currentPlayer = 0; //player0 == 0 && player1 == 1
    private GameMode mode = GameMode.HumanVsHuman;
    private IBot bot = null;
    private IBot bot2 = null;

    /**
     * Set's the currentState so the game can begin.
     * Game expected to be played Human vs Human
     * @param currentState Current game state, usually an empty board, 
     * but could load a saved game.
     */
    public GameManager(IGameState currentState) {
        this.currentState = currentState;
        mode = GameMode.HumanVsHuman;
    }
    
    /**
     * Set's the currentState so the game can begin.
     * Game expected to be played Human vs Bot
     * @param currentState Current game state, usually an empty board, 
     * but could load a saved game.
     * @param bot The bot to play against in vsBot mode.
     */
    public GameManager(IGameState currentState, IBot bot) {
        this.currentState = currentState;
        mode = GameMode.HumanVsBot;
        this.bot = bot;
    }
    
    /**
     * Set's the currentState so the game can begin.
     * Game expected to be played Bot vs Bot
     * @param currentState Current game state, usually an empty board, 
     * but could load a saved game.
     * @param bot The first bot to play.
     * @param bot2 The second bot to play.
     */
    public GameManager(IGameState currentState, IBot bot, IBot bot2) {
        this.currentState = currentState;
        mode = GameMode.BotVsBot;
        this.bot = bot;
        this.bot2 = bot2;
    }
    
    /**
     * User input driven Update
     * @param move The next user move
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean UpdateGame(IMove move)
    {
        //Verify the new move
        if(!VerifyMoveLegality(move)) 
        {   System.out.println("false move");
            return false; 
        }
        System.out.println("true move");
        //Update the currentState
        PrintDebugField(currentState.getField().getBoard());
       
        UpdateBoard(move);
        UpdateMacroboard(move);
        PrintDebugField(currentState.getField().getBoard());

       
        //Update currentPlayer
        currentPlayer = (currentPlayer + 1) % 2;
        
        return true;
    }
    
    /**
     * Non-User driven input, e.g. an update for playing a bot move.
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean UpdateGame()
    {
        //Check game mode is set to one of the bot modes.
        assert(mode != GameMode.HumanVsHuman);
        
        //Check if player is bot, if so, get bot input and update the state based on that.
        if(mode == GameMode.HumanVsBot && currentPlayer == 1)
        {
            //Check bot is not equal to null, and throw an exception if it is.
            assert(bot != null);
            
            IMove botMove = bot.doMove(currentState);
            
            //Be aware that your bots might perform illegal moves.
            return UpdateGame(botMove);
        }
        
        //Check bot is not equal to null, and throw an exception if it is.
        assert(bot != null);
        assert(bot2 != null);
        
        //TODO: Implement a bot vs bot Update.
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    private Boolean VerifyMoveLegality(IMove move)
    {
        //Test if the move is legal   
        //NOTE: should also check whether the move is placed on an occupied spot.
        System.out.println("Checking move validity against macroboard available field");
        System.out.println("Not currently checking move validity actual board");
        return currentState.getField().isInActiveMicroboard(move.getX(), move.getY());
    }
    
    private void UpdateBoard(IMove move)
    {
        // Hardcoded.

        System.out.println(move.getX() + "x" + "Y" + move.getY());
        this.currentState.getField().getBoard()[move.getX()][move.getY()]= xOrO();
    }
    
    private void UpdateMacroboard(IMove move)
    {
        int macroX = move.getX() / 3;
        int macroY = move.getY() / 3;
        System.out.println("To update whether available field or not etc");
    } 
    
    private void PrintDebugField(String[][] microBoard)
    {
        System.out.println();
        for (int x = 0; x < 9; x++)
        {
            for (int y = 0; y < 9; y++)
            {

                System.out.print(microBoard[y][x] + " ");
            }
            System.out.println();
        }
    }

    public String xOrO()
    {
        String symbol;
        if(currentPlayer == 1)
        {
             symbol = "X";
        }
        else
        {
            symbol = "O";
        }
        return symbol;
    }
    
    public String[][] getBoard()
    {
        return currentState.getField().getBoard();
    }
}
