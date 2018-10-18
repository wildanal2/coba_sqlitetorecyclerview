package com.sqliterecycler.com.sqlite_recycler;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ItemAdapter mAdapter;
    private SQLiteDatabase mDatabase;
    private EditText mETname;
    private TextView mTVamount;
    private int mAmount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ItemDbHelper dbHelper = new ItemDbHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        RecyclerView recyView = findViewById(R.id.recy_view);
        recyView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ItemAdapter(this,getAllItems());
        recyView.setAdapter(mAdapter);

        mETname = findViewById(R.id.et_named);
        mTVamount = findViewById(R.id.tv_counter);
        Button btnadd = findViewById(R.id.btn_add);
        Button btnIncrease = findViewById(R.id.btn_plus);
        Button btnDecrease = findViewById(R.id.btn_min);

        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Increast();
            }
        });
        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrease();
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItems();
            }
        });
    }

    private void addItems() {

        if(mETname.getText().toString().trim().length() ==0 || mAmount==0){
            return;
        }

        String name = mETname.getText().toString();
        ContentValues  val = new ContentValues();
        val.put("nama", name);
        val.put("jumlah", mAmount);

        mDatabase.insert("belanjaan",null,val);
        Toast.makeText(getApplicationContext(),"Sukses Ditambahkan ",Toast.LENGTH_SHORT).show();
        mAdapter.swapCursor(getAllItems());

        mETname.setText("");
        mAmount=0;
        mTVamount.setText("0");
    }

    private void decrease() {
        if (mAmount>0){
            mAmount--;
            mTVamount.setText(String.valueOf(mAmount));
        }
    }

    private void Increast() {
        mAmount++;
        mTVamount.setText(String.valueOf(mAmount));
    }

    private Cursor getAllItems(){
        return mDatabase.query("belanjaan",
                null,
                null,
                null,
                null,
                null,
                "timestmp DESC");
    }
}
