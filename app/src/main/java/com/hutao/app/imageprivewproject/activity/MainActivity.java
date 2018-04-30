package com.hutao.app.imageprivewproject.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.hutao.app.imageprivewproject.MyApplication;
import com.hutao.app.imageprivewproject.R;
import com.hutao.app.imageprivewproject.adapter.childAdapter.ModelAdapter;
import com.hutao.app.imageprivewproject.dao.daoHelper.ImageModelHelper;
import com.hutao.app.imageprivewproject.dao.daoHelper.ModelHelper;
import com.hutao.app.imageprivewproject.model.ImageModel;
import com.hutao.app.imageprivewproject.model.Model;
import com.hutao.app.imageprivewproject.util.DateUtil;
import com.hutao.app.imageprivewproject.util.FileUtil;
import com.hutao.app.imageprivewproject.weight.ImageGroup;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ModelAdapter modelAdapter;
    private RecyclerView recyclerView;
    private List<Model> modelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryData();
    }

    private ProgressDialog progressDialog;

    private void queryData() {
        progressDialog = ProgressDialog.show(this, "正在搜索数据库", "正在查询");
        new Thread(new Runnable() {
            @Override
            public void run() {
                modelList.clear();
                modelList.addAll(ModelHelper.queryAllModelList());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        modelAdapter.updateData(modelList);
                        progressDialog.dismiss();
                    }
                });
            }
        }).start();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void setAdapter() {
        modelAdapter = new ModelAdapter(modelList, this, callbackInterListener);
        recyclerView.setAdapter(modelAdapter);
    }

    private ImageGroup.CallbackInterListener callbackInterListener = new ImageGroup.CallbackInterListener() {
        @Override
        public void delete(int position, int childGropPositon, String sapCode, ImageModel imageModel) {
            //删除单张图片只能根据图片id删除
            String filePath = FileUtil.getImagePath(imageModel.getImageId(), MainActivity.this);
            if (filePath != null) {
                File file = new File(filePath);
                if (file.exists()) file.delete();//删除图片文件
            }

            //从数据库中删除该条数据
            ImageModelHelper.deleteModel(imageModel);//移除图片配置文件
            queryData();//重新刷新数据
            Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void add(int childPosition, String sapCode) {
            Toast.makeText(MainActivity.this, "delete", Toast.LENGTH_SHORT).show();
            addImageChildItemPosition = childPosition;
            requestPermissionToTakeCamera();
        }
    };

    //跳转到添加附件页面
    public void feedBack(View view) {
        startActivity(new Intent(this, AddActivity.class));
    }

    private int addImageChildItemPosition = -1;
    public static final int TAKEPHOTO = 100;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.CAMERA"};
    private void requestPermissionToTakeCamera() {
        if (Build.VERSION.SDK_INT >= 23) {//判断当前系统是不是Android6.0
            requestRuntimePermissions(PERMISSIONS_STORAGE, new PermissionListener() {
                @Override
                public void granted() {
                    //权限申请通过
                    startCarmera();
                }

                @Override
                public void denied(List<String> deniedList) {
                    //权限申请未通过
                    for (String denied : deniedList) {
                        if (denied.equals("android.permission.CAMERA")) {
                            Toast.makeText(MainActivity.this, "打开失败，没有权限", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "没有打开权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        } else {
            startCarmera();
        }
    }

    private void startCarmera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TAKEPHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKEPHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            File dirs = new File(FileUtil.getImageCacheDirsName(this));
            if (!dirs.exists()) dirs.mkdirs();
            File file = new File(FileUtil.getImageCacheDirsName(this) + "/" + DateUtil.millis() + ".jpg");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");
                try {
                    //开始保存图片
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                    photo.compress(Bitmap.CompressFormat.PNG, 50, bos);
                    //开始创建图片附件
                    Model m = modelList.get(addImageChildItemPosition);
                    ImageModel imageModel = new ImageModel();
                    imageModel.setSapCode(m.getSapCode());//将选择的图片归类给附件列表所点击的item（重要！！！）
                    imageModel.setImageId(file.getName());//每张图片都有自己的id
                    //开始保存图片附件
                    MyApplication.instances.getDaoSession().getImageModelDao().insert(imageModel);
                    Toast.makeText(this, "保存成功:", Toast.LENGTH_SHORT).show();

                    //重新刷新页面
                    queryData();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "保存图片异常", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "图片不存在", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
