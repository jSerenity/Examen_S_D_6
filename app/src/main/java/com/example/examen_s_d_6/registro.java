package com.example.examen_s_d_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import com.example.examen_s_d_6.tablas.tablas;
public class registro extends AppCompatActivity {
    EditText name, email, phone, password;
    Button register;
    TextView login;
    boolean isNameValid, isEmailValid, isPhoneValid, isPasswordValid;
    TextInputLayout nameError, emailError, phoneError, passError;
    AppSQLiteOpenHepler conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        conn= new AppSQLiteOpenHepler(this,"db_restaurant",null,1);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);
        login = (TextView) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        nameError = (TextInputLayout) findViewById(R.id.nameError);
        emailError = (TextInputLayout) findViewById(R.id.emailError);
        phoneError = (TextInputLayout) findViewById(R.id.phoneError);
        passError = (TextInputLayout) findViewById(R.id.passError);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetValidation();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to LoginActivity
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void SetValidation() {
        // Check for a valid name.
        if (name.getText().toString().isEmpty()) {
            nameError.setError(getResources().getString(R.string.name_error));
            isNameValid = false;
        } else  {
            isNameValid = true;
            nameError.setErrorEnabled(false);
        }

        // Check for a valid email address.
        if (email.getText().toString().isEmpty()) {
            emailError.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            emailError.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
            emailError.setErrorEnabled(false);
        }

        // Check for a valid phone number.
        if (phone.getText().toString().isEmpty()) {
            phoneError.setError(getResources().getString(R.string.phone_error));
            isPhoneValid = false;
        } else  {
            isPhoneValid = true;
            phoneError.setErrorEnabled(false);
        }

        // Check for a valid password.
        if (password.getText().toString().isEmpty()) {
            passError.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (password.getText().length() < 6) {
            passError.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
            passError.setErrorEnabled(false);
        }

        if (isNameValid && isEmailValid && isPhoneValid && isPasswordValid) {
            if(registrarUser(name.getText().toString(),email.getText().toString(),phone.getText().toString(),password.getText().toString())){
                Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
                redirect();
            }else {
                Toast.makeText(getApplicationContext(), "Usuario NO registrado", Toast.LENGTH_SHORT).show();
            }

        }

    }

    private  boolean registrarUser(String name,String email,String phone,String password){
        SQLiteDatabase db = conn.getWritableDatabase();
        boolean result=false;
        ContentValues values = new ContentValues();
        values.put(tablas.CAMPO_NOMBRE, name);
        values.put(tablas.CAMPO_EMAIL, email.toUpperCase());
        values.put(tablas.CAMPO_PHONE, phone);
        values.put(tablas.CAMPO_PASSWORD, password);
        long id = db.insert(tablas.TABLA_USUARIOS, tablas.CAMPO_ID,values);
        if(id>0){
            result=true;
        }
        db.close();
        return  result;
    }

    private void redirect() {
        Intent intent=new Intent(registro.this,MainActivity.class);

        startActivity(intent);
    }
}