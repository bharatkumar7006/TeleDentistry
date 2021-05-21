package com.example.teledentistry.PatientModule.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teledentistry.PatientModule.OnRadioButtonClickListener;
import com.example.teledentistry.R;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Appointment_Slot_RecyclerView_Adapter extends RecyclerView.Adapter<Appointment_Slot_RecyclerView_Adapter.ViewHold> {
    Context context;
    View view;
    MaterialRadioButton lastChecked;
    int lastSelectedPosition;
    String date;
    DatabaseReference reference;
    List<String> list;
    OnRadioButtonClickListener onRadioButtonClickListener;

    public Appointment_Slot_RecyclerView_Adapter(Context context, View view,
            List<String> list,String date, OnRadioButtonClickListener onRadioButtonClickListener) {
        this.context = context;
        this.view = view;
        this.list = list;
        this.date = date;
        this.onRadioButtonClickListener = onRadioButtonClickListener;
    }

    @NonNull
    @Override
    public ViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View slots = layoutInflater.inflate(R.layout.time_slots_doc_module, parent, false);
        ViewHold viewHold = new ViewHold(slots,onRadioButtonClickListener);
        return viewHold;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHold holder, final int position) {

        holder.materialRadioButton1.setText(list.get(position));

      holder.materialRadioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if(lastChecked!=null) {
                  if(lastSelectedPosition!= position) {
                      lastChecked.setChecked(false);
                      lastChecked = null;
                  }
              }
              lastChecked = holder.materialRadioButton1;
              lastSelectedPosition = position;
              onRadioButtonClickListener.onRadioClick(position);
          }

      });


    }

    public void setList(List<String> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        return 0;
    }


    public class ViewHold extends RecyclerView.ViewHolder {
        MaterialRadioButton materialRadioButton1;
        OnRadioButtonClickListener onRadioButtonClickListener;
        public ViewHold(@NonNull View itemView, OnRadioButtonClickListener onRadioButtonClickListener) {
            super(itemView);
            materialRadioButton1 = itemView.findViewById(R.id.radio_btn);
            this.onRadioButtonClickListener = onRadioButtonClickListener;
        }

    }

}


