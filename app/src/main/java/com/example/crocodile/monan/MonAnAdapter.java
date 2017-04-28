package com.example.crocodile.monan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MonAnAdapter extends BaseAdapter {

    Activity myContext;
    int myLayout;
    List<MonAn> arrayMonAn;

    public MonAnAdapter(Activity context, int Layout, List<MonAn> monAnList){
        myContext = context;
        myLayout = Layout;
        arrayMonAn = monAnList;
    }

    @Override
    public int getCount() {
        return arrayMonAn.size();
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

        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(myLayout,null);

        final TextView txtID = (TextView) convertView.findViewById(R.id.FoodID_tv);
        TextView txtName = (TextView) convertView.findViewById(R.id.FoodName_tv);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.FoodPrice_tv);
        TextView txtKind = (TextView) convertView.findViewById(R.id.FoodKind_tv);
        ImageView imageFood = (ImageView) convertView.findViewById(R.id.imageView_food);
        Button btnUpdate = (Button) convertView.findViewById(R.id.btn_Update);
        Button btnDelete = (Button) convertView.findViewById(R.id.btn_Delete);

        String loai =  "";
        if(arrayMonAn.get(position).KindID == 1){
            loai = "Món ăn";
        }else if(arrayMonAn.get(position).KindID == 2){
            loai = "Nước uống";
        }else  loai = "Khác";

        txtID.setText(arrayMonAn.get(position).FoodyID + "");
        txtName.setText(arrayMonAn.get(position).FoodyName + "");
        txtPrice.setText(arrayMonAn.get(position).Price + "");
        txtKind.setText(loai);

        int length = arrayMonAn.get(position).Image.length;
        Bitmap bmImageFood = BitmapFactory.decodeByteArray(arrayMonAn.get(position).Image,0,length);
        imageFood.setImageBitmap(bmImageFood);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext,UpdateMonAn.class);
                int FID = Integer.parseInt((String) txtID.getText());
                intent.putExtra("ID",FID);
                myContext.startActivity(intent);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(myContext);
                dialog.setIcon(android.R.drawable.ic_delete);
                dialog.setTitle("Xác nhận xóa");
                dialog.setMessage("Bạn muốn xóa món ăn này?");
                dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = arrayMonAn.get(position).FoodyID;
                        delete(id);
                    }
                });
                dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog showdialog = dialog.create();
                showdialog.show();
            }
        });

        return convertView;
    }

    private void delete(int foodyid) {
        SQLiteDatabase dbDelete = Database.initDatabase(myContext,"OrderFood.sqlite");
        dbDelete.delete("Foodys", "FoodyID = ?",new String[] {foodyid + ""});

        Toast.makeText(myContext,"Xóa thành công",Toast.LENGTH_LONG).show();

        arrayMonAn.clear();
        Cursor cur = dbDelete.rawQuery("SELECT * FROM Foodys",null);
        while (cur.moveToNext()){
            Integer id = cur.getInt(0);
            String ten = cur.getString(1);
            Double gia = cur.getDouble(2);
            Integer loai = cur.getInt(3);
            byte[] img = cur.getBlob(4);
            arrayMonAn.add(new MonAn(id,ten,gia,loai,img));
        }
        notifyDataSetChanged();
    }

}
