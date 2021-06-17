package com.example.isietudiant.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.isietudiant.Api.ApiClient;
import com.example.isietudiant.R;
import com.example.isietudiant.Service.UserService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextInputEditText username, password;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.user);
        password = findViewById(R.id.pass);
        btnLogin = findViewById(R.id.log);
        btnLogin.setOnClickListener((view)-> {
            login(
                    username.getText().toString(),
                    password.getText().toString()


            );
        });



    }
    public void login(String username, String password){
        ApiClient api = new ApiClient();
        UserService userService = api.getRetrofit(this).create(UserService.class);
        Map<String, String> map = new HashMap<>();
        map.put("username" , username);
        map.put("password" , password);

        Call<ResponseBody> call= userService.loginUser(map);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Intent i = new Intent(MainActivity.this,ProcessList.class);
                    startActivity(i);

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).
                        show();
            }

        });

    }


}