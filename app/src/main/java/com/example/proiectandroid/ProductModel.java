package com.example.proiectandroid;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ProductModel implements Parcelable {

    private String name;

    private Integer price;

    private Integer idProduct;

    public ProductModel(String name, Integer price, Integer idProduct) {
        this.name=name;
        this.price=price;
        this.idProduct=idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int i) {
        dest.writeStringArray(new String[] {this.name, this.price.toString(), this.idProduct.toString()});
    }

    public ProductModel(Parcel in) {
        String[] data = new String[3];

        in.readStringArray(data);

        this.name = data[0];
        this.price = Integer.valueOf(data[1]);
        this.idProduct = Integer.valueOf(data[2]); // de modificat la nevoie
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public Object createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public Object[] newArray(int size) {
            return new ProductModel[size];
        }
    };
}