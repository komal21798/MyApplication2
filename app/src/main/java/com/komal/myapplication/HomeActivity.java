package com.komal.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

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

import static com.komal.myapplication.Constants.URL_PRODUCTS;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    public ProductsAdapter adapter;
    public String username;

    //a list to store all the products
    List<Product> productList;

    //the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //get Username
        username = SharedPrefManager.getInstance(getApplicationContext()).getUserEmail();

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
            return;
        }

        final NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);

        dl = (DrawerLayout) findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(abdt);
        abdt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Intent myIntent;
                int id = item.getItemId();


                if (id == R.id.home_item) {

                    finish();
                    startActivity(new Intent(HomeActivity.this, HomeActivity.class));

                } else if (id == R.id.my_bookings_item) {

                    startActivity(new Intent(HomeActivity.this, MyBookingsActivity.class));

                } else if (id == R.id.logout_item) {

                    SharedPrefManager.getInstance(HomeActivity.this).logout();
                    finish();
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));

                } else if(id == R.id.view_bookings_item) {

                    //startActivity(new Intent(ProfileActivity.this, ViewBookingsActivity.class));
                }

                return true;
            }
        });


        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.productRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts();

    }

    private void loadProducts() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Product(
                                        product.getInt("id"),
                                        product.getString("title"),
                                        product.getString("shortdesc"),
                                        product.getDouble("price"),
                                        product.getString("image")
                                ));

                                /*try {
                                    File path = getApplicationContext().getExternalFilesDir(null);
                                    File file = new File(path, "text.json");
                                    FileOutputStream stream = new FileOutputStream(file, true);

                                    try {
                                        stream.write(product.toString().getBytes());
                                    } finally {
                                        stream.close();
                                    }
                                } catch(Exception e) {
                                    e.printStackTrace();
                                }*/

                            }

                            //creating adapter object and setting it to recyclerview
                            adapter = new ProductsAdapter(HomeActivity.this, productList);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

}
