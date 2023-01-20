package lk.ise.pos.view.tm;

import javafx.scene.control.Button;

public class CartTM {
     private String code;
     private String description;
     private double price;
     private int qty;
     private double total;
     private Button btn;


     // Constructors
    public CartTM() {
    }
    public CartTM(String code, String description, double price, int qty, double total, Button btn) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.qty = qty;
        this.total = total;
        this.btn = btn;
    }


    // Set Methods
    public void setCode(String code) {
        this.code = code;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public void setBtn(Button btn) {
        this.btn = btn;
    }

    // Get Methods
    public String getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public double getPrice() {
        return price;
    }
    public int getQty() {
        return qty;
    }
    public double getTotal() {
        return total;
    }
    public Button getBtn() {
        return btn;
    }

}
