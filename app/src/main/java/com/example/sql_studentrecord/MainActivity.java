package com.example.sql_studentrecord;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
DatabaseHelper mydb;
EditText editTextId,editTextName,editTextEmail,editTextCC;
Button buttonAdd,buttonGetData,buttonUpdate,buttonDelete,buttonViewAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
mydb=new DatabaseHelper(this);
editTextId=findViewById(R.id.editText_id);
editTextName=findViewById(R.id.editText_name);
editTextEmail=findViewById(R.id.editText_email);
editTextCC=findViewById(R.id.editText_CC);

buttonAdd=findViewById(R.id.button_add);
buttonDelete=findViewById(R.id.button_delete);
buttonUpdate=findViewById(R.id.button_update);
buttonGetData=findViewById(R.id.button_view);
buttonViewAll=findViewById(R.id.button_viewAll);
addData();
deleteData();
updataData();
getData();
vewAll();
    }
     public void addData(){
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean IsInserted=mydb.insertData(editTextName.getText().toString(),editTextEmail.getText().toString(),editTextCC.getText().toString());

                if (IsInserted==true){
                    Toast.makeText(MainActivity.this, "data inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Something went wrong!",Toast.LENGTH_SHORT).show();
                }

            }
        });
     }
         public void getData(){
        buttonGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          String id=editTextId.getText().toString();
          if(id.equals(String.valueOf(""))){
              editTextId.setError("enter id");
          return;
          }
                Cursor cursor=mydb.getData(id);
                String data=null;
            if (cursor.moveToNext()){   //so that we move to next data
                data="ID: "+cursor.getString(0)+" \n"
                        +" NAME: "+cursor.getString(1)+" \n"
                        +" EMAIL: "+cursor.getString(2)+" \n"
                        +" COURSE COUNT: "+cursor.getString(3)+" \n";
            }
            showMesage("DATA",data);
            }


        });
         }

         public void vewAll(){
        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = mydb.getAllData();
//small test
                if (cursor.getCount() == 0) {
                    showMesage("ERROR", "Nothing found in DB ");
                }
                StringBuffer stringBuffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    stringBuffer.append("ID: " + cursor.getString(0) + "\n");
                    stringBuffer.append("NAME: " + cursor.getString(1) + "\n");
                    stringBuffer.append("EMAIL: " + cursor.getString(2) + "\n");
                    stringBuffer.append("Course Count: " + cursor.getString(3) + "\n");
                }
                showMesage("ALL DATA", stringBuffer.toString());

            }
        });
         }

         public void updataData(){
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated=mydb.updateData(editTextId.getText().toString(),
                        editTextName.getText().toString(),
                        editTextEmail.getText().toString(),
                        editTextCC.getText().toString());

            if(isUpdated==true){
                Toast.makeText(MainActivity.this,"Updated",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this,"oops!",Toast.LENGTH_SHORT).show();
            }
            }
        });
         }

         public void deleteData() {



                 Integer delete=mydb.deleteData(editTextId.getText().toString());

                 if (delete > 0) {
                     Toast.makeText(MainActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                 } else {
                     Toast.makeText(MainActivity.this, "not deleted", Toast.LENGTH_SHORT).show();
                 }
             }

    private void showMesage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.create();

        builder.setMessage(message);
        builder.setTitle(title);
        builder.setCancelable(true);
        builder.show();
    }
}
