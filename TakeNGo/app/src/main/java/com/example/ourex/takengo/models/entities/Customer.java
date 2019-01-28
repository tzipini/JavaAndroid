package com.example.ourex.takengo.models.entities;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * •	שם משפחה
 •	שם פרטי
 •	מספר ת.ז.
 •	מספר טלפון
 •	כתובת דוא"ל
 •	מספר כרטיס אשראי לחיוב.
 */
public class Customer extends User  implements  IArray{

    protected Integer number;
    protected String creditNo;

    public Customer(Integer number, String firstName, String lastName, String identityNo, String tel, String email,String password, String creditNo) {
        super(number,firstName, lastName, identityNo, tel, email,password);
        this.creditNo = creditNo;
        this.number = number;
    }

    @Override
    public ArrayList<String> getArrayList() {
        return new ArrayList<>(Arrays.asList(this.number.toString(),
                this.firstName,
                this.lastName,
                this.tel,
                this.email,
                this.password,
                this.creditNo,
                this.number.toString()));
    }

    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public String getCreditNo() {
        return creditNo;
    }
    public void setCreditNo(String creditNo) {
        this.creditNo = creditNo;
    }
}
