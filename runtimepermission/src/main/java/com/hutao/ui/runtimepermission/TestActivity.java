package com.hutao.ui.runtimepermission;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;
import java.util.List;

public class TestActivity extends PermissionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.CAMERA"};

    public void onClickEvent(View view) {
        //点击按钮开始测试拍照
        requestPermissionToTakeCamera();
    }

    /**
     * 如果需要测试拍照，onClick中直接调用即可
     */
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
                            Toast.makeText(TestActivity.this, "打开失败，没有权限", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(TestActivity.this, "没有打开权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        } else {
            startCarmera();
        }
    }

    public static final int TAKEPHOTO = 100;

    /**
     * 开始调用系统相机拍照
     */
    private void startCarmera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TAKEPHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKEPHOTO && resultCode == RESULT_OK) {
            //doEvent


        }
    }
}
