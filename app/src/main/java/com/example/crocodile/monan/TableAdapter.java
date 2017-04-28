package com.example.crocodile.monan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ThienKhiem on 4/27/2017.
 */

public class TableAdapter extends BaseAdapter {

    Activity MyContext;
    int MyLayout;
    List<Table> arrayTable;

    public TableAdapter(Activity myContext, int myLayout, List<Table> arrayTable) {
        this.MyContext = myContext;
        this.MyLayout = myLayout;
        this.arrayTable = arrayTable;
    }

    @Override
    public int getCount() {
        return arrayTable.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) MyContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(MyLayout,null);
        TextView viewTen = (TextView) convertView.findViewById(R.id.txt_TableName);
        TextView viewInfo = (TextView) convertView.findViewById(R.id.txt_ChairNumber);

        String isUsed = null;
        if(arrayTable.get(position).isUsed==1){
            isUsed = "Đang có khách";
        } else isUsed = "Bàn trống";

        Button btn_UpdateTable = (Button) convertView.findViewById(R.id.btn_UpdateTable);
        Button btn_OrderTable = (Button) convertView.findViewById(R.id.btn_OrderFood);
        Button btn_PayTable = (Button) convertView.findViewById(R.id.btn_PayTotal);

        viewTen.setText(arrayTable.get(position).TableName + "");
        viewInfo.setText("Số lượng ghế: " + arrayTable.get(position).ChairNumber + " Tình trạng: " + isUsed);

        btn_OrderTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MyContext,OrderFood.class);
                int tableID = arrayTable.get(position).TableID;
                intent.putExtra("tableID",tableID);
               MyContext.startActivity(intent);
            }
        });

        return convertView;
    }


}
