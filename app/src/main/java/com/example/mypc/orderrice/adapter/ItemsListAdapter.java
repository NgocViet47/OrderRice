package com.example.mypc.orderrice.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mypc.orderrice.R;
import com.example.mypc.orderrice.activity.FoodActivity;
import com.example.mypc.orderrice.model.ItemsList;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by MyPC on 2/10/2017.
 */

public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.ViewHolder> {
    private List<ItemsList> contactInfoList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private View itemView;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = mLayoutInflater.inflate(R.layout.items_list, parent, false);
        return new ViewHolder(itemView);
    }

    public ItemsListAdapter(List<ItemsList> contactInfoList, Context mContext) {
        this.mContext = mContext;
        this.contactInfoList = contactInfoList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemsList itemsList = contactInfoList.get(position);
        holder.textViewItemsHome.setText(itemsList.getFood());
        Picasso.with(mContext)
                .load(itemsList.getIdImageItems())
                .placeholder(R.color.colorPrimary)
                .fit()
                .centerInside()
                .noFade()
                .tag(mContext)
                .into(holder.imageViewItemsHome);
    }

    @Override
    public int getItemCount() {
        int count = (contactInfoList != null) ? contactInfoList.size() : 0;
        return count;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView textViewItemsHome;
        protected ImageView imageViewItemsHome;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewItemsHome = (TextView) itemView.findViewById(R.id.textView);
            imageViewItemsHome = (ImageView) itemView.findViewById(R.id.imageViewItemsHome);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ItemsList itemsList = contactInfoList.get(getAdapterPosition());
            Intent intent = new Intent(mContext, FoodActivity.class);
            intent.putExtra("position",getAdapterPosition());
            mContext.startActivity(intent);
            Toast.makeText(mContext,itemsList.getFood(),Toast.LENGTH_SHORT).show();
        }
    }

}
