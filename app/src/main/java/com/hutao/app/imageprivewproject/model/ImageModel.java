package com.hutao.app.imageprivewproject.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/4/29.
 */

@Entity
public class ImageModel {

    @Property
    private String sapCode;//所属于的附件id
    @Id
    private String imageId;//图片的id，读取的时候在规定的文件夹读取

    public String getImageId() {
        return this.imageId;
    }
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
    public String getSapCode() {
        return this.sapCode;
    }
    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }

    @Generated(hash = 1740035212)
    public ImageModel(String sapCode, String imageId) {
        this.sapCode = sapCode;
        this.imageId = imageId;
    }

    public ImageModel() {
    }
}
