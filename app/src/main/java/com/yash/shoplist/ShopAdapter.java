package com.yash.shoplist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {

    private String[] mShopData;

    private final ShopAdapterOnClickHandler mClickHandler;

    public interface ShopAdapterOnClickHandler{
        void onClick(String clickedShopData);
    }

    public ShopAdapter(ShopAdapterOnClickHandler clickHandler){
        mClickHandler = clickHandler;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.shop_list_item, parent, false);
        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopAdapter.ShopViewHolder holder, int position) {
        String shopData = mShopData[position];
        holder.listItemShopView.setText(shopData);
    }

    @Override
    public int getItemCount() {
        return mShopData==null?0:mShopData.length;
    }

    public void setShopData(String[] shopData) {
        mShopData = shopData;
        notifyDataSetChanged();
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView listItemShopView;

        public ShopViewHolder(View itemView){
            super(itemView);
            listItemShopView =  itemView.findViewById(R.id.tv_shop_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            String clickedShopData = mShopData[adapterPosition];
            mClickHandler.onClick(clickedShopData);
        }
    }

}
