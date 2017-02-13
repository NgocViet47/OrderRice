package com.example.mypc.orderrice.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mypc.orderrice.R;
import com.example.mypc.orderrice.adapter.ItemsListFoodAdapter;
import com.example.mypc.orderrice.adapter.ItemsOrderAdapter;
import com.example.mypc.orderrice.model.Food;

import java.util.ArrayList;
import java.util.List;

public class OrderScreenActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewResult;
    private RecyclerView recyclerView;
    private ItemsOrderAdapter customListAdapter;
    private ItemsListFoodAdapter itemsListFoodAdapter;
    private List<Food> mList;
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
        toast();
    }
    protected void toast(){
        Bundle bundle = getIntent().getExtras();
        textViewResult.setText(String.valueOf(bundle.getInt("valueAll")));
        //Toast.makeText(this, String.valueOf(customListAdapter.value()), Toast.LENGTH_SHORT).show();
    }
    private void addArrayList() {
        mList = new ArrayList<>();
        mList.clear();
        Bundle bundle = getIntent().getExtras();
        intent = bundle.getInt("position");
        /*for (int a=0;a<itemsListFoodAdapter.mlistPosition.size();a++){
            int b= (int) itemsListFoodAdapter.mlistPosition.get(a);
            mList.add(new Food(bundle.getInt("idImage"+b),bundle.getString("name"+b),bundle.getInt("quantity"+b),bundle.getInt("value"+b)));
        }*/
        mList.add(new Food(bundle.getInt("idImage"),bundle.getString("name"),bundle.getInt("quantity"),bundle.getInt("value")));
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

        textViewResult = (TextView)findViewById(R.id.textViewResult);

        buttonBack = (Button)findViewById(R.id.buttonOrderBack);
        buttonResult = (Button)findViewById(R.id.buttonOrderResult);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
        textViewResult.setText(String.valueOf(food.getQuantity()*food.getValue()));
    }

    private void buttonBackClick() {
        Intent intentView = new Intent(OrderScreenActivity.this,FoodScreenActivity.class);
        intentView.putExtra("position",intent);
        startActivity(intentView);
    }
    private void buttonClick() {
        buttonBack.setOnClickListener(this);
        buttonResult.setOnClickListener(this);
    }
}
