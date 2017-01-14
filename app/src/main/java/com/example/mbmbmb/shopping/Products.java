package com.example.mbmbmb.shopping;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Products extends AppCompatActivity {
    private E_CommerceDB e_commerce_db;
    private List<Product> product_list;
    private ProductAdapter product_adapter;
    private ListView product_list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        e_commerce_db=new E_CommerceDB(this);
        product_list=new ArrayList<>();
        String user_name=getIntent().getStringExtra("user_name");
        product_adapter=new ProductAdapter(this,R.layout.product_item,product_list,user_name);
        product_list_view=(ListView) findViewById(R.id.activity_products);
        product_list_view.setAdapter(product_adapter);
        String category_name=getIntent().getStringExtra("name");
        setTitle(category_name);
        Cursor products= e_commerce_db.ProductsByCategory(category_name);
        while (!products.isAfterLast()){
            Product product=new Product(products.getString(0),products.getFloat(1), products.getInt(2));
            product_list.add(product);
            products.moveToNext();
        }
    }
}
