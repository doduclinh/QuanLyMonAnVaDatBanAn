package com.example.crocodile.monan;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ThienKhiem on 4/27/2017.
 */

public class OrderFoodAdapter2 extends BaseAdapter {
    Activity MyContext;
    int MyLayout;
    List<OrderDetailEntity> arrayOrder;

    public OrderFoodAdapter2(Activity myContext, int myLayout, List<OrderDetailEntity> arrayOrder) {
        MyContext = myContext;
        MyLayout = myLayout;
        this.arrayOrder = arrayOrder;
    }


    @Override
    public int getCount() {
        return arrayOrder.size();
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

        TextView textView2 = (TextView) convertView.findViewById(R.id.txt_foodName);

        EditText Qty = (EditText) convertView.findViewById(R.id.edit_Soluong2);

        textView2.setText("Tên món: " + arrayOrder.get(position).FoodyName + " - Giá: " + arrayOrder.get(position).Price + " đ");
        Qty.setText(arrayOrder.get(position).Quantity + "");


        return convertView;
    }
}
