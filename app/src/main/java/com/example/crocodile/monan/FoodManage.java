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

public class FoodManage extends AppCompatActivity {

    ListView lvMonAn;
    ArrayList<MonAn> mangMonAn;
    String dbName = "OrderFood.sqlite";
    SQLiteDatabase dbMonAn;
    Button btnAddFoody, btnbackQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle("Quan ly mon an");
        setContentView(R.layout.activity_food_manage);

        lvMonAn = (ListView) findViewById(R.id.listviewMonAn);
        mangMonAn = new ArrayList<MonAn>();

        dbMonAn = Database.initDatabase(FoodManage.this,dbName);
        Cursor cursor =dbMonAn.rawQuery("SELECT * FROM Foodys", null);
        mangMonAn.clear();
        for (int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            Integer id = cursor.getInt(0);
            String Name = cursor.getString(1);
            double price = cursor.getDouble(2);
            Integer Kind = cursor.getInt(3);
            byte[] img = cursor.getBlob(4);

            mangMonAn.add(new MonAn(id,Name,price,Kind,img));
        }

        MonAnAdapter anAdapter = new MonAnAdapter(
                FoodManage.this,
                R.layout.danh_sach_mon_an,
                mangMonAn
        );

        lvMonAn.setAdapter(anAdapter);

        btnAddFoody = (Button) findViewById(R.id.btn_themMonAn);
        btnAddFoody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodManage.this,InsertMonAn.class);
                startActivity(intent);
            }
        });
        btnbackQL = (Button) findViewById(R.id.btn_backQLfromFood);
        btnbackQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodManage.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
