package lk.ise.pos.view.tm;

import javafx.scene.control.Button;

public class CustomerTm {

    private String ID;
    private String name;
    private String address;
    private double salary;
    private Button button;


    public CustomerTm(){}
    public CustomerTm(String ID, String name, String address, double salary,Button button) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.button = button;
    }


    public void setID(String ID) {
        this.ID = ID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public void setButton(Button button) {
        this.button = button;
    }


    public String getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public double getSalary() {
        return salary;
    }
    public Button getButton() {
        return button;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                '}';
    }
}
