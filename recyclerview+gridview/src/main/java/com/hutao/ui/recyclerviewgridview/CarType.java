package com.hutao.ui.recyclerviewgridview;

import java.util.List;

/**
 * Created by Administrator on 2018/5/13.
 */

public class CarType {

    private List<String> carPhotoList;//该汽车各种照片

    public CarType(List<String> carPhotoList) {
        this.carPhotoList = carPhotoList;
    }

    public List<String> getCarPhotoList() {
        return carPhotoList;
    }

    public void setCarPhotoList(List<String> carPhotoList) {
        this.carPhotoList = carPhotoList;
    }

    @Override
    public String toString() {
        return "CarType{" +
                "carPhotoList=" + carPhotoList +
                '}';
    }
}
