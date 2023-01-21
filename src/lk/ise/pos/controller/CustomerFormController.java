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
import lk.ise.pos.dao.DataAccessCode;
import lk.ise.pos.entity.Customer;
import lk.ise.pos.view.tm.CustomerTm;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class CustomerFormController {
    public AnchorPane customerFormContext;
    public TextField idtxt;
    public TextField nameTxt;
    public TextField addressTxt;
    public TextField salaryTxt;
    public TableView<CustomerTm> tbl;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colOption;
    public Button btn;


    // Set initializer
    public void initialize(){
        loadAll("");
        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("Button"));

        tbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setData(newValue);
            }
        });
    }

    // Get values
    private void setData(CustomerTm newValue) {
        idtxt.setText(newValue.getID());
        nameTxt.setText(newValue.getName());
        addressTxt.setText(newValue.getAddress());
        salaryTxt.setText(String.valueOf(newValue.getSalary()));
        btn.setText("Update Customer");
    }

    // Back to dashboard
    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException{
        Stage stage = (Stage) customerFormContext.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(Objects.requireNonNull(getClass()
                        .getResource("..\\view\\DashboardForm.fxml"))))
        );
        stage.centerOnScreen();
    }

    // Save customer button
    public void saveCustomerOnAction(ActionEvent actionEvent) {

        if (!idtxt.getText().equals("")) {
            if (!nameTxt.getText().equals("")) {
                if (!addressTxt.getText().equals("")) {
                    if (!salaryTxt.getText().equals("")) {

                        try {

                            // Get customer details
                            Customer customer = new Customer(
                                    idtxt.getText(),nameTxt.getText(),addressTxt.getText(),Double.parseDouble(salaryTxt.getText())
                            );

                            // If button is save mod this code will happen
                            if (btn.getText().equals("Save")) {

                                try {
                                    if (new DataAccessCode().saveCustomer(customer)) {
                                        new Alert(Alert.AlertType.INFORMATION, "Customer Save Successfully").show();
                                        loadAll("");
                                    } else {
                                        new Alert(Alert.AlertType.WARNING, "Something went Wrong!").show();
                                    }
                                    // Reset all inputs
                                    clearData();

                                    // Reload customer table
                                    loadAll("");

                                }catch (ClassNotFoundException | SQLException e){
                                    //e.printStackTrace();
                                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                                }

                                // Add customer in to database
                                //Database.customers.add(customer);

                                // Show successful alert
                                //new Alert(Alert.AlertType.INFORMATION,"Customer Save Successfully").show();

                            } else { // If button is update mode it will happen this code

                                try {
                                    if (new DataAccessCode().updateCustomer(customer)) {
                                        new Alert(Alert.AlertType.INFORMATION, "Customer Update Successfully").show();
                                        loadAll("");
                                    } else {
                                        new Alert(Alert.AlertType.WARNING, "Something went Wrong!").show();
                                    }
                                }catch (ClassNotFoundException | SQLException e){
                                    //e.printStackTrace();
                                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                                }

//                                int validID = 0;
//
//                                // This code will update customer new details
//                                for (Customer c : Database.customers) {
//                                    if (c.getID().equals(idtxt.getText())) {
//                                        c.setName(customer.getName());
//                                        c.setAddress(customer.getAddress());
//                                        c.setSalary(customer.getSalary());
//
//                                        // Show successful alert
//                                        new Alert(Alert.AlertType.INFORMATION,"Customer Update Successfully").show();
//
//                                        // Reset all inputs
//                                        clearData();
//
//                                        // Reload customer table
//                                        lodAll("");
//
//                                        btn.setText("Save");
//                                        validID++;
//                                        break;
//                                    }
//                                }
//
//                                if (validID == 0) {
//                                    // Show warning alert
//                                    warningAlert("Invalid ID !");
//                                }

                            }

                        }  catch (NumberFormatException NFE) {
                            // If salary is not integer or double it will show warning alert
                            warningAlert("Salary Request Type Is \nInteger Or Double Value !");

                        } catch (Exception e) {
                            // If eny error happen it will show this error massage
                            warningAlert("Enter Customer Detail Correctly !");
                        }

                    } else {
                        warningAlert("Enter Salary !");
                    }
                } else {
                    warningAlert("Enter Address !");
                }
            } else {
                warningAlert("Enter Name !");
            }
        } else {
            warningAlert("Enter ID !");
        }

    }

    // New customer
    public void newCustomerOnAction(ActionEvent actionEvent) {
        clearData();
        btn.setText("Save");
    }

    // Warning alert
    private void warningAlert(String warningMg) {
        new Alert(Alert.AlertType.WARNING,warningMg).show();
    }

    // Reset inputs
    private void clearData() {
        idtxt.clear();
        nameTxt.clear();
        addressTxt.clear();
        salaryTxt.clear();
    }

    // Load customers
    private void loadAll(String searchText) {
        ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();
        for (Customer c : Database.customers) {
            Button btn = new Button("Delete");
            CustomerTm tm = new CustomerTm(
                    c.getID(), c.getName(), c.getAddress(), c.getSalary(), btn
            );

            btn.setOnAction((e)->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure ?",
                        ButtonType.YES,ButtonType.NO);
                Optional<ButtonType> type = alert.showAndWait();
                if (type.get() == ButtonType.YES) {
                    Database.customers.remove(c);
                    new Alert(Alert.AlertType.INFORMATION,"Successfully Delete Customer!").show();
                    loadAll("");
                }
            });

            tmList.add(tm);
        }
        tbl.setItems(tmList);
    }

}
