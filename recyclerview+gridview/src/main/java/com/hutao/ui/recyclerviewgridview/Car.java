package com.hutao.ui.recyclerviewgridview;

/**
 * Created by Administrator on 2018/5/13.
 */

public class Car {

    private CarType carType;//车型
    private String country;//所属国家

    public Car(CarType carType, String country) {
        this.carType = carType;
        this.country = country;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carType=" + carType +
                ", country='" + country + '\'' +
                '}';
    }
}
