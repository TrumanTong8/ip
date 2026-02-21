package jiarui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import jiarui.Jiarui;

public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Jiarui jiaRui;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image botImage = new Image(this.getClass().getResourceAsStream("/images/bot.png"));


    public void setJiaRui(Jiarui jiaRui) {
        this.jiaRui = jiaRui;
    }

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jiaRui.getResponse(input);

        dialogContainer.getChildren().add(DialogBox.getUserDialog(input,userImage));
        dialogContainer.getChildren().add(DialogBox.getBotDialog(response,botImage));

        userInput.clear();

        if (jiaRui.isExit()) {
            // Platform.exit();
        }
    }
}
