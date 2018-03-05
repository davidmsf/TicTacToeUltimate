/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import ultimatetictactoe.BLL.Move.IMove;
import ultimatetictactoe.GUI.Model.Model;

/**
 *
 * @author B
 */
public class MainController implements Initializable {
    

    
    @FXML
    private GridPane macroBoard;
    
   
    private GridPane[][] microBoards;
    
    private Model model;
    @FXML
    private Label displayGameInfo;
    
    private Button[][] buttons;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        model = new Model();
        initBoard(); 
        getAvailableMacroBoards();
        initClickEvent();
    }    



    private void initClickEvent()
    {
    
        for(int i = 0; i < 9; i++)
        {
            GridPane grid = (GridPane) macroBoard.getChildren().get(i);
            for(Node node : grid.getChildren())
            {
                node.setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) 
                    {         
                        setMove(event, node);
                    }
                });
            }
            
        }
    }
    
    
    
    /**
     *  BoardSetup
     */
    private void initBoard() 
    {
        microBoards = new GridPane[3][3];
        buttons = new Button[9][9];
        
        for(int i = 0; i < 9; i++)
        {
            
            for(int q = 0; q < 9; q++)
            {
                int x = i;
                int y = q;

                
                if (microBoards[x / 3][y / 3] == null)
                {
                    microBoards[x / 3][y / 3] = new GridPane();
                    macroBoard.add(microBoards[x / 3][y / 3], x / 3, y / 3);
                }
                GridPane microBoard = microBoards[x / 3][y / 3];
                microBoard.getColumnConstraints().add(new ColumnConstraints(80));

                Button button = new Button();
                buttons[i][q] = button;
                GridPane.setVgrow(button, Priority.ALWAYS);
                button.setMaxSize(40, 40);
                microBoard.add(button, x % 3, y % 3);

                button.setUserData(new IMove()
                {
                    @Override
                    public int getX()
                    {
                        return x;
                    }

                    @Override
                    public int getY()
                    {
                        return y;
                    }
                });
                }
        }

    }

    private void setMove(MouseEvent event, Node node)
    {
        String XorO = model.getPlayer();
        IMove move = (IMove) ((Button) event.getSource()).getUserData();
        
        boolean validMove = model.makeMove(move);
        if(validMove)
        {
            Button btn = (Button)node;
            btn.setText(XorO);
            setMoveStyle(XorO, btn);
            getAvailableMacroBoards();  
        }
        
        checkGameWin(XorO); 
        String XorObot = model.getPlayer();
        botMove(XorObot);
        checkGameWin(XorObot); 
    }

    private void checkGameWin(String XorO) {
        if (model.gameWon())
        {
            makeWonMessage(XorO);
            stopGame();
        }
    }


    private void getAvailableMacroBoards() {
        String[][] availableMacroBoard = model.getAvailableMacroBoards();
    
        for(int x = 0; x < 3; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                    System.out.println(availableMacroBoard[x][y]);
                    for(Node node : microBoards[x][y].getChildren())
                    {  
                        
                        if(!availableMacroBoard[x][y].equals("-1"))
                        {
                            Button btn = (Button) node;
                            btn.setDisable(true);
                        }
                        else
                        {
                            Button btn = (Button) node;
                            btn.setDisable(false);
                        }
                        
                        if(availableMacroBoard[x][y].equals("X") )
                        {
                            Button btn = (Button) node;
                            btn.setStyle("-fx-background-color: green");
                        }
                        else if(availableMacroBoard[x][y].equals("O"))
                        {
                            Button btn = (Button) node;
                            btn.setStyle("-fx-background-color: blue");
                        }
                        
                    }
                
            }
        }
    }
    
    
    
    private void setMoveStyle(String XorO, Button btn) 
    {
        if(XorO.equals("X"))
        {
            btn.setStyle("-fx-background-color: green; "
                    + "-fx-text-fill: white; "
                    + "-fx-font-weight: bold;");
            displayGameInfo.setAlignment(Pos.CENTER);
            displayGameInfo.setText("Turn: O");
            displayGameInfo.setStyle("-fx-background-color: blue");
        }
        else
        {
            btn.setStyle("-fx-background-color: blue; "
                    + "-fx-text-fill: white; "
                    + "-fx-font-weight: bold;");
            displayGameInfo.setAlignment(Pos.CENTER);
            displayGameInfo.setText("Turn: X");
            displayGameInfo.setStyle("-fx-background-color: green");
        }
    }
    
    private void makeWonMessage(String player)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Info");
        alert.setHeaderText(null);
        alert.setContentText(player + " " + "Won the game!!!");
        alert.show();
    }
    
    private void stopGame()
    {
        for(int i = 0;i<buttons.length;i++)
        {
            for(int y = 0;y<buttons.length;y++)
            {
                buttons[i][y].setDisable(true);
            }
        }
    }

    @FXML
    private void resetGame(ActionEvent event) 
    {
    }

    private void botMove(String XorO) {
        model.botMove();
        IMove botMove = model.getBotMove();
        Button btn = (Button)buttons[botMove.getX()][botMove.getY()];
        System.out.println(botMove.getX()+" !!!"+botMove.getY());
        btn.setText(XorO);
        setMoveStyle(XorO, btn);
        getAvailableMacroBoards(); 
    }

}
