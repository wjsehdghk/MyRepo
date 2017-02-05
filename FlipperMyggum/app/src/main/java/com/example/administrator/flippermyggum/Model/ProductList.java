package com.example.administrator.flippermyggum.Model;

/**
 * Created by Administrator on 2016-09-27.
 */
public class ProductList {

    int Image ;
    String name1;
    String name2;
    String name3;

    public ProductList(int image, String name1, String name2, String name3) {
        Image = image;
        this.name1 = name1;
        this.name2 = name2;
        this.name3 = name3;
    }
    public int getImage() {
        return Image;
    }
    public void setImage(int image) {
        Image = image;
    }
    public String getName1() {
        return name1;
    }
    public void setName1(String name1) {
        this.name1 = name1;
    }
    public String getName2() {
        return name2;
    }
    public void setName2(String name2) {
        this.name2 = name2;
    }
    public String getName3() {
        return name3;
    }
    public void setName3(String name3) {
        this.name3 = name3;
    }
}

