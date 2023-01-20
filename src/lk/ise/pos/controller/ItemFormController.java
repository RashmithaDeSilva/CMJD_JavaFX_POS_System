package lk.ise.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ItemFormController {
    public AnchorPane itemFormContext;
    public TextField codeTxt;
    public TextField qtOnHandTxt;
    public TextField descriptionTxt;
    public TextField priceTxt;
    public TableView tbl;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colQtOnHand;
    public TableColumn codPrice;
    public TableColumn colOption;

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) itemFormContext.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(Objects.requireNonNull(getClass()
                        .getResource("..\\view\\DashboardForm.fxml"))))
        );
        stage.centerOnScreen();
    }


    public void newItemOnAction(ActionEvent actionEvent) {

    }

    public void saveItemOnAction(ActionEvent actionEvent) {

    }

}
