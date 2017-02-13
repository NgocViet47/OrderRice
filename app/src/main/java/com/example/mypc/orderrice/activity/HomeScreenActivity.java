package com.example.mypc.orderrice.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.mypc.orderrice.R;
import com.example.mypc.orderrice.adapter.ItemsListAdapter;
import com.example.mypc.orderrice.model.ItemsList;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemsListAdapter customListAdapter;
    private List<ItemsList> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        initialView();
        addArrayList();
    }


    private void addArrayList() {
        mList = new ArrayList<>();
        mList.clear();
        mList.add(new ItemsList("Bữa Sáng", R.drawable.breakfast));
        mList.add(new ItemsList("Bữa Trưa", R.drawable.lunch));
        mList.add(new ItemsList("Bữa Tối", R.drawable.dinner));
        mList.add(new ItemsList("Thức uống", R.drawable.drinks));
        setRecycleView();
    }

    public void setRecycleView() {
        customListAdapter = new ItemsListAdapter(mList, this);
        recyclerView.setAdapter(customListAdapter);
    }

    private void initialView() {
        recyclerView = (RecyclerView) findViewById(R.id.cardList);
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }
}
