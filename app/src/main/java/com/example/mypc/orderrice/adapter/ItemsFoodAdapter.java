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
 * Created by MyPC on 2/10/2017.
 */

public class ItemsFoodAdapter extends RecyclerView.Adapter<ItemsFoodAdapter.ViewHolder> {
    private List<Food> contactInfoList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private View itemView;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = mLayoutInflater.inflate(R.layout.items_food, parent, false);
        return new ViewHolder(itemView);
    }

    public ItemsFoodAdapter(List<Food> contactInfoList, Context mContext) {
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
        holder.checkBoxCheckInt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contactInfoList.get(position).getCheckInt()==1){
                    contactInfoList.get(position).setCheckInt(0);
                }else contactInfoList.get(position).setCheckInt(1);
            }
        });
        holder.checkBoxCheckInt.setChecked(true);
        if(contactInfoList.get(position).getCheckInt()>0){
            holder.checkBoxCheckInt.setChecked(true);
        }
        else holder.checkBoxCheckInt.setChecked(false);

        holder.buttonMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity =  contactInfoList.get(position).getQuantity() + 1;
                itemsList.setQuantity(quantity);
                notifyDataSetChanged();
            }
        });
        holder.buttonSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( contactInfoList.get(position).getQuantity() >0) {
                    int quantity = contactInfoList.get(position).getQuantity() - 1;
                    itemsList.setQuantity(quantity);
                    notifyDataSetChanged();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        int count = (contactInfoList != null) ? contactInfoList.size() : 0;
        return count;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView textViewNameFood;
        protected TextView textViewQuatity;
        protected TextView textViewValue;
        protected ImageView imageViewItemsFood;
        protected TextView textViewQuatityMove;
        protected Button buttonSubtraction;
        protected Button buttonMore;
        protected CheckBox checkBoxCheckInt;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewNameFood = (TextView) itemView.findViewById(R.id.textViewNameFood);
            textViewQuatity = (TextView) itemView.findViewById(R.id.textViewQuantity);
            textViewValue = (TextView) itemView.findViewById(R.id.textViewValue);
            textViewQuatityMove = (TextView) itemView.findViewById(R.id.textViewQuantityMove);
            imageViewItemsFood = (ImageView) itemView.findViewById(R.id.imageViewItemsFood);
            buttonMore = (Button) itemView.findViewById(R.id.buttonMore);
            buttonSubtraction = (Button) itemView.findViewById(R.id.buttonSubtraction);
            checkBoxCheckInt = (CheckBox) itemView.findViewById(R.id.checkBoxCheckInt);
        }
    }
}
