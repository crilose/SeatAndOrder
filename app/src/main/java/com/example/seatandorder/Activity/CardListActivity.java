package com.example.seatandorder.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.seatandorder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import Adapter.OrdineAdapter;
import Helper.ManagementCard;
import Interface.ChangeNumberItemsListener;

public class CardListActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCard managementCard;
    private TextView totalPrezzoTxt, copertoTxt, totaleTxt, emptyTxt;
    private double coperto;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        managementCard=new ManagementCard(this);

        initView();
        initList();
        calculateOrdine();
        bottomNavigation();
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.cart_btn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CardListActivity.this,CardListActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CardListActivity.this,MainActivity.class));
            }
        });
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new OrdineAdapter(managementCard.getListCard(),this, new ChangeNumberItemsListener(){
            @Override
            public void changed(){
                calculateOrdine();
            }
        });
        recyclerViewList.setAdapter(adapter);

        if(managementCard.getListCard().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }
        else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private void calculateOrdine()
    {
        double coperto=2;
        double total = managementCard.getTotalPrezzo() + coperto;
        double itemTotal = Math.round(managementCard.getTotalPrezzo()*100.0)/100;
        totalPrezzoTxt.setText("$" + itemTotal);
        copertoTxt.setText("$"+coperto);
        totaleTxt.setText("$"+total);
    }
    private void initView() {
        recyclerViewList=findViewById(R.id.ordineView);
        totalPrezzoTxt=findViewById(R.id.totaleProdPrezzo);
        copertoTxt=findViewById(R.id.prezzoCoperto);
        totaleTxt=findViewById(R.id.totalePrezzo);
        emptyTxt=findViewById(R.id.emptyText);
        scrollView=findViewById(R.id.ordineScroll);
    }
}