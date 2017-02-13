package com.example.mypc.orderrice.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mypc.orderrice.R;
import com.example.mypc.orderrice.adapter.ItemsOrderAdapter;
import com.example.mypc.orderrice.model.Food;
import com.example.mypc.orderrice.utils.BundleExtra;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class OrderScreenActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewResult;
    private RecyclerView recyclerView;
    private ItemsOrderAdapter customListAdapter;
    private List<Food> mList;
    private List<Food> mListOrder;
    private int intent;
    private Button buttonBack;
    private Button buttonResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_screen);


        initialView();
        addArrayList();
        buttonClick();
    }

    private void initialIntent(){
        if(getIntent().getExtras()!=null){
            String lstFoodJson = getIntent().getExtras().getString(BundleExtra.FOOD_DATA,"");
            Food foods [] = new Gson().fromJson(lstFoodJson,Food[].class);
            mList = Arrays.asList(foods);
        }
    }

    private void addArrayList() {
        initialIntent();
        setRecycleView();
    }

    public void setRecycleView() {
        customListAdapter = new ItemsOrderAdapter(mList, this);
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
        Food food = mList.get(0);
        textViewResult.setText(String.valueOf(food.getQuantity() * food.getValue()));
    }

    private void buttonBackClick() {
        Intent intentView = new Intent(OrderScreenActivity.this, FoodScreenActivity.class);
        intentView.putExtra("position", intent);
        startActivity(intentView);
    }

    private void buttonClick() {
        buttonBack.setOnClickListener(this);
        buttonResult.setOnClickListener(this);
    }
}
