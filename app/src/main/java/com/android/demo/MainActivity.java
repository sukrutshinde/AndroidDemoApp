package com.android.demo;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;

import com.android.demo.R;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DataAdapter dataAdapter;
    LinearLayoutManager layoutManager;
   ActionBar ab;
    DatabaseHandler db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DatabaseHandler(this);
        recyclerView= (RecyclerView) findViewById(R.id.recycleview1);
        recyclerView.setHasFixedSize(true);

        ab=getSupportActionBar();
        ab.setTitle("Recycle View");
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab=getSupportActionBar();

        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration=new DividerItemDecoration(this,LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        dataAdapter=new DataAdapter(getDataSet(),MainActivity.this);
        recyclerView.setAdapter(dataAdapter);



    }
    ArrayList<DataObject> getDataSet()
    {
        DatabaseHandler db=new DatabaseHandler(this);
        ArrayList result = new ArrayList<DataObject>();

        result=db.getAllContacts();

        return  result;
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
}
