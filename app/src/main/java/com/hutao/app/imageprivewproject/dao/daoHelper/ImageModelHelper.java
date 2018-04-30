package com.hutao.app.imageprivewproject.dao.daoHelper;

import com.hutao.app.imageprivewproject.MyApplication;
import com.hutao.app.imageprivewproject.dao.DaoSession;
import com.hutao.app.imageprivewproject.dao.ImageModelDao;
import com.hutao.app.imageprivewproject.dao.ModelDao;
import com.hutao.app.imageprivewproject.model.ImageModel;
import com.hutao.app.imageprivewproject.model.Model;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by Administrator on 2018/4/29.
 */

public class ImageModelHelper {

    //根据图片的imageId查询一张图片信息
    public static ImageModel queryBySapCode(String imageId) {
        ImageModelDao imageModelDaoDao = MyApplication.instances.getDaoSession().getImageModelDao();
        QueryBuilder<ImageModel> builder = imageModelDaoDao.queryBuilder();
        return builder.where(ImageModelDao.Properties.ImageId.eq(imageId)).build().unique();
    }

    //保存一张图片信息
    public static long insertImageModel(ImageModel model) {
        DaoSession ds = MyApplication.instances.getDaoSession();
        return ds.insert(model);
    }

    //保存一个集合的数据
    public static void inserImageModelList(List<ImageModel> imageModels) {
        DaoSession ds = MyApplication.instances.getDaoSession();
        for (ImageModel im : imageModels) {
            ds.insert(im);
        }
    }

    //删除一条数据
    public static void deleteModel(ImageModel model) {
        ImageModelDao ds = MyApplication.instances.getDaoSession().getImageModelDao();
        ds.delete(model);
    }

    //更新一条数据
    public static void updateModel(ImageModel updateModel) {
        //注意是根据id更新的
        DaoSession ds = MyApplication.instances.getDaoSession();
        ds.update(updateModel);
    }

    //根据sapCode查询数据
    public static List<ImageModel> queryImageModelListBySapCode(String sapCode) {
        ImageModelDao dao = MyApplication.instances.getDaoSession().getImageModelDao();
        QueryBuilder<ImageModel> builder = dao.queryBuilder();
        return builder.where(ImageModelDao.Properties.SapCode.eq(sapCode)).list();
    }
}
