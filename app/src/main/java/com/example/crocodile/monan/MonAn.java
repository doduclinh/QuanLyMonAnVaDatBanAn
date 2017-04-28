package com.example.crocodile.monan;

import java.sql.Blob;

/**
 * Created by CROCODILE on 4/9/2017.
 */

public class MonAn {
    public Integer FoodyID;
    public String FoodyName;
    public double Price;
    public Integer KindID;
    public byte[] Image;

    public MonAn(Integer foodyID, String foodyName, double price, Integer kindID, byte[] image) {
        FoodyID = foodyID;
        FoodyName = foodyName;
        Price = price;
        KindID = kindID;
        Image = image;
    }
}
