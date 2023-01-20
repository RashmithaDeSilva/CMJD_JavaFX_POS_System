package lk.ise.pos.entity;

public class Item {

    private String code;
    private String description;
    private int qtyOnHand;
    private double unitPrice;


    public Item(){}
    public Item(String code, String description, int qtyOnHand, double unitPrice) {
        this.code = code;
        this.description = description;
        this.qtyOnHand = qtyOnHand;
        this.unitPrice = unitPrice;
    }


    public void setCode(String code) {
        this.code = code;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }


    public String getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public int getQtyOnHand() {
        return qtyOnHand;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
}
