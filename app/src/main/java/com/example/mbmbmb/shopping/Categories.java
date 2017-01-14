package com.example.mbmbmb.shopping;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;


public class Categories extends Fragment {

    private E_CommerceDB e_commerce_db;
    private GridView categories_grid_view;
    private List<String> categories_list;
    private CategoriesAdapter categories_adapter;
    public Categories() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_categories, container, false);
        categories_grid_view=(GridView) rootView.findViewById(R.id.lv_categories);
        e_commerce_db=new E_CommerceDB(getContext());
        categories_list=new ArrayList<>();
        String user_name=getArguments().getString("user_name");
        categories_adapter=new CategoriesAdapter(getContext(),categories_list,user_name);
        categories_grid_view.setAdapter(categories_adapter);
        Cursor categories=e_commerce_db.getCategories();
        while (!categories.isAfterLast()){
            categories_list.add(categories.getString(0));
            categories.moveToNext();
        }
        return rootView;
    }

}
