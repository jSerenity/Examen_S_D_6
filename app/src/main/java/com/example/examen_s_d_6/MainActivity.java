package com.example.examen_s_d_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.example.examen_s_d_6.tablas.tablas;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    Button login;
    TextView register;
    boolean isEmailValid, isPasswordValid;
    TextInputLayout emailError, passError;
    AppSQLiteOpenHepler conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conn= new AppSQLiteOpenHepler(this,"db_restaurant",null,1);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);
        emailError = (TextInputLayout) findViewById(R.id.emailError);
        passError = (TextInputLayout) findViewById(R.id.passError);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetValidation();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), registro.class);
                startActivity(intent);
            }
        });
    }
    public void SetValidation() {
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

        if (isEmailValid && isPasswordValid) {
            if(isUser(email.getText().toString(),password.getText().toString())){
                Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
                redirect();
            }
            else{
                Toast.makeText(getApplicationContext(), "INCORRECT USER OR PASSWORD", Toast.LENGTH_SHORT).show();
            }
        }

    }
    public boolean isUser(String email,String password){
        boolean result=false;
        SQLiteDatabase db=conn.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT nombre, password  FROM "+ tablas.TABLA_USUARIOS +" WHERE "
                + tablas.CAMPO_EMAIL +"=" +"'"+email+"'" + " AND "+ tablas.CAMPO_PASSWORD+"="+"'"+password+"'",null);

        if (cursor != null) {
            if(cursor.getCount() > 0){
                result =true;
            }
        }
        db.close();
        return result;
    }
    private void redirect() {
        Intent intent=new Intent(MainActivity.this,HomeActivity.class);

        startActivity(intent);
    }
}