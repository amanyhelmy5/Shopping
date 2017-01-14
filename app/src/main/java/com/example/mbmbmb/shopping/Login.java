package com.example.mbmbmb.shopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText user_name_edit_text;
    //private ShowHidePasswordEditText password_edit_text;
    private EditText password_edit_text;

    private E_CommerceDB e_commerce_db;
    private CheckBox remeber_data_check_box;
    private SharedPreferences user_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_name_edit_text=(EditText) findViewById(R.id.et_user_name);
        password_edit_text=(EditText) findViewById(R.id.et_password);
        remeber_data_check_box=(CheckBox) findViewById(R.id.cb_remeber_data);
        e_commerce_db=new E_CommerceDB(this);
        user_data=getSharedPreferences("user_data",MODE_PRIVATE);
        if (!user_data.getString("user_name","dummy").equals("dummy")&&!user_data.getString("password","dummy").equals("dummy")){
            String user_name=user_data.getString("user_name","dummy");
            String password=user_data.getString("password","dummy");
            if(e_commerce_db.logIn(user_name,password)) {
                Intent home_intent = new Intent(Login.this, Home.class);
                home_intent.putExtra("user_name",user_name);
                startActivity(home_intent);
            }else {
                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void login(View view) {
        String user_name=(user_name_edit_text.getText().toString().isEmpty())?null:user_name_edit_text.getText().toString();
        String password=(password_edit_text.getText().toString().isEmpty())?null:password_edit_text.getText().toString();
        if(user_name==null){
            user_name_edit_text.setError(" User Name Required");
        }
        if(password==null){
            password_edit_text.setError("Password Required");
        }
        if(user_name!=null&&password!=null) {
            if(e_commerce_db.logIn(user_name,password)) {
                if(remeber_data_check_box.isChecked()) {
                    SharedPreferences.Editor user_data_editor = user_data.edit();
                    user_data_editor.putString("user_name", user_name);
                    user_data_editor.putString("password", password);
                    user_data_editor.commit();
                }
                Intent home_intent = new Intent(Login.this, Home.class);
                home_intent.putExtra("user_name",user_name);
                startActivity(home_intent);
            }else {
                Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void createAccount(View view) {
        Intent signup_intent=new Intent(Login.this,Signup.class);
        startActivity(signup_intent);
    }

    public void forgotPassword(View view) {
        Intent forgot_password_intent=new Intent(Login.this,PsswordForgoting.class);
        startActivity(forgot_password_intent);
    }
}



