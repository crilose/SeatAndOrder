package com.example.seatandorder.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.seatandorder.R;

import Domain.FoodDomain;
import Helper.ManagementCard;

public class ShowDetailActivity extends AppCompatActivity {

    private TextView addToOrderBtn;
    private TextView titleTxt, prezzo, descriptionTxt, quantityTxt;
    public ImageView plusBtn,minusBtn,picFood;
    private FoodDomain object;
    private int quantity = 1;
    private ManagementCard managementCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCard = new ManagementCard(this);
        initView();
        getBundle();
    }

    private void getBundle()
    {
        object = (FoodDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId=this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(object.getTitle());
        prezzo.setText("$" + object.getFee());
        descriptionTxt.setText(object.getDescription());
        quantityTxt.setText(String.valueOf(quantity));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity ++;
                quantityTxt.setText(String.valueOf(quantity));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quantity>1)
                    quantity--;
                quantityTxt.setText(String.valueOf(quantity));
            }
        });

        addToOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCard(quantity);
                managementCard.insertFood(object);
            }
        });

    }

    private void initView() {

        //Otteniamo gli elementi grafici
        addToOrderBtn = findViewById(R.id.addToOrder);
        titleTxt = findViewById(R.id.titleTxt);
        prezzo = findViewById(R.id.prezzo);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        quantityTxt = findViewById(R.id.quantityTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picFood = findViewById(R.id.foodPic);
    }
}