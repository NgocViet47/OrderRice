package com.example.mypc.orderrice.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.mypc.orderrice.R;
import com.example.mypc.orderrice.adapter.ItemsBrunchAdapter;
import com.example.mypc.orderrice.database.DatabaseHandler;
import com.example.mypc.orderrice.model.Brunch;
import com.example.mypc.orderrice.model.Food;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemsBrunchAdapter customListAdapter;
    private List<Brunch> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initialView();
        addDatabaseBrunch();
        addArrayList();
    }


    private void addArrayList() {
        DatabaseHandler db = new DatabaseHandler(this);
        mList = new ArrayList<>();
        mList.clear();
        mList = db.getAllBrunch();
        setRecycleView();
    }
    private void addDatabaseBrunch() {
        /*DatabaseHandler db = new DatabaseHandler(this);

        db.addBrunch(new Brunch("Bữa Sáng", 0, R.drawable.breakfast));
        db.addBrunch(new Brunch("Bữa Trưa", 1, R.drawable.lunch));
        db.addBrunch(new Brunch("Bữa Tối", 2, R.drawable.dinner));
        db.addBrunch(new Brunch("Thức uống", 3, R.drawable.drinks));

        db.addFood(new Food(R.drawable.banhmi, "Bánh Mì", 0, 0, 15000, 0));
        db.addFood(new Food(R.drawable.phobo, "Phở Bò", 0, 0, 16000, 0));
        db.addFood(new Food(R.drawable.bunbohue, "Bún Bò Huế", 0, 0, 20000, 0));
        db.addFood(new Food(R.drawable.xoi, "Xôi", 0, 0, 10000, 0));
        db.addFood(new Food(R.drawable.mytom, "Mỳ Tôm", 0, 0, 10000, 0));
        db.addFood(new Food(R.drawable.comga, "Cơm Gà", 1, 0, 16000, 0));
        db.addFood(new Food(R.drawable.comthitbo, "Cơm Thịt Bò", 1, 0, 20000, 0));
        db.addFood(new Food(R.drawable.comtamsuong, "Cơm Tấm Sường", 1, 0, 30000, 0));
        db.addFood(new Food(R.drawable.comtrung, "Cơm Tấm Trứng", 1, 0, 25000, 0));
        db.addFood(new Food(R.drawable.comga, "Cơm Gà", 2, 0, 16000, 0));
        db.addFood(new Food(R.drawable.mixaobo, "Mì Xào Bò", 2, 0, 20000, 0));
        db.addFood(new Food(R.drawable.nuixaobo, "Nui Xào Bò", 2, 0, 20000, 0));
        db.addFood(new Food(R.drawable.cafe, "Cafe", 3, 0, 10000, 0));
        db.addFood(new Food(R.drawable.nuocsuoi, "Nước Suối", 3, 0, 10000, 0));*/

    }

    public void setRecycleView() {
        customListAdapter = new ItemsBrunchAdapter(mList, this);
        recyclerView.setAdapter(customListAdapter);
    }

    private void initialView() {
        recyclerView = (RecyclerView) findViewById(R.id.cardList);
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }
}
