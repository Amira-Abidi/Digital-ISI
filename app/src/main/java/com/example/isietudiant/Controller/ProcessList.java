package com.example.isietudiant.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isietudiant.Adapter.MyAdapter;
import com.example.isietudiant.Api.ApiClient;
import com.example.isietudiant.Cookies.AddCookiesInterceptor;
import com.example.isietudiant.Models.Process;
import com.example.isietudiant.Models.User;
import com.example.isietudiant.R;
import com.example.isietudiant.Service.ProcessService;
import com.example.isietudiant.Service.UserService;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProcessList extends AppCompatActivity {
    TextView proc1;
    String userId;
    List<Process> process;
    MyAdapter adapter;
    RecyclerView rView;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_process_list);

        Intent intent = getIntent();
        context= this;
        proc1 = (TextView) findViewById(R.id.proc);
        GetUserById();
        rView = (RecyclerView) findViewById(R.id.recyclev);
    }
    private void GetUserById() {
        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new AddCookiesInterceptor(this)); // VERY VERY IMPORTANT
        client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://digitalisi.tn:8080/bonita/") // REQUIRED
                .client(client) // VERY VERY IMPORTANT
                .addConverterFactory(GsonConverterFactory.create())
                .build(); // REQUIRED
        UserService userService = retrofit.create(UserService.class);
        // get user by id
        Call<User> call1= userService.getUserId();
        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    userId = response.body().getUser_id();
                    Toast.makeText(ProcessList.this, "success "+response.code() + " userId= " + userId, Toast.LENGTH_SHORT).show();
                    GetProcess();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(ProcessList.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).
                        show();
            }
        });
    }
    private void GetProcess() {
        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new AddCookiesInterceptor(this)); // VERY VERY IMPORTANT
        client = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://digitalisi.tn:8080/bonita/") // REQUIRED
                .client(client) // VERY VERY IMPORTANT
                .addConverterFactory(GsonConverterFactory.create())
                .build(); // REQUIRED
        ProcessService processService  = retrofit.create(ProcessService .class);
        // get user by id
        Call<List<Process>> listeProc=  processService.getProcessus("0", "100" , "version%20desc" , "activationState=ENABLED", "user_id=" + userId);
        listeProc.enqueue(new Callback<List<Process>>() {
            @Override
            public void onResponse(Call<List<Process>> call, Response<List<Process>> response) {
                if (response.isSuccessful()) {
                    List<Process> processus = response.body();
                    String content = "";
                    process=  new ArrayList<>();
                    process=response.body();
                    adapter= new MyAdapter(context,process);
                    rView.setLayoutManager(new LinearLayoutManager(context));
                    rView.setHasFixedSize(true);
                    rView.setAdapter(adapter);
                    // content += "Id: " + processus.get(0).getId();
                    content += "Name: " + processus.get(0).getName();
                    // content += "deploymentDate: " + processus.get(0).getDeploymentDate();
                    // content += "last_update_date: " + processus.get(0).getLast_update_date();

                    Toast.makeText(ProcessList.this, "process loading "+response.code() + content, Toast.LENGTH_SHORT).show();

                    // proc1.setText(content);
                    // adapter.setListProcess(process);
                    Toast.makeText(ProcessList.this, "success "+response.code() , Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ProcessList.this, "failure "+response.code() , Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<Process>> call, Throwable t) {
                Toast.makeText(ProcessList.this,"Throwable ", Toast.LENGTH_LONG).
                        show();
            }


        });
    }

}