package com.example.crocodile.monan;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class TableManage extends AppCompatActivity {


    ArrayList<Table> arrayTable;
    String dbName = "OrderFood.sqlite";
    SQLiteDatabase dbTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle("Quản lý bàn");
        setContentView(R.layout.activity_table_manage);

        ListView lvTables = (ListView) findViewById(R.id.lvTable);

        arrayTable = new ArrayList<Table>();

        Intent intent = getIntent();
        int ZID = intent.getIntExtra("ID",1);

        dbTable = Database.initDatabase(TableManage.this,dbName);
        Cursor cursor =dbTable.rawQuery("SELECT TableID, TableName, ChairNumber, isUsed " +
                        "FROM TTable WHERE ZoneID = " + ZID, null);

        arrayTable.clear();
        for (int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            Integer id = cursor.getInt(0);
            String Name = cursor.getString(1);
            Integer chair = cursor.getInt(2);
            Integer is = cursor.getInt(3);

            arrayTable.add(new Table(id,Name,chair,is));
        }

        TableAdapter tableAdapter = new TableAdapter(
                TableManage.this,
                R.layout.table_view,
                arrayTable
        );

        lvTables.setAdapter(tableAdapter);
    }
}
