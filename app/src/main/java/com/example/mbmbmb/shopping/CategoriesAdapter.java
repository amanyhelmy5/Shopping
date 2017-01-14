package com.example.mbmbmb.shopping;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mbmbmb on 12/12/2016.
 */
public class CategoriesAdapter extends ArrayAdapter {
    private String user_name;
    public CategoriesAdapter(Context context, List<String> data, String user_name) {
        super(context,0,data);
        this.user_name=user_name;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.category_item,parent,false);
        }
        final String category= (String) getItem(position);
        TextView category_name_text_view=(TextView) convertView.findViewById(R.id.tv_product_name);
        category_name_text_view.setText(category);
        category_name_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent products=new Intent(getContext(),Products.class);
                products.putExtra("name",category);
                products.putExtra("user_name",user_name);
                getContext().startActivity(products);
            }
        });
        ImageView category_image_view=(ImageView) convertView.findViewById(R.id.iv_product_image);
        category_image_view.setImageResource(R.drawable.sh);
        return convertView;
    }
}

