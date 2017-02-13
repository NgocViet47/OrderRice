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
import com.example.mypc.orderrice.utils.BundleExtra;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderScreenActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewResult;
    private RecyclerView recyclerView;
    private ItemsOrderAdapter customListAdapter;
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

    private void initialIntent(){
        if(getIntent().getExtras()!=null){
            String lstFoodJson = getIntent().getExtras().getString(BundleExtra.FOOD_DATA,"");
            Food foods [] = new Gson().fromJson(lstFoodJson,Food[].class);
            mList = Arrays.asList(foods);
        }
    }

    protected void toast() {
        Bundle bundle = getIntent().getExtras();
        textViewResult.setText(String.valueOf(bundle.getInt("valueAll")));
        //Toast.makeText(this, String.valueOf(itemsListFoodAdapter.mlistPosition.get(0)), Toast.LENGTH_SHORT).show();
    }

    private void addArrayList() {
        initialIntent();
        setRecycleView();
        /*mList = new ArrayList<>();
        mList.clear();
        Bundle bundle1 = getIntent().getExtras();
        intent = bundle1.getInt("position");
        if (itemsListFoodAdapter.mlistPosition.size()>=0) {
            for (int a = 0; a < itemsListFoodAdapter.mlistPosition.size(); a++) {
                int b = itemsListFoodAdapter.mlistPosition.get(a);
                mList.add(new Food(bundle1.getInt("idImage" + b), bundle1.getString("name" + b), bundle1.getInt("quantity" + b), bundle1.getInt("value" + b)));
            }
        } else
            switch (intent) {
                case 0:
                    mList.add(new Food(R.drawable.banhmi, "Bánh Mì", 0, 15000));
                    mList.add(new Food(R.drawable.phobo, "Phở Bò", 0, 16000));
                    mList.add(new Food(R.drawable.bunbohue, "Bún Bò Huế", 0, 20000));
                    mList.add(new Food(R.drawable.xoi, "Xôi", 0, 10000));
                    mList.add(new Food(R.drawable.mytom, "Mỳ Tôm", 0, 10000));
                    setRecycleView();
                    break;
                case 1:
                    mList.add(new Food(R.drawable.comga, "Cơm Gà", 0, 16000));
                    mList.add(new Food(R.drawable.comthitbo, "Cơm Thịt Bò", 0, 20000));
                    mList.add(new Food(R.drawable.comtamsuong, "Cơm Tấm Sường", 0, 30000));
                    mList.add(new Food(R.drawable.comtrung, "Cơm Tấm Trứng", 0, 25000));
                    setRecycleView();
                    break;
                case 2:
                    mList.add(new Food(R.drawable.comga, "Cơm Gà", 0, 16000));
                    mList.add(new Food(R.drawable.mixaobo, "Mì Xào Bò", 0, 20000));
                    mList.add(new Food(R.drawable.nuixaobo, "Nui Xào Bò", 0, 20000));
                    setRecycleView();
                    break;
                case 3:
                    mList.add(new Food(R.drawable.cafe, "Cafe", 0, 10000));
                    mList.add(new Food(R.drawable.nuocsuoi, "Nước Suối", 0, 10000));
                    setRecycleView();
                    break;
            }*/
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
