package com.example.mbmbmb.shopping;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Search extends Fragment {
    private ImageButton text_button,audio_button;
    private EditText query_edit_text;
    private final int REQUEST_AUDIO=8000;
    private E_CommerceDB e_commerce_db;
    private Cursor search_results;
    private List<Product> results_list;
    private ListView results_list_view;
    private ProductAdapter results_adapter;
    public Search() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_search, container, false);
        query_edit_text=(EditText) rootView.findViewById(R.id.et_search_text);
        audio_button=(ImageButton) rootView.findViewById(R.id.ibtn_audio);
        text_button=(ImageButton) rootView.findViewById(R.id.ibtn_text);
        e_commerce_db=new E_CommerceDB(getContext());
        results_list=new ArrayList<>();
        results_list_view=(ListView) rootView.findViewById(R.id.lv_search_result);
        results_adapter=new ProductAdapter(getContext(),R.layout.product_item,results_list, getArguments().getString("user_name"));
        results_list_view.setAdapter(results_adapter);
        text_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(query_edit_text.getVisibility()==View.VISIBLE ){
                    query_edit_text.setVisibility(View.GONE);
                }else {
                    query_edit_text.setVisibility(View.VISIBLE);
                }

            }
        });
        audio_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent get_text=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(get_text,REQUEST_AUDIO);
            }
        });
        query_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                search_results=e_commerce_db.searchProducts(charSequence.toString());
                results_list.clear();
                while (!search_results.isAfterLast()){
                    Product product=new Product(search_results.getString(0),search_results.getFloat(1), search_results.getInt(2));
                    results_list.add(product);
                    search_results.moveToNext();
                }
                results_adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(REQUEST_AUDIO==requestCode && resultCode==getActivity().RESULT_OK){
            ArrayList<String> results=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            results_list.clear();
            search_results=e_commerce_db.searchProducts(results.get(0));
            if (search_results!=null){
                while (!search_results.isAfterLast()){
                    Product product=new Product(search_results.getString(0),search_results.getFloat(1), search_results.getInt(2));
                    results_list.add(product);
                    search_results.moveToNext();
                }
                results_adapter.notifyDataSetChanged();
            }
        }
    }
}
