package com.yash.shoplist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yash.shoplist.data.ShopData;

public class MainActivity extends AppCompatActivity implements ShopAdapter.ShopAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private EditText mSearchEditText;
    private Button mSearchButton;
    private ShopAdapter mAdapter;
    private ShopData mShopData;
    private String[] mShopName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mShopData = new ShopData();

        mSearchEditText = findViewById(R.id.et_search);

        mSearchButton = findViewById(R.id.bt_search);

        mRecyclerView = findViewById(R.id.rv_shop);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new ShopAdapter(this);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(itemDecoration);

        mRecyclerView.setAdapter(mAdapter);

        loadShopData();

        mSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0)
                    loadShopData();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSearchEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction()==KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    searchProductShop();
                    return true;
                }
                return false;
            }
        });

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchProductShop();
            }
        });

    }

    private void loadShopData(){
        mShopName = mShopData.getAllShopNames();
        mAdapter.setShopData(mShopName);
    }

    private void searchProductShop(){
        String searchQuery = mSearchEditText.getText().toString().toLowerCase();
        if(searchQuery.isEmpty())
            Toast.makeText(MainActivity.this,"Please enter a product name to search.",Toast.LENGTH_SHORT).show();
        else{
            String[] searchResult = mShopData.searchItemTag(searchQuery);
            if(searchResult.length == 0)
                Toast.makeText(MainActivity.this, "No shop sell this product.", Toast.LENGTH_SHORT).show();
            else
                mAdapter.setShopData(searchResult);
        }
    }

    @Override
    public void onClick(String clickedShopData) {
        Intent shopActivityIntent = new Intent(MainActivity.this, ShopActivity.class);
        shopActivityIntent.putExtra(Intent.EXTRA_INTENT, clickedShopData);
        startActivity(shopActivityIntent);
    }
}