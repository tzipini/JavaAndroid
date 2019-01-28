package com.example.ourex.takengo.models.entities;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * •	כ{עיר, רחוב, מספר}
 */
public class Address implements IArray {

    protected Integer number;
    protected String street;
    protected String city;

    public Address(Integer number, String street, String city) {
        this.number = number;
        this.street = street;
        this.city = city;
    }




    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number=" + number +
                ", city='" + city + '\'' +
                '}';
    }


    @Override
    public ArrayList<String> getArrayList() {
        return new ArrayList<>(Arrays.asList(this.number.toString(),
                this.street,
                this.city));
    }
}
