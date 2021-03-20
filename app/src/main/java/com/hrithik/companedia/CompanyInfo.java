package com.hrithik.companedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class CompanyInfo extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_info);

        String company = getIntent().getStringExtra("Company");

        final ArrayList<Model> list = new ArrayList<>();
        if(company.equals("Accounting Company 1"))
        list.add(new Model("Hrithik Agarwal"));
        else if(company.equals("Chemicals Company 1"))
        list.add(new Model("Udith Shyamsukha"));
        else if(company.equals("Computer Software Company 1"))
        list.add(new Model("Eshan Gupta"));
        else if(company.equals("Construction Company 1"))
        list.add(new Model("Rithesh Kunchakuri"));
        else if(company.equals("Design Company 1"))
        list.add(new Model("Tarunika Agarwal"));

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new Adapter(list);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new Adapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(CompanyInfo.this, Profile.class);
                intent.putExtra("Employee", list.get(position).getName());
                startActivity(intent);
            }
        });
    }
}