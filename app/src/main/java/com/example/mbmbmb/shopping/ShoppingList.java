package com.example.mbmbmb.shopping;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class ShoppingList extends Fragment {
    private TextView total_text_view;
    private EditText user_address_edit_text;
    private float total=0;
    private ListView shopping_list_view;
    private ShoppingListAdapter shopping_list_adapter;
    private List<ShoppingListItem> shopping_list_items;
    private Button buy_button;
    private E_CommerceDB e_commerce_db;
    private Bundle user;
    public ShoppingList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        user_address_edit_text=(EditText) rootView.findViewById(R.id.et_user_address);
        total_text_view=(TextView) rootView.findViewById(R.id.tv_total);
        buy_button=(Button) rootView.findViewById(R.id.btn_buy);
        e_commerce_db=new E_CommerceDB(getContext());
        user=getArguments();
        shopping_list_items=new ArrayList<>();
        shopping_list_view=(ListView) rootView.findViewById(R.id.lv_shopping_items);
        shopping_list_adapter=new ShoppingListAdapter(getContext(),R.layout.shopping_list_item, shopping_list_items,user.getString("user_name"));
        shopping_list_view.setAdapter(shopping_list_adapter);

        buy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar date = Calendar.getInstance();
                if(e_commerce_db.addOrder(user.getString("user_name"),date.getTime().toString(),user_address_edit_text.getText().toString(),shopping_list_items)){
                    Toast.makeText(getContext(),"Order added",Toast.LENGTH_LONG).show();
                    Fragment fragment = new Categories();
                    fragment.setArguments(user);
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                            android.R.anim.fade_out);
                    fragmentTransaction.replace(R.id.content_home, fragment);
                    fragmentTransaction.commitAllowingStateLoss();
                    getActivity().setTitle("Categories");
                }
            }
        });
        Cursor items=e_commerce_db.getAllItems(user.getString("user_name"));
        if (items != null) {
            while (!items.isAfterLast()){
                ShoppingListItem item=new ShoppingListItem(items.getString(0),items.getFloat(1),items.getInt(2));
                shopping_list_items.add(item);
                total+=items.getFloat(1);
                items.moveToNext();
            }
        }else {
            shopping_list_items.clear();
        }
        total_text_view.setText(String.valueOf(total));
        getLocation();
        return rootView;
    }
    public void getLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (getContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || getContext().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 8000);
        } else {
            LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
            if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 1, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        List<Address> addresses = new ArrayList<Address>();
                        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                        try {
                            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (!addresses.isEmpty()) {
                            Address selected = addresses.get(0);
                            String address = "";
                            for (int i = 0; i < selected.getMaxAddressLineIndex(); i++) {
                                address += selected.getAddressLine(i) + " ";
                            }
                            user_address_edit_text.setText(address);
                        }
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }
                });
            } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        List<Address> addresses = new ArrayList<Address>();
                        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                        try {
                            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (!addresses.isEmpty()) {
                            Address selected = addresses.get(0);
                            String address = "";
                            for (int i = 0; i < selected.getMaxAddressLineIndex(); i++) {
                                address += selected.getAddressLine(i) + " ";
                            }
                            user_address_edit_text.setText(address);
                        }
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }
                });
            }
        }
    }

}
