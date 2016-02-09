package com.xteam.demo.app.domain;

import java.util.ArrayList;
import java.util.List;

public class Product { //todo toSTring equals hashcode and PRO*** builder at separate class

    private String type;

    private String id;

    private long size; // todo what about decimal ?

    private long price; //todo what about decimal ?

    private String face;

    private long stock;

    private List<String> tags = new ArrayList<String>();

    public String getFace() {
        return face;
    }

    public long getPrice() {
        return price;
    }
}
