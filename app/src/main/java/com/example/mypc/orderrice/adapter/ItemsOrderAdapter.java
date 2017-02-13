package com.example.mypc.orderrice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mypc.orderrice.R;
import com.example.mypc.orderrice.model.Food;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by MyPC on 2/12/2017.
 */

public class ItemsOrderAdapter extends RecyclerView.Adapter<ItemsOrderAdapter.ViewHolder> {
    private List<Food> contactInfoList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private View itemView;
    private Button buttonSubtraction;
    private Button buttonMore;
    public int i = 0;

    @Override
    public ItemsOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = mLayoutInflater.inflate(R.layout.items_list_food, parent, false);
        return new ItemsOrderAdapter.ViewHolder(itemView);
    }

    public ItemsOrderAdapter(List<Food> contactInfoList, Context mContext) {
        this.contactInfoList = contactInfoList;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public void onBindViewHolder(ItemsOrderAdapter.ViewHolder holder, final int position) {
        Food itemsList = contactInfoList.get(position);
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
                Food items = contactInfoList.get(position);
                int a = items.getQuantity() + 1;
                items.setQuantity(a);
                notifyDataSetChanged();
            }
        });
        buttonSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Food items = contactInfoList.get(position);
                int a = items.getQuantity() - 1;
                items.setQuantity(a);
                notifyDataSetChanged();
            }
        });
        if(contactInfoList.get(position).getCheckInt()>0){
            holder.chkCheckInt.setChecked(true);
        }
        else holder.chkCheckInt.setChecked(false);
    }

    @Override
    public int getItemCount() {
        int count = (contactInfoList != null) ? contactInfoList.size() : 0;
        return count;
    }

    public Integer value() {
        int value = 0;
        Food itemsList;
        for (int a = 0; a < contactInfoList.size(); a++) {
            itemsList= contactInfoList.get(a);
            value += (itemsList.getQuantity() * itemsList.getValue());
        }
        return contactInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView textViewNameFood;
        protected TextView textViewQuatity;
        protected TextView textViewValue;
        protected ImageView imageViewItemsFood;
        protected TextView textViewQuatityMove;
        protected CheckBox chkCheckInt;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNameFood = (TextView) itemView.findViewById(R.id.textViewNameFood);
            textViewQuatity = (TextView) itemView.findViewById(R.id.textViewQuantity);
            textViewValue = (TextView) itemView.findViewById(R.id.textViewValue);
            textViewQuatityMove = (TextView) itemView.findViewById(R.id.textViewQuantityMove);
            imageViewItemsFood = (ImageView) itemView.findViewById(R.id.imageViewItemsFood);
            buttonMore = (Button) itemView.findViewById(R.id.buttonMore);
            buttonSubtraction = (Button) itemView.findViewById(R.id.buttonSubtraction);
            chkCheckInt = (CheckBox)itemView.findViewById(R.id.checkBoxCheckInt);
        }
    }
}
