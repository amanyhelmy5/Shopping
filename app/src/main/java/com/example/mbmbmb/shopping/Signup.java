package com.example.mbmbmb.shopping;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class Signup extends AppCompatActivity {
    private EditText customer_name_edit_text;
    private EditText user_name_edit_text;
    private EditText email_edit_text;
    private EditText password_edit_text;
    private RadioGroup gender_radio_group;
    private EditText job_edit_text;
    private Date birth_date;
    private TextView birth_date_text_view;
    private E_CommerceDB e_commerce_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        customer_name_edit_text=(EditText) findViewById(R.id.et_cust_name);
        user_name_edit_text=(EditText) findViewById(R.id.et_user_name);
        email_edit_text=(EditText) findViewById(R.id.et_email);
        password_edit_text=(EditText) findViewById(R.id.et_password);
        gender_radio_group=(RadioGroup) findViewById(R.id.rg_gender);
        job_edit_text=(EditText) findViewById(R.id.et_job);
        birth_date_text_view=(TextView) findViewById(R.id.tv_birthdate);
        e_commerce_db=new E_CommerceDB(this);
        birth_date=new Date();
    }

    public void pickDate(View view) {
        Calendar today_calendar=Calendar.getInstance();
        int initial_day_of_month=today_calendar.get(Calendar.DAY_OF_MONTH);
        int initial_month_of_year=today_calendar.get(Calendar.MONTH);
        int initial_year=today_calendar.get(Calendar.YEAR);
        DatePickerDialog birth_date_picker_dialog=new DatePickerDialog(Signup.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                birth_date.setDate(dayOfMonth);
                birth_date.setMonth(monthOfYear);
                birth_date.setYear(year);
                birth_date_text_view.setText(birth_date.toString());
            }
        },initial_year,initial_month_of_year,initial_day_of_month);
        birth_date_picker_dialog.getDatePicker().setMaxDate(new Date().getTime());
        birth_date_picker_dialog.show();
    }

    public void signup(View view) {
        String customer_name = (customer_name_edit_text.getText().toString().isEmpty()) ? null : customer_name_edit_text.getText().toString();
        String user_name = (user_name_edit_text.getText().toString().isEmpty()) ? null : user_name_edit_text.getText().toString();
        String email = (email_edit_text.getText().toString().isEmpty()) ? null : email_edit_text.getText().toString();
        String password = (password_edit_text.getText().toString().isEmpty()) ? null : password_edit_text.getText().toString();
        int selected_id=gender_radio_group.getCheckedRadioButtonId();
        RadioButton rb_gender=(RadioButton) findViewById(selected_id);
        String gender = rb_gender.getText().toString();
        String job = (job_edit_text.getText().toString().isEmpty()) ? null : job_edit_text.getText().toString();
        if (customer_name == null) {
            customer_name_edit_text.setError("Please Enter Customer Name");
        }if (user_name == null) {
            user_name_edit_text.setError("Please Enter User Name");
        }if(email == null){
            email_edit_text.setError("Please Enter Email");
        }if (password == null) {
            password_edit_text.setError("Please Enter Password");
        }if (birth_date == null) {
            Toast.makeText(getApplicationContext(), "Please Pick Birthdate", Toast.LENGTH_LONG).show();
        }if (job == null) {
            job_edit_text.setError("Please Enter Job");
        }
        if(customer_name!=null&&user_name!=null&&email!=null&&password!=null&&gender!=null&&birth_date!=null&&job!=null) {
            if (e_commerce_db.signUp(customer_name, user_name,email, password, gender, birth_date.toString(), job)) {
                Intent home_intent = new Intent(Signup.this, Home.class);
                home_intent.putExtra("user_name",user_name);
                startActivity(home_intent);
            } else {
                Toast.makeText(getApplicationContext(), "Signup Failed", Toast.LENGTH_LONG).show();
            }
        }

    }
}
