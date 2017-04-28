package com.example.crocodile.monan;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnFoodManage,btnOrderTable,btnPayManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFoodManage = (Button) findViewById(R.id.btn_FoodManage);
        btnFoodManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(FoodManage.class);
            }
        });
        btnOrderTable = (Button) findViewById(R.id.btn_OrderTable);
        btnOrderTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnPayManage = (Button) findViewById(R.id.btn_PayManage);
        btnPayManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(PayManage.class);
            }
        });

    }

    private void openActivity(Class a) {
        Intent intent = new Intent(MainActivity.this,a);
        startActivity(intent);
    }

}
