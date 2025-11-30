package com.fahad.ashtana;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class Order implements Parcelable {
    private String orderId;
    private String orderDate;
    private double totalAmount;
    private List<CartItem> items;
    private String status;

    public Order(String orderId, String orderDate, double totalAmount, List<CartItem> items, String status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.items = items;
        this.status = status;
    }

    protected Order(Parcel in) {
        orderId = in.readString();
        orderDate = in.readString();
        totalAmount = in.readDouble();
        items = in.createTypedArrayList(CartItem.CREATOR);
        status = in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderId);
        dest.writeString(orderDate);
        dest.writeDouble(totalAmount);
        dest.writeTypedList(items);
        dest.writeString(status);
    }

    // Getters
    public String getOrderId() { return orderId; }
    public String getOrderDate() { return orderDate; }
    public double getTotalAmount() { return totalAmount; }
    public List<CartItem> getItems() { return items; }
    public String getStatus() { return status; }
}