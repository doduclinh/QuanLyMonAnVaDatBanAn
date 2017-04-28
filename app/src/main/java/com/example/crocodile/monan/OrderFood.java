package com.example.crocodile.monan;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OrderFood extends AppCompatActivity {

    ArrayList<MonAn> arrayMonAn;
    ArrayList<OrderDetailEntity> arrayOrder;
    String dbName = "OrderFood.sqlite";
    SQLiteDatabase dbMonAn, dbOrder;
    double total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle("gọi món");
        setContentView(R.layout.activity_order_food);

        ListView lvMonAn = (ListView) findViewById(R.id.lvListFood);
        ListView lvOrder = (ListView)  findViewById(R.id.lvOrdertFood);

        arrayMonAn = new ArrayList<MonAn>();
        arrayOrder = new ArrayList<OrderDetailEntity>();

        Intent intent = getIntent();
        int tableID = intent.getIntExtra("tableID",1);

        dbMonAn = Database.initDatabase(OrderFood.this,dbName);
        Cursor cursorMA =dbMonAn.rawQuery("SELECT FoodyID, FoodyName, Price, KindID, image  FROM Foodys ORDER BY KindID, FoodyName ", null);

        arrayMonAn.clear();
        for (int i = 0; i < cursorMA.getCount(); i++){
            cursorMA.moveToPosition(i);
            Integer id = cursorMA.getInt(0);
            String Name = cursorMA.getString(1);
            double price = cursorMA.getDouble(2);
            Integer ki = cursorMA.getInt(3);
            byte[]  img = cursorMA.getBlob(4);

            arrayMonAn.add(new MonAn(id,Name,price,ki,img));
        }

        OrderFoodAdapter orderFoodAdapter = new OrderFoodAdapter(
                OrderFood.this,
                R.layout.list_food_view,
                arrayMonAn
        );

        lvMonAn.setAdapter(orderFoodAdapter);

        dbOrder = Database.initDatabase(OrderFood.this,dbName);
        Cursor cursorOD =dbOrder.rawQuery("SELECT o.OrderID, d.OrderDetailID, d.Quantity, o.TableID," +
                " f.FoodyID, f.FoodyName, f.Price FROM Orders o, OrderDetail d, Foodys f\n" +
                "WHERE o.OrderID = d.OrderID AND f.FoodyID = d.FoodyID  AND o.StateID = 2  AND" +
                "  o.TableID =" + tableID, null);
        arrayOrder.clear();
        for (int i = 0; i < cursorOD.getCount(); i++){
            cursorOD.moveToPosition(i);
            int oid = cursorOD.getInt(0);
            int odid = cursorOD.getInt(1);
            int odQty = cursorOD.getInt(2);
            int Tid = cursorOD.getInt(3);
            int Fid = cursorOD.getInt(4);
            String Name = cursorOD.getString(5);
            double price = cursorOD.getDouble(6);

            total += price;
            arrayOrder.add(new OrderDetailEntity(oid,odid,odQty,Tid,Fid,Name,price));
        }

        OrderFoodAdapter2 orderFoodAdapter2 = new OrderFoodAdapter2(
                OrderFood.this,
                R.layout.list_selected_food_view,
                arrayOrder
        );

        lvOrder.setAdapter(orderFoodAdapter2);
        TextView tvTotal = (TextView) findViewById(R.id.totalOrrder);
        tvTotal.setText("Món đã gọi  |  Tổng tiền: " + total + "đ");
       // cursorOD.moveToPosition(0);
       // orderFoodAdapter.setOrderID(cursorOD.getInt(0));
    }
}
