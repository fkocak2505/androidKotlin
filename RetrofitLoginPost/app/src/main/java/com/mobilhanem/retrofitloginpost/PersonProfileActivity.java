package com.mobilhanem.retrofitloginpost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mobilhanem.retrofitloginpost.adapter.CustomAdapter;
import com.mobilhanem.retrofitloginpost.model.UserResponse;

import java.util.List;

public class PersonProfileActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private List<UserResponse> userResponses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_profile);

        Intent i = getIntent();
        userResponses = (List<UserResponse>) i.getSerializableExtra("mylist");

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CustomAdapter(userResponses);
        mRecyclerView.setAdapter(mAdapter);


    }
}
