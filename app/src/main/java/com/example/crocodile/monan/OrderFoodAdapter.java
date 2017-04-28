package com.example.crocodile.monan;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThienKhiem on 4/27/2017.
 */

public class OrderFoodAdapter extends BaseAdapter {

    String dbName = "OrderFood.sqlite";
    SQLiteDatabase dbOrderDetail;

  //  int orderID;
    Activity MyContext;
    int MyLayout;
    List<MonAn> arrayMonAns;

    public OrderFoodAdapter(Activity myContext, int myLayout, List<MonAn> arrayMonAns) {
        MyContext = myContext;
        MyLayout = myLayout;
        this.arrayMonAns = arrayMonAns;
    }

/*    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    } */

    @Override
    public int getCount() {
        return arrayMonAns.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) MyContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(MyLayout,null);
        TextView viewTen = (TextView) convertView.findViewById(R.id.txtFoodNameOrder);
        EditText soluong = (EditText) convertView.findViewById(R.id.txt_SoLuong);

     //    int foodID = arrayMonAns.get(position).FoodyID;
      //   int Qty = Integer.parseInt(soluong.getText().toString());

        Button btn_SelectFood = (Button) convertView.findViewById(R.id.btn_ChonMon);

        viewTen.setText("Tên món: " + arrayMonAns.get(position).FoodyName + " - Giá: " + arrayMonAns.get(position).Price + " đ");
/*
        btn_SelectFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  AddFoodOrderDetail(orderID,foodID,Qty);

            }
        }); */


        return convertView;
    }
/*
    public void AddFoodOrderDetail( int OrID, int FoodID, int Qty){
        dbMonAn = Database.initDatabase(MyContext,dbName);
        Cursor cursor =dbMonAn.rawQuery("SELECT OrderDetailID FROM OrderDetail  WHERE OrderID =" + OrID +
                " AND FoodyID = " + FoodID, null);
        cursor.moveToPosition(0);
        int OrderID = cursor.getInt(0);
        if(cursor == null){

            SQLiteDatabase dbUpdate = Database.initDatabase(MyContext, dbName);
            ContentValues ctValue = new ContentValues();
            ctValue.put("OrderID", OrderID);
            ctValue.put("FoodyID", FoodID);
            ctValue.put("Quantity", Qty);

            dbUpdate.insert("OrderDetail", null, ctValue);

            Toast.makeText(MyContext, "đã thêm", Toast.LENGTH_LONG).show();
        }
    } */

}
