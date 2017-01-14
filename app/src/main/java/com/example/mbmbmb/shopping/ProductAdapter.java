package com.example.mbmbmb.shopping;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by mbmbmb on 12/12/2016.
 */
public class ProductAdapter extends ArrayAdapter {
    private E_CommerceDB e_commerce_db;
    private String user_name;
    public ProductAdapter(Context context, int resource, List objects, String user_name) {
        super(context, 0, objects);
        e_commerce_db=new E_CommerceDB(getContext());
        this.user_name=user_name;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.product_item,parent,false);
        }
        Product product= (Product) getItem(position);
        TextView product_name_text_view=(TextView) convertView.findViewById(R.id.tv_product_name);
        product_name_text_view.setText(product.getName());
        TextView product_price_text_view=(TextView) convertView.findViewById(R.id.tv_product_price);
        product_price_text_view.setText(String.valueOf(product.getPrice()));
        TextView product_quantity_text_view=(TextView) convertView.findViewById(R.id.tv_product_quantity);
        product_quantity_text_view.setText(String.valueOf(product.getQuantity()));
        ImageView add_to_cart_image_view=(ImageView) convertView.findViewById(R.id.iv_add_to_cart);
        add_to_cart_image_view.setImageResource(R.drawable.buy);
        add_to_cart_image_view.setTag(product);
        add_to_cart_image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product_name=((Product)view.getTag()).getName();
                float product_price=((Product)view.getTag()).getPrice();
                ShoppingListItem item=new ShoppingListItem(product_name,product_price,1);
                if(e_commerce_db.addToShoppingList(user_name,item)){
                    Toast.makeText(getContext(),"Product Added To Shopping Cart",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"Failed To Add To Shopping Cart",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }
}
