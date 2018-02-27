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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import ultimatetictactoe.BLL.Move.IMove;
import ultimatetictactoe.GUI.Model.Model;

/**
 *
 * @author B
 */
public class MainController implements Initializable {
    

    
    @FXML
    private GridPane macroBoard;
    
    private Button[] buttons;
    
    private GridPane[][] microBoards;
    
    private Model model;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        model = new Model();
        initBoard();   
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
                        
                         IMove move = (IMove) ((Button) event.getSource()).getUserData();
                         System.out.println(move.getX()+" "+move.getY());
                    }
                });
            }
            
        }
    }
    // BoardSetup
    private void initBoard() {
        microBoards = new GridPane[3][3];
        buttons = new Button[81];
        
        for(int i = 0; i < 81; i++)
        {
            int x = i % 9;
            int y = i / 9;
        
        
        
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
            buttons[i] = button;

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
        /*
        for(int i = 0; i < 9; i++)
        {
            
            GridPane grid = (GridPane) macroBoard.getChildren().get(i);
            grid.setPadding(new Insets(20, 20, i, i));
            Button button = new Button("X");
            for(int y = 0; y < 3; y++)
            {
                
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                grid.add(button, 0, y);
                
                button = new Button("X");
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                grid.add(button, 1, y);
 
                button = new Button("X");
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                grid.add(button, 2, y);

                
            }
    
        }
        */
    }



}
