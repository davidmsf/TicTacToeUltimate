/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author B
 */
public class MainController implements Initializable {
    
    private Label label;
    @FXML
    private GridPane MacroBoard;
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
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void MacroBoardClick(MouseEvent event) {
    }
    
}
