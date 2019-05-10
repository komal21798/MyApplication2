package com.komal.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.komal.myapplication.Constants.URL_BOOKINGS;

public class MyBookingsActivity extends AppCompatActivity {

    //a list to store all the products
    List<Bookings> bookingsList;

    //the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.bookingRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        bookingsList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview
        loadBookings();
    }

    public void loadBookings() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_BOOKINGS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject bookings = array.getJSONObject(i);

                                //adding the product to product list
                                bookingsList.add(new Bookings(
                                        bookings.getInt("id"),
                                        bookings.getString("room_title"),
                                        bookings.getInt("quantity"),
                                        bookings.getDouble("total"),
                                        bookings.getString("username")

                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            BookingsAdapter adapter = new BookingsAdapter(getApplicationContext(), bookingsList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MyBookingsActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);

    }
}
