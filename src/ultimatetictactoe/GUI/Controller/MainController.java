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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author B
 */
public class MainController implements Initializable {
    

    @FXML
    private GridPane microBoard;
    @FXML
    private GridPane gridPaneLeftTop;
    @FXML
    private GridPane gridPaneMiddleLeft;
    @FXML
    private GridPane gridPaneLeftBottom;
    @FXML
    private GridPane gridPaneMiddleTop;
    @FXML
    private GridPane gridPaneMiddle;
    @FXML
    private GridPane gridPaneMiddleBottom;
    @FXML
    private GridPane gridPaneTopRight;
    @FXML
    private GridPane gridPaneMiddleRight;
    @FXML
    private GridPane gridPaneRightBottom;
    
    private Button selectedButton;
    
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        initBoard();   
        initClickEvent();
    }    

    @FXML
    private void macroBoardClick(MouseEvent event) 
    {
        
 
    }

    private void initClickEvent()
    {
    
        for(int i = 0; i < 9; i++)
        {
            GridPane grid = (GridPane) microBoard.getChildren().get(i);
            for(Node node : grid.getChildren())
            {
                node.setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) 
                    {
                        selectedButton = (Button) node;
                        
                        System.out.println(GridPane.getRowIndex(grid));
                        System.out.println(GridPane.getColumnIndex(grid));
                        System.out.println(grid.getRowIndex(node));
                        System.out.println(grid.getColumnIndex(node));
                    }
                });
            }
            
        }
    }
    // BoardSetup
    private void initBoard() {
        for(int i = 0; i < 9; i++)
        {
            
            GridPane grid = (GridPane) microBoard.getChildren().get(i);
            grid.setPadding(new Insets(20, 20, i, i));
            
            for(int y = 0; y < 3; y++)
            {
                Button button = new Button("X");
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
    }
}
