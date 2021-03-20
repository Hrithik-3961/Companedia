package com.hrithik.companedia;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView industry;
    private TextView companies;
    private EditText search;
    private ListView listView;
    private Button getInfo;

    private ArrayList<String> arrayList;
    private Map<String, ArrayList<String>> map;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        industry = findViewById(R.id.industry);
        companies = findViewById(R.id.company);
        getInfo = findViewById(R.id.getInfo);
        getInfo.setEnabled(false);

        map = new HashMap<>();

        arrayList = new ArrayList<>();
        arrayList.add("Accounting Company 1");
        arrayList.add("Accounting Company 2");
        arrayList.add("Accounting Company 3");
        arrayList.add("Accounting Company 4");
        map.put("Accounting", arrayList);

        arrayList = new ArrayList<>();
        arrayList.add("Chemicals Company 1");
        arrayList.add("Chemicals Company 2");
        arrayList.add("Chemicals Company 3");
        arrayList.add("Chemicals Company 4");
        map.put("Chemicals", arrayList);

        arrayList = new ArrayList<>();
        arrayList.add("Computer Software Company 1");
        arrayList.add("Computer Software Company 2");
        arrayList.add("Computer Software Company 3");
        arrayList.add("Computer Software Company 4");
        map.put("Computer Software", arrayList);

        arrayList = new ArrayList<>();
        arrayList.add("Construction Company 1");
        arrayList.add("Construction Company 2");
        arrayList.add("Construction Company 3");
        arrayList.add("Construction Company 4");
        map.put("Construction", arrayList);

        arrayList = new ArrayList<>();
        arrayList.add("Design Company 1");
        arrayList.add("Design Company 2");
        arrayList.add("Design Company 3");
        arrayList.add("Design Company 4");
        map.put("Design", arrayList);

        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.spinner);
        dialog.getWindow().setLayout(700, 800);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        search = dialog.findViewById(R.id.search_view);
        listView = dialog.findViewById(R.id.list_view);
    }

    public void industry(View view) {

        dialog.show();
        ArrayList<String> list = new ArrayList<>();
        list.addAll(map.keySet());

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                industry.setText(adapter.getItem(i));
                companies.setText("Select Company");
                companies.setTextColor(Color.GRAY);
                getInfo.setEnabled(false);
                companies.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        companies(adapter.getItem(i));
                    }
                });
                industry.setTextColor(Color.BLACK);
                dialog.dismiss();
            }
        });
    }

    void companies(String industry) {

        dialog.show();
        ArrayList<String> list;
        list = map.get(industry);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                companies.setText(adapter.getItem(i));
                getInfo.setEnabled(true);
                companies.setTextColor(Color.BLACK);
                dialog.dismiss();
            }
        });
    }

    public void getInfo(View view) {
        Intent intent = new Intent(this, CompanyInfo.class);
        intent.putExtra("Company", companies.getText().toString());
        startActivity(intent);
    }
}