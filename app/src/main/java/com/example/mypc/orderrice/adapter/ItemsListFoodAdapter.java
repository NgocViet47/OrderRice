package com.example.mypc.orderrice.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mypc.orderrice.R;
import com.example.mypc.orderrice.model.Food;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 2/10/2017.
 */

public class ItemsListFoodAdapter extends RecyclerView.Adapter<ItemsListFoodAdapter.ViewHolder> {
    private List<Food> contactInfoList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private View itemView;
    private Button buttonSubtraction;
    private Button buttonMore;
    public int positionAddFood;
    public List<Integer> mlistPosition =  new ArrayList<>();
    public int i = 0;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = mLayoutInflater.inflate(R.layout.items_list_food, parent, false);
        return new ViewHolder(itemView);
    }

    public ItemsListFoodAdapter(List<Food> contactInfoList, Context mContext) {
        this.contactInfoList = contactInfoList;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Food itemsList = contactInfoList.get(position);
        holder.textViewNameFood.setText(itemsList.getName());
        holder.textViewQuatity.setText(String.valueOf(itemsList.getQuantity()));
        holder.textViewValue.setText(String.valueOf(itemsList.getValue()));
        holder.textViewQuatityMove.setText(String.valueOf(itemsList.getQuantity()));
        Picasso.with(mContext)
                .load(itemsList.getIdImage())
                .placeholder(R.color.colorPrimary)
                .fit()
                .centerInside()
                .noFade()
                .tag(mContext)
                .into(holder.imageViewItemsFood);
        buttonMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = itemsList.getQuantity() + 1;
                itemsList.setQuantity(a);
                positionAddFood = position;
                mlistPosition.add(position);
                notifyDataSetChanged();
            }
        });
        buttonSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = itemsList.getQuantity() - 1;
                itemsList.setQuantity(a);
                notifyDataSetChanged();
            }
        });
        i += itemsList.getQuantity() * itemsList.getValue();
    }

    public List<String> addList(){
        List<String> a = new ArrayList<String>();
        a.add("0");
        a.add("1");
        return  a;
     }
    @Override
    public int getItemCount() {
        int count = (contactInfoList != null) ? contactInfoList.size() : 0;
        return count;
    }

    public Integer value() {
        /*int value = 0;
        for (int a = 0; a <= contactInfoList.size(); a++) {
            Food itemsList = contactInfoList.get(a);
            value += (itemsList.getQuantity() * itemsList.getValue());
        }*/
        return i;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView textViewNameFood;
        protected TextView textViewQuatity;
        protected TextView textViewValue;
        protected ImageView imageViewItemsFood;
        protected TextView textViewQuatityMove;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNameFood = (TextView) itemView.findViewById(R.id.textViewNameFood);
            textViewQuatity = (TextView) itemView.findViewById(R.id.textViewQuantity);
            textViewValue = (TextView) itemView.findViewById(R.id.textViewValue);
            textViewQuatityMove = (TextView) itemView.findViewById(R.id.textViewQuantityMove);
            imageViewItemsFood = (ImageView) itemView.findViewById(R.id.imageViewItemsFood);
            buttonMore = (Button) itemView.findViewById(R.id.buttonMore);
            buttonSubtraction = (Button) itemView.findViewById(R.id.buttonSubtraction);
        }
    }
}
