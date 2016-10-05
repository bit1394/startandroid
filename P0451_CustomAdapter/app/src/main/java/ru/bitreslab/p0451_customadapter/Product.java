package ru.bitreslab.p0451_customadapter;

/**
 * Created by Bit on 06.10.2016.
 */

public class Product {
    String name;
    int price;
    int image;
    boolean box;

    Product(String _describe, int _price, int _image, boolean _box){
        name = _describe;
        price = _price;
        image = _image;
        box = _box;
    }
}
