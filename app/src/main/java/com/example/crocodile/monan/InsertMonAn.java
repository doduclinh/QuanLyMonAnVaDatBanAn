package com.example.crocodile.monan;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class InsertMonAn extends AppCompatActivity {
    Spinner spinnerKind;
    String dbName = "OrderFood.sqlite";
    SQLiteDatabase dbMonAn;
    ImageView imgeditHinh;
    Button btnChonHinh;
    Button btnChupHinh;
    Button btnLuu;
    Button btnHuy;
    TextView tvID;
    EditText editGia, editTen;
    final int REQUEST_TAKE_PHOTO = 123;
    final int REQUEST_CHOOSE_PHOTO = 321;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_mon_an);

        spinnerKind = (Spinner) findViewById(R.id.SpinnerInsertKind);

        btnChonHinh = (Button) findViewById(R.id.btnInsertSelectPic);
        btnChupHinh= (Button) findViewById(R.id.btn_InsertTakePic);
        btnLuu = (Button) findViewById(R.id.btn_Insert);
        btnHuy = (Button) findViewById(R.id.btn_InsertCancel);

        tvID = (TextView) findViewById(R.id.txt_insertID);
        editGia = (EditText) findViewById(R.id.insert_Price);
        editTen = (EditText) findViewById(R.id.insert_Name);

        imgeditHinh = (ImageView) findViewById(R.id.imageView_insertMonAn);

        showSpinner();
        addEvents();
    }
    private void addEvents(){
        btnChonHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhoto();
            }
        });
        btnChupHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePic();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cancel();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CheckName = editTen.getText().toString();
                String CheckPrice = editGia.getText().toString();
                if (CheckName.matches("")){
                    Toast.makeText(InsertMonAn.this, "Vui lòng nhập tên món ăn", Toast.LENGTH_LONG).show();
                    return;
                }else if(CheckPrice.matches(""))
                {
                    Toast.makeText(InsertMonAn.this, "Vui lòng nhập giá món ăn", Toast.LENGTH_LONG).show();
                    return;
                }else
                Insert();
            }
        });

    }

    private void showSpinner() {
        ArrayList<String> arrayKind = new ArrayList<String>();
        dbMonAn = Database.initDatabase(InsertMonAn.this,dbName);
        Cursor cursor = dbMonAn.rawQuery("SELECT * FROM Kind", null);
        arrayKind.clear();
        for (int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            String NameKind = cursor.getString(1);

            arrayKind.add(NameKind);
        }
        ArrayAdapter AdapterSpinner = new ArrayAdapter(InsertMonAn.this,android.R.layout.simple_spinner_item,arrayKind);
        AdapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerKind.setAdapter(AdapterSpinner);
    }
    private void takePic(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_TAKE_PHOTO);
    }

    private void choosePhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CHOOSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            if (requestCode == REQUEST_CHOOSE_PHOTO){

                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imgeditHinh.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }else if (requestCode == REQUEST_TAKE_PHOTO){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgeditHinh.setImageBitmap(bitmap);

            }
        }
    }
    private void Insert(){
        String tenMonAn =  editTen.getText().toString();
        Double giaMonAn = Double.parseDouble(editGia.getText().toString());
        Integer k = spinnerKind.getSelectedItemPosition()+1;
        byte[] hinh = getbyteArray(imgeditHinh);


            SQLiteDatabase dbUpdate = Database.initDatabase(this, dbName);
            ContentValues ctValue = new ContentValues();
            ctValue.put("FoodyName", tenMonAn);
            ctValue.put("Price", giaMonAn);
            ctValue.put("KindID", k);
            ctValue.put("image", hinh);

            dbUpdate.insert("Foodys", null, ctValue);

            Toast.makeText(InsertMonAn.this, "Thêm mới món ăn thành công", Toast.LENGTH_LONG).show();
    }
    public void Cancel(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }
    public byte[] getbyteArray(ImageView imgv){

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
