package com.example.chau.homework;

import java.util.zip.DeflaterOutputStream;

/**
 * Created by Chau on 04/04/2017.
 */

public class Info {
    private String code;
    private String name;
    private Float buyPrice;
    private Float transferPrice;
    private Float sellPrice;

    public Info(String code,String name,Float buyPrice,Float transferPrice,Float sellPrice){
        this.code = code;
        this.name = name;
        this.buyPrice = buyPrice;
        this.transferPrice = transferPrice;
        this.sellPrice = sellPrice;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Float getTransferPrice() {
        return transferPrice;
    }

    public void setTransferPrice(Float transferPrice) {
        this.transferPrice = transferPrice;
    }

    public Float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Float sellPrice) {
        this.sellPrice = sellPrice;
    }
}
