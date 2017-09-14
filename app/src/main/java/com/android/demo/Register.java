package com.android.demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import com.android.demo.R;

/**
 * Created by Sukrut on 9/13/2017.
 */

public class Register extends AppCompatActivity {

    Button b1,b2;
    EditText e1,e2,e3,e4,e5;
    Button submit,details,jsonfile,sharefile;
    RadioGroup rg;
    static String gender;
    RadioButton male,female;
    DatabaseHandler db;
    DataObject dataObject;
    ActionBar ab;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        b1= (Button) findViewById(R.id.submit);
        b2= (Button) findViewById(R.id.get_details);
        jsonfile = (Button) findViewById(R.id.jsonfile);
        sharefile= (Button) findViewById(R.id.sharefile);
        db=new DatabaseHandler(this);
        ab=getSupportActionBar();
        ab.setTitle("Task 1");
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab=getSupportActionBar();
        rg= (RadioGroup) findViewById(R.id.gender_radiogroup);
        e1= (EditText) findViewById(R.id.edit_name);
        e2= (EditText) findViewById(R.id.age);
        e3= (EditText) findViewById(R.id.occupation);
        e4= (EditText) findViewById(R.id.description);
        e5= (EditText) findViewById(R.id.address);
        male= (RadioButton) findViewById(R.id.male);
        female= (RadioButton) findViewById(R.id.female);

        if(Build.VERSION.SDK_INT>=23)
        {
            int permissioncheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(permissioncheck!= PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(Register.this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                ActivityCompat.requestPermissions(Register.this,new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},2);

            }
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(male.isChecked()==true)
                {
                    gender="Male";
                }
                if(female.isChecked()==true)
                {
                    gender="Female";
                }
                try {
                    validatedata();

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Register.this,MainActivity.class);
                startActivity(intent);


            }
        });

        jsonfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gson gson=new Gson();
                String json=gson.toJson(dataObject);
                if(json.equalsIgnoreCase(""))
                {
                    Toast.makeText(Register.this,"Please Submit the Details First",Toast.LENGTH_SHORT).show();
                }
                else {
                    generateFileONSD("JsonFile.txt", json);
                }

            }
        });

        sharefile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    File sdcard = Environment.getExternalStorageDirectory();
                    File file = new File(sdcard.getAbsolutePath()+"/JsonDir");

                    File file1=new File(file,"/JsonFile.txt");

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    Uri uri=Uri.fromFile(file1);

                    intent.putExtra(Intent.EXTRA_STREAM, uri);
                    intent.setType("file/*");
                    startActivity(intent.createChooser(intent, "Json Text File"));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });


    }

    private void validatedata()
    {
        if(e1.getText().toString().equalsIgnoreCase(""))
		{
			e1.setError("field cannot left Empty");
			e1.requestFocus();
			return;
		}

		if(male.isChecked()==false&&female.isChecked()==false)
		{
			Toast.makeText(this,"Gender Cannot left Empty",Toast.LENGTH_SHORT).show();
			male.requestFocus();
			return;
		}

        if(e2.getText().toString().equalsIgnoreCase(""))
        {
            e2.setError("field cannot left Empty");
            e2.requestFocus();
            return;
        }

        if(e3.getText().toString().equalsIgnoreCase(""))
        {
            e3.setError("field cannot left Empty");
            e3.requestFocus();
            return;
        }

        if(e4.getText().toString().equalsIgnoreCase(""))
        {
            e4.setError("field cannot left Empty");
            e4.requestFocus();
            return;
        }

        if(e5.getText().toString().equalsIgnoreCase(""))
        {
            e5.setError("field cannot left Empty");
            e5.requestFocus();
            return;
        }

        dataObject =new DataObject(e1.getText().toString(), gender, e2.getText().toString(), e3.getText().toString(), e4.getText().toString(), e5.getText().toString());
        db.addDetails(dataObject);
        Toast.makeText(Register.this,"Data Inserted Sucessfully In Database",Toast.LENGTH_SHORT).show();
        e1.setText("");
        e2.setText("");
        e3.setText("");
        e4.setText("");
        e5.setText("");
        male.setChecked(false);
        female.setChecked(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId())
        {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
   public void generateFileONSD(String filename,String body)
    {
        try
        {
            File root= Environment.getExternalStorageDirectory();
            File dir=new File(root.getAbsolutePath()+"/JsonDir");
            dir.mkdir();
            File file=new File(dir,filename);
            FileOutputStream f=new FileOutputStream(file);
            PrintWriter pw=new PrintWriter(f);
            pw.append(body);
            pw.flush();
            pw.close();
            f.close();
            Toast.makeText(this,"File Created at Sdcard/JsonDir/JsonFile.txt",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
