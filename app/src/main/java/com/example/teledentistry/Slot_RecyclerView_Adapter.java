package com.example.teledentistry;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.radiobutton.MaterialRadioButton;

import java.util.Collections;
import java.util.List;

public class Slot_RecyclerView_Adapter extends RecyclerView.Adapter<Slot_RecyclerView_Adapter.ViewHold> {
    MaterialRadioButton materialRadioButton1;
    Context context;
    View view;
    List<String> list;


    public Slot_RecyclerView_Adapter(Context context, View view, List<String> list) {

        this.context = context;
        this.view = view;
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View slots = layoutInflater.inflate(R.layout.time_slots, parent, false);
        ViewHold viewHold = new ViewHold(slots);
        return viewHold;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHold holder, int position) {

        holder.materialRadioButton1.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHold extends RecyclerView.ViewHolder{
        MaterialRadioButton materialRadioButton1;

        public ViewHold(@NonNull View itemView) {
            super(itemView);
            materialRadioButton1 = itemView.findViewById(R.id.radiobtn_1);

            
        }

    }
}
