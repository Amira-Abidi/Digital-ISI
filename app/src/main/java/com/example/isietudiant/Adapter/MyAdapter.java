package com.example.isietudiant.Adapter;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.isietudiant.Controller.DemandeStage;
import com.example.isietudiant.Models.Process;
import com.example.isietudiant.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.viewHolder> {

    public List<Process> processlist = new ArrayList<>();
    public Context context;
    // data is passed into the constructor
    public MyAdapter(Context context, List<Process> process) {
        this.processlist = process;
        this.context=context;
    }
    public void setListProcess(List<Process> process)
    { this.processlist=new ArrayList<>(process);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.itemdata,viewGroup,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.proc.setText(processlist.get(position).getDisplayName());
        holder.proc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DemandeStage.class);
                i.putExtra("id",processlist.get(position).getId());
                context.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return processlist.size();
    }
    @Override
    public void registerAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    // stores and recycles views as they are scrolled off screen
    public static class viewHolder extends RecyclerView.ViewHolder  {
        TextView proc;


        public   viewHolder(@NonNull View itemView) {
            super(itemView);
            proc = (TextView) itemView.findViewById(R.id.proc);

        }

    }

}