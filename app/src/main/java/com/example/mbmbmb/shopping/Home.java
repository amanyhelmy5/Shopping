package com.example.mbmbmb.shopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private View navigation_header;
    private TextView user_name_text_view;
    private TextView edit_profile_text_view;
    private SharedPreferences user_data;
    private Intent user_name_holder;
    private Bundle user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        user_data=getSharedPreferences("user_data",MODE_PRIVATE);

        navigation_header=navigationView.getHeaderView(0);
        user_name_text_view=(TextView) navigation_header.findViewById(R.id.tv_usr_name);
        user_name_holder =getIntent();
        user_name_text_view.setText(user_name_holder.getStringExtra("user_name"));
        edit_profile_text_view=(TextView) navigation_header.findViewById(R.id.tv_edit_profile);
        edit_profile_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit_profile_intent=new Intent(Home.this,EditProfile.class);
                edit_profile_intent.putExtra("user_name",user_name_holder.getStringExtra("user_name"));
                startActivity(edit_profile_intent);
            }
        });
        user=new Bundle();
        user.putString("user_name",user_name_holder.getStringExtra("user_name"));
        Fragment fragment = new Categories();
        fragment.setArguments(user);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.content_home, fragment);
        fragmentTransaction.commitAllowingStateLoss();
        setTitle("Categories");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        int id = item.getItemId();
        if(id == R.id.logout){
            SharedPreferences.Editor user_data_editor=user_data.edit();
            user_data_editor.remove("user_name");
            user_data_editor.remove("password");
            user_data_editor.commit();
            Intent login_intent=new Intent(Home.this,Login.class);
            startActivity(login_intent);
        }
        else {
            if (id == R.id.categories) {
                fragment=new Categories();
                fragment.setArguments(user);
                setTitle("Categories");
            } else if (id == R.id.search) {
                fragment=new Search();
                fragment.setArguments(user);
                setTitle("Search");
            } else if (id == R.id.shopping_list) {
                fragment=new ShoppingList();
                fragment.setArguments(user);
                setTitle("Shopping List");
            }
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.content_home, fragment);
            fragmentTransaction.commitAllowingStateLoss();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
