package com.example.mypc.orderrice.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import com.example.mypc.orderrice.R;
import com.example.mypc.orderrice.adapter.ItemsListFoodAdapter;
import com.example.mypc.orderrice.model.Food;
import com.example.mypc.orderrice.utils.BundleExtra;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FoodScreenActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private ItemsListFoodAdapter customListAdapter;
    private List<Food> mList;
    private int intent;
    private Button buttonBack;
    private Button buttonOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_screen);
        initialView();
        addArrayList();
        buttonClick();
    }

    public FoodScreenActivity() {
    }

    private void addArrayList() {
        mList = new ArrayList<>();
        mList.clear();
        Bundle bundle = getIntent().getExtras();
        intent = bundle.getInt("position");
        switch (intent){
            case 0:
                mList.add(new Food(R.drawable.banhmi, "Bánh Mì", 0,15000));
                mList.add(new Food(R.drawable.phobo, "Phở Bò", 0,16000));
                mList.add(new Food(R.drawable.bunbohue, "Bún Bò Huế", 0,20000));
                mList.add(new Food(R.drawable.xoi, "Xôi", 0,10000));
                mList.add(new Food(R.drawable.mytom, "Mỳ Tôm", 0,10000));
                setRecycleView();
                break;
            case 1:
                mList.add(new Food(R.drawable.comga, "Cơm Gà", 0,16000));
                mList.add(new Food(R.drawable.comthitbo, "Cơm Thịt Bò", 0,20000));
                mList.add(new Food(R.drawable.comtamsuong, "Cơm Tấm Sường", 0,30000));
                mList.add(new Food(R.drawable.comtrung, "Cơm Tấm Trứng", 0,25000));
                setRecycleView();
                break;
            case 2:
                mList.add(new Food(R.drawable.comga, "Cơm Gà", 0,16000));
                mList.add(new Food(R.drawable.mixaobo, "Mì Xào Bò", 0,20000));
                mList.add(new Food(R.drawable.nuixaobo, "Nui Xào Bò", 0,20000));
                setRecycleView();
                break;
            case 3:
                mList.add(new Food(R.drawable.cafe, "Cafe", 0,10000));
                mList.add(new Food(R.drawable.nuocsuoi, "Nước Suối", 0,10000));
                setRecycleView();
                break;
        }
    }
    public void setRecycleView() {
        customListAdapter = new ItemsListFoodAdapter(mList, this);
        recyclerView.setAdapter(customListAdapter);
    }

    private void initialView() {
        recyclerView = (RecyclerView) findViewById(R.id.cardListFood);
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        buttonBack = (Button)findViewById(R.id.buttonBack);
        buttonOrder = (Button)findViewById(R.id.buttonOrder);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonBack:
                buttonBackClick();
                break;
            case R.id.buttonOrder:
                buttonOrderClick();
                break;
        }
    }

    public Integer value(){
        int value = 0;
        Food itemsList;
        for (int a = 0; a < mList.size(); a++) {
            itemsList = mList.get(a);
            value += (itemsList.getQuantity() * itemsList.getValue());
        }
        return value;
    }

    private void buttonOrderClick() {
        String lstFoodJson = new Gson().toJson(mList);
        Intent intentOrder = new Intent(FoodScreenActivity.this,OrderScreenActivity.class);
        intentOrder.putExtra(BundleExtra.FOOD_DATA,lstFoodJson);
        startActivity(intentOrder);
    }

    private void buttonBackClick() {
        Intent intentHome = new Intent(FoodScreenActivity.this,HomeScreenActivity.class);
        startActivity(intentHome);
    }

    private void buttonClick() {
        buttonBack.setOnClickListener(this);
        buttonOrder.setOnClickListener(this);
    }

}
