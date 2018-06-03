package ru.itis.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * Date 03.06.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
public class ModalController {


    @FXML
    public Button submit;
    @FXML
    public TextField textField;

    public void submitBtn(ActionEvent actionEvent) {
        String username = textField.getText();
        if (!username.equals("")){
            FlappyBird.message.setUsername(username);
            FlappyBird.modalStage.close();
        }
    }
}
