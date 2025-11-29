package com.fahad.ashtana;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private String name;
    private double originalPrice;
    private double discountedPrice;
    private int imageResource;
    private String description;

    public Product(String name, double originalPrice, double discountedPrice, int imageResource, String description) {
        this.name = name;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
        this.imageResource = imageResource;
        this.description = description;
    }

    // Parcelable implementation
    protected Product(Parcel in) {
        name = in.readString();
        originalPrice = in.readDouble();
        discountedPrice = in.readDouble();
        imageResource = in.readInt();
        description = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(originalPrice);
        dest.writeDouble(discountedPrice);
        dest.writeInt(imageResource);
        dest.writeString(description);
    }

    // Getters
    public String getName() { return name; }
    public double getOriginalPrice() { return originalPrice; }
    public double getDiscountedPrice() { return discountedPrice; }
    public int getImageResource() { return imageResource; }
    public String getDescription() { return description; }
}