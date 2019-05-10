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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BookProductsActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_products);

        Bundle bundle = getIntent().getExtras();
        Title = bundle.getString("Title");
        ShortDesc = bundle.getString("ShortDesc");
        Price = bundle.getDouble("Price");
        Image = bundle.getString("Image");

        product_title =  findViewById(R.id.product_title);
        product_desc = findViewById(R.id.product_desc);
        product_price = findViewById(R.id.product_price);
        product_image = findViewById(R.id.product_image);
        enter_rooms_text = findViewById(R.id.enter_rooms_edittext);
        confirmRoomsBtn = findViewById(R.id.confirmRoomsBtn);
        confirmProgressBar = findViewById(R.id.confirmProgressBar);

        Glide.with(this)
                .load(Image) // Remote URL of image.
                .into(product_image);//ImageView to set.

        product_title.setText(Title);
        product_desc.setText(ShortDesc);
        product_price.setText(Price.toString());

        confirmRoomsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookRoom();
            }
        });

    }

    public void bookRoom() {

        final String quantity;
        quantity = enter_rooms_text.getText().toString();
        final Double totalPrice;
        totalPrice = Price*Integer.parseInt(enter_rooms_text.getText().toString());

        confirmProgressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_BOOK_PRODUCT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        confirmProgressBar.setVisibility(View.INVISIBLE );

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            finish();

                            Intent myIntent = new Intent(BookProductsActivity.this, SuccessfulActivity.class);
                            myIntent.putExtra("Title", Title);
                            myIntent.putExtra("ShortDesc", ShortDesc);
                            myIntent.putExtra("Total", totalPrice);
                            myIntent.putExtra("Image", Image);
                            startActivity(myIntent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        confirmProgressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("room_title", Title);
                params.put("quantity", quantity);
                params.put("total", totalPrice.toString());
                params.put("username", SharedPrefManager.getInstance(getApplicationContext()).getUserEmail());
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

}
