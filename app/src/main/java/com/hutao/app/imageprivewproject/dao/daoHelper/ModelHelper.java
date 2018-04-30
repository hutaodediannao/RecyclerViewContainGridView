package com.hutao.app.imageprivewproject.dao.daoHelper;

import com.hutao.app.imageprivewproject.MyApplication;
import com.hutao.app.imageprivewproject.dao.DaoSession;
import com.hutao.app.imageprivewproject.dao.ModelDao;
import com.hutao.app.imageprivewproject.model.Model;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by Administrator on 2018/4/29.
 */

public class ModelHelper {

    //根据sapCode查询返回一个model
    public static Model queryBySapCode(String sapCode) {
        ModelDao modelDao = MyApplication.instances.getDaoSession().getModelDao();
        QueryBuilder<Model> builder = modelDao.queryBuilder();
        return builder.where(ModelDao.Properties.SapCode.eq(sapCode)).build().unique();
    }

    //插入一条数据
    public static long insertModel(Model model) {
        DaoSession ds = MyApplication.instances.getDaoSession();
        return ds.insert(model);
    }

    //删除一条数据
    public static void deleteModel(Model model) {
        ModelDao ds = MyApplication.instances.getDaoSession().getModelDao();
        ds.delete(model);
    }

    //更新一条数据
    public static void updateModel(Model updateModel) {
        //注意是根据id更新的
        DaoSession ds = MyApplication.instances.getDaoSession();
        ds.update(updateModel);
    }

    //查询所有数据
    public static List<Model> queryAllModelList() {
        ModelDao dao = MyApplication.instances.getDaoSession().getModelDao();
        QueryBuilder<Model> builder = dao.queryBuilder();
        return builder.orderDesc(ModelDao.Properties.SapCode).list();
    }
}
