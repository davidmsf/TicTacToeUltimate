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
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author B
 */
public class MainController implements Initializable {
    
    @FXML
    private GridPane MacroBoard;
    
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initBoard();
              
    }    

    @FXML
    private void macroBoardClick(MouseEvent event) 
    {
        
        for(int i = 0; i < 9; i++)
        {
            GridPane grid = (GridPane) MacroBoard.getChildren().get(i);
            for(Node node : grid.getChildren())
            {
                node.setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println(MacroBoard.getRowIndex(grid));
                        System.out.println(MacroBoard.getColumnIndex(grid));
                        System.out.println(grid.getRowIndex(node));
                        System.out.println(grid.getColumnIndex(node));
                    }
                });
            }
            
        }
    }

    private void initBoard() {
        for(int i = 0; i < 9; i++)
        {
            GridPane grid = (GridPane) MacroBoard.getChildren().get(i);
            for(int y = 0;y<3;y++)
            {
            grid.add(new Label("X"), 0, y);
            grid.add(new Label("X"), 1, y);
            grid.add(new Label("X"), 2, y);
            
            grid.add(new Label("X"), y, 0);
            grid.add(new Label("X"), y, 1);
            grid.add(new Label("X"), y, 2);
            }
        }
    }
    
}
