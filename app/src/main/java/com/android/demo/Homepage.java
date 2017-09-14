package com.android.demo;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Sukrut on 9/14/2017.
 */

public class Homepage extends AppCompatActivity {

    ActionBar ab;
    Button task1,task2,extra_work;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        ab=getSupportActionBar();
        ab.setTitle("Home Page");
        ab=getSupportActionBar();
        task1= (Button) findViewById(R.id.task1);
        task2= (Button) findViewById(R.id.task2);
        extra_work= (Button) findViewById(R.id.extratask);

        task1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Homepage.this,Register.class);
                startActivity(intent);
            }
        });

        task2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Homepage.this,GoogleSignin.class);
                startActivity(intent);
            }
        });

        extra_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Homepage.this,GithubUser.class);
                startActivity(intent);

            }
        });
    }
}
