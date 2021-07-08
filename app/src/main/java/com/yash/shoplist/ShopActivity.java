package com.yash.shoplist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.yash.shoplist.data.ShopData;

public class ShopActivity extends AppCompatActivity {

    private TextView mShopNameDisplay;
    private TextView mShopAddressDisplay;
    private TextView mShopOfferDisplay;
    private ShopData mShopData;
    private String mShopName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        mShopNameDisplay = findViewById(R.id.tv_shop_name);
        mShopAddressDisplay = findViewById(R.id.tv_shop_address);
        mShopOfferDisplay = findViewById(R.id.tv_shop_offer);
        mShopData = new ShopData();

        Intent intent = getIntent();
        if(intent.hasExtra(Intent.EXTRA_INTENT))
            mShopName = intent.getStringExtra(Intent.EXTRA_INTENT);
        mShopNameDisplay.setText(mShopName);
        String address = mShopData.getShopAddress(mShopName);
        mShopAddressDisplay.setText(address);
        String offer = mShopData.getShopOffer(mShopName);
        if(offer.equals(""))
            offer = "No offers at the time.";
        else
            offer = parseShopOffer(offer);
        mShopOfferDisplay.setText(offer);
        mShopAddressDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+address));
                startActivity(intent);
            }
        });
    }

    private String parseShopOffer(String offer){
        String[] offers = offer.split(",");
        offer = "";
        for(int i=0;i<offers.length;i++){
            int j = i+1;
            String off = "Offer " + j + ": " + offers[i];
            if(i!=offers.length-1)
                off += "\n\n";
            offer += off;
        }
        return offer;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.shop_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_share){
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            String subject = "Shop Details";
            String body = "Shop name:"+mShopNameDisplay.getText()+"\n\nAddress:\n"+mShopAddressDisplay.getText()
                    +"\n\nOffers:\n"+mShopOfferDisplay.getText();
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            shareIntent.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(shareIntent, "Share using"));
        }
        return super.onOptionsItemSelected(item);
    }
}