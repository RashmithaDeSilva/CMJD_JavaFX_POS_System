package lk.ise.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ise.pos.DB.Database;
import lk.ise.pos.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.Objects;

public class LoginFormController {

    public AnchorPane loginFormContext;
    public TextField usernameTxt;
    public PasswordField pwdTxt;

    public void initialize(){}

    // Login Button
    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        // Check username is correct or not
        User selectedUser = Database.users.stream()
                .filter(user -> user.getUsername()
                        .equals(usernameTxt.getText())).findFirst().orElse(null);

        // If Username is correct it will check password
        if (selectedUser != null) {

            // Check password is correct or not
            if (BCrypt.checkpw(pwdTxt.getText(),selectedUser.getPassword())) {

                // If password is correct it will show Dashboard
                Stage stage = (Stage) loginFormContext.getScene().getWindow();
                stage.setScene(
                        new Scene(FXMLLoader.load(Objects.requireNonNull(getClass()
                                .getResource("..\\view\\DashboardForm.fxml"))))
                );
                stage.centerOnScreen();

            } else { // If password is incorrect it will show warning alert
                new Alert(Alert.AlertType.WARNING,"Password Ia Incorrect!").show();
            }

        } else { // If username is incorrect it will show warning alert
            new Alert(Alert.AlertType.WARNING,"User name not Found!").show();
        }
    }

    // Password Enter
    public void pwdOnAction(ActionEvent actionEvent) throws IOException {
        loginOnAction(actionEvent);
    }

}
