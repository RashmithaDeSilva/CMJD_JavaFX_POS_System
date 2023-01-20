package lk.ise.pos.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DashboardFormController {
    public AnchorPane dashboardContext;
    public Label timeTxt;
    public Label dateTxt;

    public void initialize(){
        manageDateAndTime();
    }

    // Set time and date
    private void manageDateAndTime() {

        // Set date
        Timeline date = new Timeline(new KeyFrame(Duration.ZERO,e->
            dateTxt.setText(LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))),
                new KeyFrame(Duration.seconds(1)));

        date.setCycleCount(Animation.INDEFINITE);
        date.play();

        // Set time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO,e->
                timeTxt.setText(LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("HH:mm:ss")))),
                new KeyFrame(Duration.seconds(1)));

        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    //
    private void setUI(String location) throws IOException {
        Stage stage = (Stage) dashboardContext.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(Objects.requireNonNull(getClass()
                        .getResource("..\\view\\"+location+".fxml"))))
        );
        stage.centerOnScreen();
    }

    // Logout button
    public void logoutOnAction(ActionEvent actionEvent) {

        // Show alert and conform user want to log out
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?",ButtonType.YES,ButtonType.NO);
        alert.showAndWait().ifPresent(buttonType -> {

            // If user click "Yes" button it will log out
            if (buttonType == ButtonType.YES) {

                // Set login form
                try {
                    setUI("LoginForm");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                // Show successful alert
                new Alert(Alert.AlertType.INFORMATION,"Successfully Logout !").show();
            }
        });
    }

    // Sel customer form
    public void openCustomerFormOnAction(ActionEvent actionEvent) throws IOException {
        setUI("CustomerForm");
    }

    public void itemFormOnAction(ActionEvent actionEvent) throws IOException {
        setUI("ItemForm");
    }

    public void newOrderFormOnAction(ActionEvent actionEvent) throws IOException {
        setUI("PlaceOrderForm");
    }

    public void orderFromOnAction(ActionEvent actionEvent) {
    }

    public void IncomeFormOnAction(ActionEvent actionEvent) {
    }
}
