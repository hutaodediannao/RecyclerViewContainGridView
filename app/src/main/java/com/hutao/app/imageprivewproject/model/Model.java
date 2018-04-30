package com.hutao.app.imageprivewproject.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/4/29.
 */

@Entity
public class Model {

    @Id
    private String sapCode;//该组数据存储到数据库中的id，便于后期查询使用
    @Property
    private String title;
    @Property
    private String content;

    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSapCode() {
        return this.sapCode;
    }
    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }
    @Generated(hash = 602109969)
    public Model(String sapCode, String title, String content) {
        this.sapCode = sapCode;
        this.title = title;
        this.content = content;
    }

    public Model() {
    }
}
