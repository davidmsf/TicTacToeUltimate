/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        model = new Model();
        initBoard(); 
        getAvailableMicroBoards();
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
    private void initBoard() {
        microBoards = new GridPane[3][3];
        
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
        IMove move = (IMove) ((Button) event.getSource()).getUserData();
        String XorO = model.getPlayer();
        boolean validMove = model.makeMove(move);
        if(validMove){
            Button btn = (Button)node;
            btn.setText(XorO);
        }
    }

    private void getAvailableMicroBoards() {
        String[][] availableMicroBoard = model.getAvailableMicroBoards();
        
        for(int x = 0; x < 3; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                if(availableMicroBoard[x][y].equals("1"))
                {
                    for(Node node : microBoards[x][y].getChildren())
                    {
                        Button btn = (Button) node;
                        btn.setDisable(true);
                    }
                }
            }
        }
    }

}
