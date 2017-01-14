package com.example.mbmbmb.shopping;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PsswordForgoting extends AppCompatActivity {
    private Button send_mail;
    private EditText email_edit_text;
    private E_CommerceDB e_commerce_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pssword_forgoting);
        email_edit_text=(EditText) findViewById(R.id.et_email);
        send_mail=(Button) findViewById(R.id.btn_send_mail);
        e_commerce_db=new E_CommerceDB(this);
        send_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = (email_edit_text.getText().toString().isEmpty()) ? null : email_edit_text.getText().toString();
                String password = e_commerce_db.getUserPassword(email);
              //   Sender Sender = new Sender("amany.helmy03@gmail.com", "12345");
                try {
                    /*
                    Sender.setFromAddress("amany.helmy03@gmail.com");
                    Sender.setMailBody(password);
                    Sender.setMailSubject("Forgot Your Password");
                    Sender.setToAddresses(email);
                    Sender.send();*/
                    Toast.makeText(getApplicationContext(), "Mail sent", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), password, Toast.LENGTH_LONG).show();
            }
        });
    }
}
