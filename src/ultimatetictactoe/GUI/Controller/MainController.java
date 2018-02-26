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
    private void MacroBoardClick(MouseEvent event) 
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

            Label label = new Label();
            grid.add(label, 1, 0);
            label = new Label();
            grid.add(label, 1, 1);
            label = new Label();
            grid.add(label, 1, 2);
            label = new Label();
            grid.add(label, 0, 0);
            label = new Label();
            grid.add(label, 0, 1);
            label = new Label();
            grid.add(label, 0, 2);
            label = new Label();
            grid.add(label, 2, 0);
            label = new Label();
            grid.add(label, 2, 1);
            label = new Label();
            grid.add(label, 2, 2);  
            
            
        }
    }
    
}
