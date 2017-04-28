package com.example.crocodile.monan;

/**
 * Created by ThienKhiem on 4/27/2017.
 */

public class OrderDetailEntity {
    Integer OrderID;
    Integer OrderDetailID;
    Integer Quantity;
    Integer TableID;
    Integer FoodyID;
    String FoodyName;
    double Price;



    public OrderDetailEntity(Integer orderID, Integer orderDetailID, Integer quantity, Integer tableID, Integer foodyID, String foodyName, double price) {
        OrderID = orderID;
        OrderDetailID = orderDetailID;
        Quantity = quantity;
        TableID = tableID;
        FoodyID = foodyID;
        FoodyName = foodyName;
        Price = price;
    }
    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Integer getOrderID() {
        return OrderID;
    }

    public void setOrderID(Integer orderID) {
        OrderID = orderID;
    }

    public Integer getOrderDetailID() {
        return OrderDetailID;
    }

    public void setOrderDetailID(Integer orderDetailID) {
        OrderDetailID = orderDetailID;
    }

    public Integer getTableID() {
        return TableID;
    }

    public void setTableID(Integer tableID) {
        TableID = tableID;
    }

    public Integer getFoodyID() {
        return FoodyID;
    }

    public void setFoodyID(Integer foodyID) {
        FoodyID = foodyID;
    }

    public String getFoodyName() {
        return FoodyName;
    }

    public void setFoodyName(String foodyName) {
        FoodyName = foodyName;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}
