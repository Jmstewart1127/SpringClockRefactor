package com.timeclock.web.ClockBeta.logistics;

import java.util.ArrayList;

/**
 * Created by Jacob Stewart on 7/3/2017.
 */
public class MaterialCostLogic {

    private int quantity;
    private double price;
    private double totalCost;

    public MaterialCostLogic() {
        super();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double storeTotalPrice(int quantity, double price) {
        double total = quantity * price;
        return total;
    }

}
