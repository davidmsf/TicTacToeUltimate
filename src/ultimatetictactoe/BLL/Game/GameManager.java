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
    public GameManager(IGameState currentState, IBot bot, IBot bot2) 
    {
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
        {   
            return false; 
        }

        UpdateBoard(move);
        allFieldsAvailable(move);
        checkMicroWinner();
        Boolean gameWon = checkMacroWinner();
        if(gameWon)
        {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!GAME WON GAME WON");
        }
        UpdateMacroboard(move);
     

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

        return currentState.getField().isInActiveMicroboard(move.getX(), move.getY());
    }
    
    private void UpdateBoard(IMove move)
    {     
        this.currentState.getField().getBoard()[move.getX()][move.getY()] = xOrO();
    }
    
    private void UpdateMacroboard(IMove move)
    {
        int macroX = move.getX() / 3;
        int macroY = move.getY() / 3;   
        
        int macroXX = move.getX() % 3;
        int macroYY = move.getY() % 3;
        
        String[][] macroBoard = this.currentState.getField().getMacroboard();
        
        if(macroBoard[macroXX][macroYY].equals("X") 
        || macroBoard[macroXX][macroYY].equals("O")
        || macroBoard[macroXX][macroYY].equals("F"))
        {
            System.out.println("XorO");
            for(int x = 0; x < 3; x++)
            {
                for(int y = 0; y < 3; y++)
                {
                    if(!(macroBoard[x][y].equals("X") 
                    || macroBoard[x][y].equals("O")
                    || macroBoard[x][y].equals("F")))
                    {
                        macroBoard[x][y] = "-1";
                    }
                    
                }
            }
            
        }
        else
        {
            System.out.println("RegularMove");
            for(int x = 0; x < 3; x++)
            {
                for(int y = 0; y < 3; y++)
                {
                    if(!(macroBoard[x][y].equals("X") 
                    || macroBoard[x][y].equals("O")
                    || macroBoard[x][y].equals("F")))
                    {
                        macroBoard[x][y] = "1";
                    }
                }
            }
            
            macroBoard[macroXX][macroYY] = "-1";
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
    
    public String[][] getMacroBoard()
    {
        return currentState.getField().getMacroboard();
    }
    

    private boolean checkMacroWinner()
    {
            String player = xOrO();

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
    
    public void checkMicroWinner()
    {

        String[][] board = this.currentState.getField().getBoard();
        String player = xOrO();
                
        horizontalCheck(board, player);
        verticalCheck(board, player);
        diagonalCheckLeftToRight(board, player);
        diagonalCheckRightToLeft(board, player);
        
    }        
       
    
    
    private void diagonalCheckLeftToRight(String[][] board, String player) 
    {
        
        for(int x = 0; x < 7; x++)
        {
            if(x % 3 == 0 || x == 0)
            {
                for(int y = 0; y < 7; y++)
                {
                    if(y % 3 == 0 || y == 0)
                    {
                        if(board[x][y].equals(board[x+1][y+1]) 
                        && board[x+1][y+1].equals(board[x+2][y+2]) 
                        && board[x][y].equals(player))
                        {
                            int macroX = x / 3;
                            int macroY = y / 3;
                            getMacroBoard()[macroX][macroY] = player;
                        }
                    }

                }
            }
        }
        
    }
  
    
    
    private void diagonalCheckRightToLeft(String[][] board, String player) 
    {
        for(int x = 0; x < 7; x++)
        {
            if(x % 3 == 0 || x == 0)
            {
                for(int y = 0; y < 7; y++)
                {
                    if(y % 3 == 0 || y == 0)
                    {
                        if(board[x][y+2].equals(board[x+1][y+1]) 
                        && board[x+1][y+1].equals(board[x+2][y]) 
                        && board[x][y+2].equals(player))
                        {
                            int macroX = x / 3;
                            int macroY = y / 3;
                            getMacroBoard()[macroX][macroY] = player;
                        }
                    }

                }
            }
        }
    }
    
    
    
    private void verticalCheck(String[][] board, String player)
    {
        
        for(int x = 0; x < 9; x++)
        {
            for(int y = 0; y < 7; y++)
            {
                if(y % 3 == 0 || y == 0)
                {

                    if(board[x][y].equals(board[x][y+1]) 
                    && board[x][y+1].equals(board[x][y+2]) 
                    && board[x][y].equals(player))
                    {
                        int macroX = x / 3;
                        int macroY = y / 3;
                        getMacroBoard()[macroX][macroY] = player;
                    }
                }

            }
        }
    
    }
    
    
    
    private void horizontalCheck(String[][] board, String player)
    {
        for(int y = 0; y < 9; y++)
        {
            for(int x = 0; x < 7; x++)
            {
                if(x % 3 == 0 || x == 0)
                {

                    if(board[x][y].equals(board[x+1][y]) 
                    && board[x+1][y].equals(board[x+2][y]) 
                    && board[x][y].equals(player))
                    {

                        int macroX = x / 3;
                        int macroY = y / 3;
                        getMacroBoard()[macroX][macroY] = player;
                    }
                }

            }
        }
    }
    
    
    
    public void allFieldsAvailable(IMove move)
    {
        int localX = move.getX() / 3;
        int localY = move.getY() / 3;
        
        int integerI = 0;
        int integerY = 0;
        boolean isFull = true;
        
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
                    Boolean emptyCell = currentState.getField().getBoard()[i][y].equals(".");
                    
                    if(emptyCell)
                    {
                        isFull = false;
                        System.out.println("ISnotFULL");
                    }
                }
            }
            
        if(isFull)
        {
            System.out.println("ISFULL");
            this.currentState.getField().getMacroboard()[localX][localY] = "F";
        }
    }

    

}
