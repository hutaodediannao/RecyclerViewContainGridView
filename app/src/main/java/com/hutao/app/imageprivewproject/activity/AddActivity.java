package com.hutao.app.imageprivewproject.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hutao.app.imageprivewproject.R;
import com.hutao.app.imageprivewproject.dao.daoHelper.ImageModelHelper;
import com.hutao.app.imageprivewproject.dao.daoHelper.ModelHelper;
import com.hutao.app.imageprivewproject.model.ImageModel;
import com.hutao.app.imageprivewproject.model.Model;
import com.hutao.app.imageprivewproject.util.FileUtil;
import com.hutao.app.imageprivewproject.util.DateUtil;
import com.hutao.app.imageprivewproject.weight.ImageGroup;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddActivity extends BaseActivity {

    private EditText etTitle, etContent;
    private ImageGroup ig;
    private List<ImageModel> imageModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        ig = findViewById(R.id.imageGroup);
        setLsitener();
    }

    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.CAMERA"};

    private void setLsitener() {
        ig.setCallbackInterListener(new ImageGroup.CallbackInterListener() {
            @Override
            public void delete(int position, int childGropPosition, String sapCode, ImageModel imageModel) {
                String filePath = FileUtil.getImagePath(imageModelList.get(childGropPosition).getImageId(), AddActivity.this);
                if (filePath != null) {
                    File file = new File(filePath);
                    if (file.exists()) file.delete();//删除图片文件
                }
                imageModelList.remove(childGropPosition);//移除图片配置文件
                ig.updateImageContent2(imageModelList);
                Toast.makeText(AddActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void add(int childPosition, String sapCode) {
                requestPermissionToTakeCamera();
            }
        });
    }

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
                            Toast.makeText(AddActivity.this, "打开失败，没有权限", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddActivity.this, "没有打开权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        } else {
            startCarmera();
        }
    }

    private String sapCode;

    //点击保存数据
    public void save(View view) {
        sapCode = DateUtil.millis() + "";

        Model m = new Model();
        m.setTitle(etTitle.getText().toString());
        m.setContent(etContent.getText().toString());
        m.setSapCode(sapCode);//设置为当前的毫秒值

        //需要保存图片配置文件，附件，图片三个内容
        for (ImageModel im : imageModelList) {
            im.setSapCode(sapCode);//从属于当前的model中的图片抬头
        }

        ImageModelHelper.inserImageModelList(imageModelList);//保存一组图片附件
        ModelHelper.insertModel(m);//保存一个单据
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    public static final int TAKEPHOTO = 100;

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
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                    photo.compress(Bitmap.CompressFormat.PNG, 50, bos);
                    ImageModel imageModel = new ImageModel();
                    imageModel.setSapCode("");//初始保存值为空
                    imageModel.setImageId(file.getName());//每张图片都有自己的id
                    imageModelList.add(imageModel);//集合中保存照片记录
                    ig.updateImageContent2(imageModelList);//开始展示图片集合
                    Toast.makeText(this, "保存成功:", Toast.LENGTH_SHORT).show();

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
