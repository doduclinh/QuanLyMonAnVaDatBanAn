package com.example.crocodile.monan;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class PayManage extends AppCompatActivity {

    ListView lvZone;
    ArrayList<Zone> arrayZone;
    String dbName = "OrderFood.sqlite";
    SQLiteDatabase dbZone;
    //Button btnAddFoody, btnbackQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle("Quan ly thanh toan");
        setContentView(R.layout.activity_pay_manage);

        lvZone = (ListView) findViewById(R.id.lv_Zone);
        arrayZone = new ArrayList<Zone>();

        dbZone = Database.initDatabase(PayManage.this,dbName);
        Cursor cursor =dbZone.rawQuery("SELECT z.ZoneID,z.ZoneName,z.isUsed, COUNT(t.TableID) AS SL " +
         "FROM Zone z, TTable t " +
         "WHERE z.ZoneID = t.ZoneID GROUP BY z.ZoneID,z.ZoneName,z.isUsed", null);

        arrayZone.clear();
        for (int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            Integer id = cursor.getInt(0);
            String Name = cursor.getString(1);
            Integer us = cursor.getInt(2);
            Integer co = cursor.getInt(3);

            arrayZone.add(new Zone(id,Name,us,co));
        }

        ZoneAdapter zoneAdapter = new ZoneAdapter(
                PayManage.this,
                R.layout.zone_view,
                arrayZone
        );

        lvZone.setAdapter(zoneAdapter);
    }
}
