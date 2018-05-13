package com.hutao.ui.recyclerviewgridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private List<Car> carList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carList = Constant.getCarList();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        myAdapter = new MyAdapter(carList, this);
        recyclerView.setAdapter(myAdapter);

        myAdapter.setClickGroupListener(new MyAdapter.ClickGroupListener() {
            @Override
            public void click(int groupPosition, int childPosition) {
                Toast.makeText(MainActivity.this, groupPosition + "," + childPosition, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void delete(int groupPosition, int childPosition) {
                Toast.makeText(MainActivity.this, groupPosition + "," + childPosition, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void showMsg(View view) {
        Log.i(TAG, "详细列表数据为：" + carList.toString() + "/n" + "------------------------------------------------------------------------");
    }
}
