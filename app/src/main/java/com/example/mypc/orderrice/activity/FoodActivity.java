package com.example.mypc.orderrice.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import com.example.mypc.orderrice.R;
import com.example.mypc.orderrice.adapter.ItemsFoodAdapter;
import com.example.mypc.orderrice.database.DatabaseHandler;
import com.example.mypc.orderrice.model.Food;
import com.example.mypc.orderrice.utils.BundleExtra;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FoodActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private ItemsFoodAdapter customListAdapter;
    private List<Food> mList;
    private Set<Food> mListNew;
    private int intent;
    private Button buttonBack;
    private Button buttonOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        initialView();
        addDatabaseFood();
        addArrayList();
        buttonClick();
    }

    public FoodActivity() {
    }

    private void addArrayList() {
        DatabaseHandler db = new DatabaseHandler(this);
        mList = new ArrayList<>();
        mList.clear();
        Bundle bundle = getIntent().getExtras();
        intent = bundle.getInt(BundleExtra.BRUNCH_DATA);
        for (int a=0;a<db.getAllFood().size();a++) {
            Food food = db.getAllFood().get(a);
            if(food.getIdBrunchName()==intent){
                mList.add(food);
            }
        }
        setRecycleView();
    }

    private void addDatabaseFood() {

    }

    public void setRecycleView() {
        customListAdapter = new ItemsFoodAdapter(mList, this);
        recyclerView.setAdapter(customListAdapter);
    }

    private void initialView() {
        recyclerView = (RecyclerView) findViewById(R.id.cardListFood);
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonOrder = (Button) findViewById(R.id.buttonOrder);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonBack:
                buttonBackClick();
                break;
            case R.id.buttonOrder:
                buttonOrderClick();
                break;
        }
    }

    private void buttonOrderClick() {
        String lstFoodJson = new Gson().toJson(mList);
        Intent intentOrder = new Intent(FoodActivity.this, OrderActivity.class);
        intentOrder.putExtra(BundleExtra.FOOD_DATA, lstFoodJson);
        startActivity(intentOrder);
    }

    private void buttonBackClick() {
        Intent intentHome = new Intent(FoodActivity.this, HomeActivity.class);
        startActivity(intentHome);
    }

    private void buttonClick() {
        buttonBack.setOnClickListener(this);
        buttonOrder.setOnClickListener(this);
    }

}
