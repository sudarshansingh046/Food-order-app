package com.example.foody;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody.adapter.AsiaFoodAdapter;
import com.example.foody.adapter.PopularFoodAdapter;
import com.example.foody.model.AsiaFood;
import com.example.foody.model.PopularFood;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    RecyclerView popularRecycler, asiaRecycler;
    PopularFoodAdapter popularFoodAdapter;
    AsiaFoodAdapter asiaFoodAdapter;
    FloatingActionButton floatingActionButton2;
    ImageButton image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getIntent();
        floatingActionButton2=(FloatingActionButton)findViewById(R.id.floatingActionButton2);
        image=(ImageButton)findViewById(R.id.imageView);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pr=new Intent(ProfileActivity.this,profiledis.class);
                startActivity(pr);
            }
        });
        // now here we will add some dummy data to out model class

        List<PopularFood> popularFoodList = new ArrayList<>();

        popularFoodList.add(new PopularFood("Float Cake Vietnam", "7.05", R.drawable.popularfood1));
        popularFoodList.add(new PopularFood("burger", "99", R.drawable.burger));
        popularFoodList.add(new PopularFood("cheese pizza", "99", R.drawable.capsicum_double_cheese_pizza));
        popularFoodList.add(new PopularFood("chapati", "10", R.drawable.chapati));
        popularFoodList.add(new PopularFood("double cheese pizza", "159", R.drawable.double_cheese_pizza));
        popularFoodList.add(new PopularFood("indian thali", "69", R.drawable.indian_thali));
        popularFoodList.add(new PopularFood("jowar roti", "199", R.drawable.jowar_roti));
        popularFoodList.add(new PopularFood("maggie", "49", R.drawable.maggie));
        popularFoodList.add(new PopularFood("momos", "29", R.drawable.momos));

        popularFoodList.add(new PopularFood("otc pizza", "199", R.drawable.otc_pizza));
        popularFoodList.add(new PopularFood("paneer butter masala", "120", R.drawable.paneer_butter_masala));
        popularFoodList.add(new PopularFood("pasta", "49", R.drawable.pasta));
        popularFoodList.add(new PopularFood("pizza margharita", "399", R.drawable.pizza_margharita));
        popularFoodList.add(new PopularFood("rice", "49", R.drawable.rice));
        popularFoodList.add(new PopularFood("salad", "29", R.drawable.salad));
        popularFoodList.add(new PopularFood("shahi panir", "75", R.drawable.shahi_panir));
        popularFoodList.add(new PopularFood("south indian", "160", R.drawable.south_indian));
        popularFoodList.add(new PopularFood("tandoori roti", "15", R.drawable.tandoori_roti));

        setPopularRecycler(popularFoodList);


        List<AsiaFood> asiaFoodList = new ArrayList<>();
        asiaFoodList.add(new AsiaFood("Chicago Pizza", "20", R.drawable.asiafood1, "4.5", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("Straberry Cake", "25", R.drawable.asiafood2, "4.2", "Friends Restaurant"));


        asiaFoodList.add(new AsiaFood("burger", "99", R.drawable.burger, "4.5", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("cheese pizza", "99", R.drawable.capsicum_double_cheese_pizza, "4.3", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("chapati", "10", R.drawable.chapati, "4.5", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("double cheese pizza", "159", R.drawable.double_cheese_pizza, "4.3", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("indian thali", "69", R.drawable.indian_thali, "4.5", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("jowar roti", "199", R.drawable.jowar_roti, "4.2", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("maggie", "49", R.drawable.maggie, "4.4", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("momos", "29", R.drawable.momos, "4.5", "Briand Restaurant"));

        asiaFoodList.add(new AsiaFood("otc pizza", "199", R.drawable.otc_pizza, "4.5", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("paneer butter masala", "120", R.drawable.paneer_butter_masala, "4.7", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("pasta", "49", R.drawable.pasta, "4.5", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("pizza margharita", "399", R.drawable.pizza_margharita, "4.9", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("rice", "49", R.drawable.rice, "4.5", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("salad", "29", R.drawable.salad, "4.8", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("shahi panir", "75", R.drawable.shahi_panir, "4.5", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("south indian", "160", R.drawable.south_indian, "4.6", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("tandoori roti", "15", R.drawable.tandoori_roti, "4.5", "Briand Restaurant"));

        setAsiaRecycler(asiaFoodList);

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(ProfileActivity.this)
                        .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                startActivity(new Intent(ProfileActivity.this, MapsFragment.class));
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                if(response.isPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                                    builder.setTitle("Permission Denied")
                                            .setMessage("Permission to access device location is permanently denied. you need to go to setting to allow the permission.")
                                            .setNegativeButton("Cancel", null)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent();
                                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                    intent.setData(Uri.fromParts("package", getPackageName(), null));
                                                }
                                            })
                                            .show();
                                } else {
                                    Toast.makeText(ProfileActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        })
                        .check();
            }
        });

    }



    private void setPopularRecycler(List<PopularFood> popularFoodList) {

        popularRecycler = findViewById(R.id.popular_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        popularRecycler.setLayoutManager(layoutManager);
        popularFoodAdapter = new PopularFoodAdapter(this, popularFoodList);
        popularRecycler.setAdapter(popularFoodAdapter);

    }

    private void setAsiaRecycler(List<AsiaFood> asiaFoodList) {

        asiaRecycler = findViewById(R.id.asia_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        asiaRecycler.setLayoutManager(layoutManager);
        asiaFoodAdapter = new AsiaFoodAdapter(this, asiaFoodList);
        asiaRecycler.setAdapter(asiaFoodAdapter);

    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.bottom_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case  R.id.account:
                Toast.makeText(ProfileActivity.this,"ACCOUNT", Toast.LENGTH_LONG).show();
                Intent i=new Intent(ProfileActivity.this,profiledis.class);
                startActivity(i);
                return true;
            case R.id.cart:
                Toast.makeText(ProfileActivity.this,"CART", Toast.LENGTH_LONG).show();
                Intent india=new Intent(ProfileActivity.this,cart.class);
                startActivity(india);
                return true;
            case R.id.contactus:
                Toast.makeText(ProfileActivity.this,"CONTACT US", Toast.LENGTH_LONG).show();
                Intent in =new Intent(ProfileActivity.this,contactus.class);
                startActivity(in);
                return true;
            case R.id.aboutus:
                Toast.makeText(ProfileActivity.this,"ABOUT US", Toast.LENGTH_LONG).show();
                Intent ind =new Intent(ProfileActivity.this,aboutus.class);
                startActivity(ind);
                return true;
            case R.id.help:
                Toast.makeText(ProfileActivity.this,"HELP", Toast.LENGTH_LONG).show();
                Intent indi =new Intent(ProfileActivity.this,contactus.class);
                startActivity(indi);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}

