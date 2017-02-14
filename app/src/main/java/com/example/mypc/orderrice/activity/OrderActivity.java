package com.example.mypc.orderrice.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mypc.orderrice.R;
import com.example.mypc.orderrice.adapter.ItemsOrderAdapter;
import com.example.mypc.orderrice.model.Food;
import com.example.mypc.orderrice.utils.BundleExtra;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewResult;
    private RecyclerView recyclerView;
    private ItemsOrderAdapter customListAdapter;
    private List<Food> mList;
    private List<Food> mListOrder = new ArrayList<>();
    private int intent;
    private Button buttonBack;
    private Button buttonResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        initialView();
        addArrayList();
        buttonClick();
        //outToast();
    }

    private void outToast() {
        Toast.makeText(this, getIntent().getExtras().getString(BundleExtra.FOOD_DATA, ""), Toast.LENGTH_SHORT).show();
    }

    private void initialIntent() {
        if (getIntent().getExtras() != null) {
            String lstFoodJson = getIntent().getExtras().getString(BundleExtra.FOOD_DATA, "");
            Food foods[] = new Gson().fromJson(lstFoodJson, Food[].class);
            mList = Arrays.asList(foods);
        }
    }

    private void addArrayList() {
        initialIntent();
        for (int a = 0; a < mList.size(); a++) {
            Food food = mList.get(a);
            if (mList.get(a).getQuantity() != 0) {
                mListOrder.add(new Food(food.getIdName(), food.getIdImage(), food.getName(), food.getIdBrunchName(), food.getQuantity(), food.getValue(), food.getCheckInt()));
            }
        }
        sumTotal();
        setRecycleView();
    }

    private void sumTotal() {
        int sumTotal = 0;
        for (int a = 0; a < mListOrder.size(); a++) {
            sumTotal += mListOrder.get(a).getQuantity() * mListOrder.get(a).getValue();
            textViewResult.setText(String.valueOf(sumTotal));
        }
    }

    public void setRecycleView() {
        customListAdapter = new ItemsOrderAdapter(mListOrder, this);
        recyclerView.setAdapter(customListAdapter);
    }

    private void initialView() {
        recyclerView = (RecyclerView) findViewById(R.id.cardListOrder);
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        textViewResult = (TextView) findViewById(R.id.textViewResult);
        buttonBack = (Button) findViewById(R.id.buttonOrderBack);
        buttonResult = (Button) findViewById(R.id.buttonOrderResult);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonOrderBack:
                buttonBackClick();
                break;
            case R.id.buttonOrderResult:
                buttonResultClick();
                break;
        }
    }

    private void buttonResultClick() {
        sumTotal();
    }

    private void buttonBackClick() {
        Intent intentView = new Intent(this, FoodActivity.class);
        intentView.putExtra("position", intent);
        startActivity(intentView);
    }

    private void buttonClick() {
        buttonBack.setOnClickListener(this);
        buttonResult.setOnClickListener(this);
    }
}
