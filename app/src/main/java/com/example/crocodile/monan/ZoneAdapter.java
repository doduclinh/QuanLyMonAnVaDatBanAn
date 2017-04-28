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

import java.util.ArrayList;
import java.util.List;



/**
 * Created by CROCODILE on 4/12/2017.
 */

public class ZoneAdapter extends BaseAdapter {
    Activity ZoneContext;
    int ZoneLayout;
    ArrayList<Zone> arrayZone;

    public ZoneAdapter(Activity payManage, int zone_view, ArrayList<Zone> arrayZone) {
        this.ZoneContext = payManage;
        this.ZoneLayout = zone_view;
        this.arrayZone = arrayZone;
    }

    @Override
    public int getCount() {
        return arrayZone.size();
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

        LayoutInflater inflater = (LayoutInflater) ZoneContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(ZoneLayout,null);
        TextView viewTen = (TextView) convertView.findViewById(R.id.txt_ZoneName);
        TextView Total = (TextView) convertView.findViewById(R.id.table_total);

        Button btn_Updatezone = (Button) convertView.findViewById(R.id.btn_UpdateZone);
        Button btn_viewTable = (Button) convertView.findViewById(R.id.btn_viewTable);

        viewTen.setText(arrayZone.get(position).ZoneName + "");
        Total.setText("Số lượng bàn: " + arrayZone.get(position).ZCount + "");

        btn_viewTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZoneContext,TableManage.class);
                int ZID = arrayZone.get(position).ZoneID;
                intent.putExtra("ID",ZID);
                ZoneContext.startActivity(intent);
                
            }
        });

        return convertView;
    }
}
