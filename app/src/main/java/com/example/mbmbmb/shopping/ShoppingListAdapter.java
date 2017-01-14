package com.example.mbmbmb.shopping;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class ShoppingListAdapter extends ArrayAdapter {
    private E_CommerceDB e_commerce_db;
    private String user_name;
    public ShoppingListAdapter(Context context, int resource, List objects, String user_name) {
        super(context, 0, objects);
        e_commerce_db=new E_CommerceDB(getContext());
        this.user_name=user_name;
    }

    @NonNull
    @Override
    public View getView( int position, View convertView,  ViewGroup parent) {

        ShoppingListItem shopping_list= (ShoppingListItem) getItem(position);
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shopping_list_item, parent, false);
        }
        TextView product_name_text_view=(TextView) convertView.findViewById(R.id.tv_product_name);
        product_name_text_view.setText(shopping_list.getName());
        TextView product_price_text_view=(TextView) convertView.findViewById(R.id.tv_product_price);
        product_price_text_view.setText(String.valueOf(shopping_list.getPrice()));
        TextView quantity_text_view=(TextView) convertView.findViewById(R.id.tv_product_quantity);
        quantity_text_view.setText(String.valueOf(shopping_list.getQuantity()));
        ImageButton remove_product_image_button=(ImageButton) convertView.findViewById(R.id.ibtn_remove_product);
        remove_product_image_button.setImageResource(R.drawable.delet);
        remove_product_image_button.setTag(shopping_list);
        remove_product_image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product_name = ((ShoppingListItem) view.getTag()).getName();
                float product_price = ((ShoppingListItem) view.getTag()).getPrice();
                int product_quantity = ((ShoppingListItem) view.getTag()).getQuantity();
                ShoppingListItem item = new ShoppingListItem(product_name, product_price, product_quantity);
                if (e_commerce_db.removeFromShoppingList(user_name, item)) {
                    Toast.makeText(getContext(), "Product Removed From Shopping Cart", Toast.LENGTH_SHORT).show();
                    Fragment fragment = new ShoppingList();
                    Bundle user = new Bundle();
                    user.putString("user_name", user_name);
                    fragment.setArguments(user);
                    FragmentTransaction fragmentTransaction = ((Home) getContext()).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                            android.R.anim.fade_out);
                    fragmentTransaction.replace(R.id.content_home, fragment);
                    fragmentTransaction.commitAllowingStateLoss();
                } else {
                    Toast.makeText(getContext(), "Failed To Remove From Shopping Cart", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button plus=(Button) convertView.findViewById(R.id.plus);
        plus.setTag(shopping_list);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product_name = ((ShoppingListItem) v.getTag()).getName();
                int product_quantity = ((ShoppingListItem) v.getTag()).getQuantity();
                float product_price = ((ShoppingListItem) v.getTag()).getPrice();
                product_quantity++;
                ShoppingListItem item = new ShoppingListItem(product_name, product_price, product_quantity);
                if (e_commerce_db.editQuantity(user_name, item)) {
                    Fragment fragment = new ShoppingList();
                    Bundle user = new Bundle();
                    user.putString("user_name", user_name);
                    fragment.setArguments(user);
                    FragmentTransaction fragmentTransaction = ((Home) getContext()).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                            android.R.anim.fade_out);
                    fragmentTransaction.replace(R.id.content_home, fragment);
                    fragmentTransaction.commitAllowingStateLoss();
                }
            }
        });
        //
        Button minus=(Button) convertView.findViewById(R.id.minus);
        minus.setTag(shopping_list);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product_name=((ShoppingListItem) v.getTag()).getName();
                int product_quantity=((ShoppingListItem) v.getTag()).getQuantity();
                float product_price=((ShoppingListItem) v.getTag()).getPrice();
                product_quantity--;
                ShoppingListItem item=new ShoppingListItem(product_name,product_price,product_quantity);
                if(e_commerce_db.editQuantity(user_name,item)){
                    Fragment fragment = new ShoppingList();
                    Bundle user=new Bundle();
                    user.putString("user_name",user_name);
                    fragment.setArguments(user);
                    FragmentTransaction fragmentTransaction = ((Home)getContext()).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                            android.R.anim.fade_out);
                    fragmentTransaction.replace(R.id.content_home, fragment);
                    fragmentTransaction.commitAllowingStateLoss();
                }
            }
        });

        return convertView;
    }
}
