package com.example.isietudiant.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.isietudiant.R;

public class DemandeStage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_stage);
        Intent i = new Intent();
        int id = i.getIntExtra("id", 1);
        Toast.makeText(this, "id:"+id, Toast.LENGTH_SHORT).show();

    }
}