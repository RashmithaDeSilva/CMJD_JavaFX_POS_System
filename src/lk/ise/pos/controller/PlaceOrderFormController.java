package lk.ise.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ise.pos.DB.Database;
import lk.ise.pos.entity.Customer;
import lk.ise.pos.entity.Item;
import lk.ise.pos.view.tm.CartTM;

import java.io.IOException;
import java.util.Objects;
import java.util.Observable;
import java.util.Optional;

public class PlaceOrderFormController {

    public ComboBox<String> idCB;
    public TextField nameTxt;
    public TextField addressTxt;
    public TextField salaryTxt;
    public ComboBox<String> itemCodeCB;
    public TextField priceTxt;
    public TextField descriptionTxt;
    public TextField qtTxt;
    public TextField rqtTxt;
    public AnchorPane placeOrderFromContext;
    public TableView<CartTM> cartTbl;
    public TableColumn itemCodeCol;
    public TableColumn descriptionCol;
    public TableColumn priceCol;
    public TableColumn qtyCol;
    public TableColumn totalCol;
    public TableColumn optionCol;

    public  void initialize(){

        itemCodeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        optionCol.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadCustomersIDs();
        idCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setCustomerDate(newValue);
            }
        });

        loadItemCodes();
        itemCodeCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setItemData(newValue);
            }
        });
    }

    // Set item details
    private void setItemData(String code) {
        Item item = Database.items.stream().filter(e -> e.getCode().equals(code)).findFirst().orElse(null);
        if (item != null) {
            descriptionTxt.setText(item.getDescription());
            priceTxt.setText(String.valueOf(item.getUnitPrice()));
            qtTxt.setText(String.valueOf(item.getQtyOnHand()));
        } else {
            warningAlert("Not Found !");
        }
    }

    // Ste customer details
    private void setCustomerDate(String ID) {
        Customer customer = Database.customers.stream().filter(e -> e.getID().equals(ID)).findFirst().orElse(null);
        if (customer != null) {
            nameTxt.setText(customer.getName());
            addressTxt.setText(customer.getAddress());
            salaryTxt.setText(String.valueOf(customer.getSalary()));
        } else {
            warningAlert("Not Found !");
        }
    }

    // Load item codes
    private void loadItemCodes() {
        for (Item data : Database.items) {
            itemCodeCB.getItems().add(data.getCode());
        }
    }

    // Load customers IDs
    private void loadCustomersIDs() {
        for (Customer data : Database.customers) {
            idCB.getItems().add(data.getID());
        }

    }

    // Warning alert
    private void warningAlert(String warningMg) {
        new Alert(Alert.AlertType.WARNING,warningMg).show();
    }


    private ObservableList<CartTM> tmList = FXCollections.observableArrayList();
    public void requestQTOnAction(ActionEvent actionEvent) {

        try {
            double unitPrice = Double.parseDouble(priceTxt.getText());
            int rqt = Integer.parseInt(rqtTxt.getText());
            double total = unitPrice * rqt;
            Button btn = new Button("Delete");

            if (rqt > 0 && rqt <= Integer.parseInt(qtTxt.getText())) {
                if (isExist(itemCodeCB.getValue())) {
                    for (CartTM ctm : tmList) {
                        if (ctm.getCode().equals(itemCodeCB.getValue())) {
                            ctm.setQty(ctm.getQty()+rqt);
                            ctm.setTotal(ctm.getTotal()+total);
                            cartTbl.refresh();
                        }
                    }

                } else {
                    CartTM tm = new CartTM(itemCodeCB.getValue(),
                            descriptionTxt.getText(),
                            Double.parseDouble(priceTxt.getText()),
                            Integer.parseInt(rqtTxt.getText()),
                            total,
                            btn
                    );
                    btn.setOnAction(e->{

                    });

                    tmList.add(tm);
                    cartTbl.setItems(tmList);
                }

            } else {
                warningAlert("\"Request QT\" Value Is Invade Value !");
            }

        } catch (Exception ex) {
            if (rqtTxt.getText().equals("")) {
                warningAlert("Enter \"Request QT\" !");
            } else if (qtTxt.getText().equals("")) {
                warningAlert("Select Item !");
            } else {
                warningAlert("Enter Integer Value In To \"Request QT\" !");
            }
        }
    }
    public void addToCartOnAction(ActionEvent actionEvent) {
        requestQTOnAction(actionEvent);
    }
    private boolean isExist(String code) {
        Optional<CartTM> selectedCartTM = tmList.stream().filter(e -> e.getCode().equals(code)).findFirst();
        return selectedCartTM.isPresent();
    }

    // Back button
    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) placeOrderFromContext.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(Objects.requireNonNull(getClass()
                        .getResource("..\\view\\DashboardForm.fxml"))))
        );
        stage.centerOnScreen();
    }

}
