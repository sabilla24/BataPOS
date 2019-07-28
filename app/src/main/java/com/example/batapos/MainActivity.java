package com.example.batapos;

import android.content.Intent;
import android.os.Bundle;

import com.example.batapos.Rest.ApiInterface;
import com.example.batapos.adapter.AdapterRam;
import com.example.batapos.model.GetRam;
import com.example.batapos.model.Ram;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private ArrayList<Ram> getListDataRam;
    private AdapterRam mAdapter;

    MaterialSearchView searchView;

//    private final String[] PAGE_TITLES = new String[]{
//            "Product", "Stock"
//    };
//
//    private final Fragment[] PAGES = new Fragment[]{
//            new Product(), new Produk()
//    };
//
//    public static TabLayout tabLayout;
//    public static ViewPager viewPager;

//    private void checkOut (){
//        dialog = new Dialog(MainActivity.this);
//        dialog.setContentView(R.layout.dialog_add_cart);
//        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        dialog.setCancelable(true);
//        dialog.show();
//
//        Button btnPay = dialog.findViewById(R.id.btnPay);
//        TextView tvAdd = dialog.findViewById(R.id.tvAdd);
//        btnPay.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                dialog.dismiss();
//                startActivity(new Intent(MainActivity.this, CartActivity.class));
//            }
//        });
//        tvAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//                finish();
//                startActivity(new Intent(MainActivity.this, MainActivity.class));
//            }
//        });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Scan Barcode", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


//        viewPager = findViewById(R.id.viewPager);
//        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
//        tabLayout = findViewById(R.id.tabLayout);
//        tabLayout.setupWithViewPager(viewPager);

        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                Toast.makeText(MainActivity.this, "Cari Produk", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.card_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }

    private void loadJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.learn2crack.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface request = retrofit.create(ApiInterface.class);
        Call<GetRam> call = request.getRam();
        call.enqueue(new Callback<GetRam>() {
            @Override
            public void onResponse(Call<GetRam> call, Response<GetRam> response) {
                GetRam getRam = response.body();
                getListDataRam = new ArrayList<>(Arrays.asList(getRam.getAndroid()));
                mAdapter = new AdapterRam(getListDataRam);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetRam> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cart, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {
            //Toast.makeText(this, "Keranjang", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, CartActivity.class ));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_stock) {
            startActivity(new Intent(MainActivity.this, StockActivity.class));
        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_bantuan) {

        } else if (id == R.id.nav_keluar) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    public class MyPagerAdapter extends FragmentPagerAdapter {
//
//        public MyPagerAdapter(FragmentManager fragmentManager) {
//            super(fragmentManager);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return PAGES[position];
//        }
//
//        @Override
//        public int getCount() {
//            return PAGES.length;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return PAGE_TITLES[position];
//        }
//
//    }
}
