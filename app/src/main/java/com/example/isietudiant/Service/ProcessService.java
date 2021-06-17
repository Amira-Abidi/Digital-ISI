package com.example.isietudiant.Service;

import com.example.isietudiant.Models.Process;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProcessService {
    @GET("API/bpm/process")
    Call<List<Process>> getProcessus (
            @Query(value="p", encoded = true) String p,
            @Query(value="c", encoded = true) String c,
            @Query(value="o", encoded = true) String o,
            @Query(value="f", encoded = true) String activationState,
            @Query(value="f", encoded = true) String IdUser
    );
}
