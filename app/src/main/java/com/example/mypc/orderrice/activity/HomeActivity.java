package com.example.mypc.orderrice.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.mypc.orderrice.R;
import com.example.mypc.orderrice.adapter.ItemsBrunchAdapter;
import com.example.mypc.orderrice.model.Brunch;

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
        addArrayList();
    }


    private void addArrayList() {
        mList = new ArrayList<>();
        mList.clear();
        mList.add(new Brunch("Bữa Sáng", R.drawable.breakfast));
        mList.add(new Brunch("Bữa Trưa", R.drawable.lunch));
        mList.add(new Brunch("Bữa Tối", R.drawable.dinner));
        mList.add(new Brunch("Thức uống", R.drawable.drinks));
        setRecycleView();
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
