package com.komal.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SuccessfulActivity extends AppCompatActivity {

    public String Title;
    public String ShortDesc;
    public Double Price;
    public String Image;

    ImageView product_image;
    TextView product_title;
    TextView product_desc;
    TextView product_price;
    EditText enter_rooms_text;
    Button confirmRoomsBtn;
    ProgressBar confirmProgressBar;

    Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful);

        Bundle bundle = getIntent().getExtras();
        Title = bundle.getString("Title");
        ShortDesc = bundle.getString("ShortDesc");
        Price = bundle.getDouble("Total");
        Image = bundle.getString("Image");

        product_title =  findViewById(R.id.product_title);
        product_desc = findViewById(R.id.product_desc);
        product_price = findViewById(R.id.product_price);
        product_image = findViewById(R.id.product_image);
        enter_rooms_text = findViewById(R.id.enter_rooms_edittext);
        confirmRoomsBtn = findViewById(R.id.confirmRoomsBtn);
        confirmProgressBar = findViewById(R.id.confirmProgressBar);
        cancelBtn = findViewById(R.id.cancelBookingBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent myIntent = new Intent(SuccessfulActivity.this, CancelActivity.class);
                myIntent.putExtra("Total", Price);
                startActivity(myIntent);
            }
        });

        product_title.setText(Title);
        product_desc.setText(ShortDesc);
        product_price.setText(Price.toString());
    }
}
